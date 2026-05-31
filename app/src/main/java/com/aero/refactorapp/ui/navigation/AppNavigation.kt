package com.aero.refactorapp.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aero.refactorapp.ui.MainViewModel
import com.aero.refactorapp.ui.features.cart.CartScreen
import com.aero.refactorapp.ui.features.cart.CartViewModel
import com.aero.refactorapp.ui.features.favorites.FavoritesScreen
import com.aero.refactorapp.ui.features.favorites.FavoritesViewModel
import com.aero.refactorapp.ui.features.productDetail.DetailScreen
import com.aero.refactorapp.ui.features.productDetail.DetailViewModel
import com.aero.refactorapp.ui.features.productstore.ProductStoreScreen
import com.aero.refactorapp.ui.features.productstore.ProductStoreViewModel
import com.aero.refactorapp.ui.navigation.components.*

@Composable
fun AppNavigation(
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val productStoreViewModel: ProductStoreViewModel = hiltViewModel()

    var showCheckoutSuccessDialog by remember { mutableStateOf(false) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route?.substringBefore("/") ?: NavScreens.HOME.route

    val navigationCallbacks = remember(navController) {
        NavigationCallbacks(
            navigateToDetail = { id -> navController.navigate("detail/$id") },
            navigateBack = { navController.popBackStack() },
            navigateToHome = {
                navController.navigate(NavScreens.HOME.route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            },
            navigateToFavorites = {
                navController.navigate(NavScreens.FAVORITES.route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            },
            navigateToCart = {
                navController.navigate(NavScreens.CART.route) {
                    launchSingleTop = true
                }
            }
        )
    }

    if (showCheckoutSuccessDialog) {
        CheckoutSuccessDialog(
            onDismiss = {
                showCheckoutSuccessDialog = false
                navigationCallbacks.navigateToHome()
            }
        )
    }

    Scaffold(
        topBar = {
            when (currentRoute) {
                NavScreens.HOME.route -> AppToolbar(title = NavScreens.HOME.label)
                NavScreens.FAVORITES.route -> AppToolbar(title = NavScreens.FAVORITES.label)
                NavScreens.CART.route -> AppToolbar(title = NavScreens.CART.label)
                "detail" -> {
                    val detailViewModel: DetailViewModel = hiltViewModel(navBackStackEntry!!)
                    val detailUiState by detailViewModel.uiState.collectAsState()
                    DetailHeader(
                        isFavorite = detailUiState.isFavorite,
                        onBack = navigationCallbacks.navigateBack,
                        onFavoriteToggle = { detailViewModel.toggleFavorite() }
                    )
                }
                else -> {}
            }
        },
        bottomBar = {
            if (currentRoute != "detail" && currentRoute != NavScreens.CART.route) {
                AppBottomNavigationBar(
                    currentRoute = currentRoute,
                    onNavigate = { route ->
                        navController.navigate(route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = NavScreens.HOME.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            composable(NavScreens.HOME.route) {
                ProductStoreScreen(
                    navigationCallbacks = navigationCallbacks,
                    onThemeChange = mainViewModel::onThemeChange,
                    productStoreViewModel = productStoreViewModel
                )
            }
            composable(NavScreens.FAVORITES.route) {
                val favoritesViewModel: FavoritesViewModel = hiltViewModel()
                FavoritesScreen(
                    navigationCallbacks = navigationCallbacks,
                    favoritesViewModel = favoritesViewModel
                )
            }
            composable("detail/{productId}") {
                val detailViewModel: DetailViewModel = hiltViewModel()
                DetailScreen(
                    navigationCallbacks = navigationCallbacks,
                    detailViewModel = detailViewModel
                )
            }
            composable(NavScreens.CART.route) {
                val cartViewModel: CartViewModel = hiltViewModel()
                CartScreen(
                    navigationCallbacks = navigationCallbacks,
                    cartViewModel = cartViewModel,
                    onCheckoutSuccess = {
                        showCheckoutSuccessDialog = true
                    }
                )
            }
        }
    }
}
