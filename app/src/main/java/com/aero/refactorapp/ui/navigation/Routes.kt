package com.aero.refactorapp.ui.navigation

enum class NavScreens(val label: String, val route: String) {
    HOME("Modular Store", "home"),
    FAVORITES("Favoritos", "favorites"),
    CART("Carrito", "cart")
}

data class NavigationCallbacks(
    val navigateToDetail: (Int) -> Unit,
    val navigateBack: () -> Unit,
    val navigateToHome: () -> Unit,
    val navigateToFavorites: () -> Unit,
    val navigateToCart: () -> Unit
)