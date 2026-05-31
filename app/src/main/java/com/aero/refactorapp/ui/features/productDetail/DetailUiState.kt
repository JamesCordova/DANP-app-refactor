package com.aero.refactorapp.ui.features.productDetail

import com.aero.refactorapp.domain.model.Product

data class DetailUiState(
    val product: Product? = null,
    val isFavorite: Boolean = false
)
