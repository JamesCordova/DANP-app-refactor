package com.aero.refactorapp.ui.features.cart

import androidx.lifecycle.ViewModel
import com.aero.refactorapp.domain.model.Cart
import com.aero.refactorapp.domain.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {
    val cart: StateFlow<Cart> = cartRepository.cart

    fun removeFromCart(productId: Int) {
        cartRepository.removeFromCart(productId)
    }

    fun updateQuantity(productId: Int, quantity: Int) {
        cartRepository.updateQuantity(productId, quantity)
    }

    fun clearCart() {
        cartRepository.clearCart()
    }

    fun getTotalPrice(): Double {
        return cart.value.totalPrice
    }

    fun getTotalItems(): Int {
        return cart.value.totalItems
    }
}
