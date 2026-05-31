package com.aero.refactorapp.ui.features.favorites

import com.aero.refactorapp.domain.model.Product

data class FavoritesUiState(
    val favoriteProducts: List<Product> = emptyList(),
    val favoriteProductIds: Set<Int> = emptySet()
)
