package com.aero.refactorapp.ui.features.cart

import com.aero.refactorapp.domain.model.Cart

data class CartUiState(
    val cart: Cart = Cart()
)
