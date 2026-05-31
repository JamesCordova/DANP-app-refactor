package com.aero.refactorapp.domain.repository

import com.aero.refactorapp.domain.model.Cart
import com.aero.refactorapp.domain.model.Product
import kotlinx.coroutines.flow.StateFlow

interface CartRepository {
    val cart: StateFlow<Cart>
    fun addToCart(product: Product, quantity: Int = 1)
    fun removeFromCart(productId: Int)
    fun updateQuantity(productId: Int, quantity: Int)
    fun clearCart()
    fun getCart(): Cart
}
