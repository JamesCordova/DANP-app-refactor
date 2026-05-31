package com.aero.refactorapp.ui.navigation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.aero.refactorapp.ui.navigation.NavScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(title: String) {
    TopAppBar(
        title = { Text(title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
fun AppBottomNavigationBar(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            selected = currentRoute == NavScreens.HOME.route,
            onClick = { onNavigate(NavScreens.HOME.route) },
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text(NavScreens.HOME.label) }
        )
        NavigationBarItem(
            selected = currentRoute == NavScreens.FAVORITES.route,
            onClick = { onNavigate(NavScreens.FAVORITES.route) },
            icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
            label = { Text(NavScreens.FAVORITES.label) }
        )
        NavigationBarItem(
            selected = currentRoute == NavScreens.CART.route,
            onClick = { onNavigate(NavScreens.CART.route) },
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = null) },
            label = { Text(NavScreens.CART.label) }
        )
    }
}

@Composable
fun CheckoutSuccessDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("¡Compra Exitosa!") },
        text = { Text("Tu pedido ha sido procesado correctamente.") },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Aceptar")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailHeader(
    isFavorite: Boolean,
    onBack: () -> Unit,
    onFavoriteToggle: () -> Unit
) {
    TopAppBar(
        title = { Text("Detalle del Producto") },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
            }
        },
        actions = {
            IconButton(onClick = onFavoriteToggle) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorito",
                    tint = if (isFavorite) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
                )
            }
        }
    )
}
