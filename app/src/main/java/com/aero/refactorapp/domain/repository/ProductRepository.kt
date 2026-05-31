package com.aero.refactorapp.domain.repository

import com.aero.refactorapp.domain.model.Product
import kotlinx.coroutines.flow.StateFlow

interface ProductRepository {
    val products: StateFlow<List<Product>>
    val categories: StateFlow<List<String>>
    val favoriteProductIds: StateFlow<Set<Int>>
    fun toggleFavorite(productId: Int)
}
