package com.aero.refactorapp.data.repository

import com.aero.refactorapp.domain.model.CategoryProduct
import com.aero.refactorapp.domain.model.Product
import com.aero.refactorapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor() : ProductRepository {
    private val _products = MutableStateFlow(defaultProducts)
    override val products: StateFlow<List<Product>> = _products.asStateFlow()

    private val _favoriteProductIds = MutableStateFlow<Set<Int>>(emptySet())
    override val favoriteProductIds: StateFlow<Set<Int>> = _favoriteProductIds.asStateFlow()

    override fun toggleFavorite(productId: Int) {
        val current = _favoriteProductIds.value.toMutableSet()
        if (current.contains(productId)) {
            current.remove(productId)
        } else {
            current.add(productId)
        }
        _favoriteProductIds.value = current
    }

    companion object {
        private val defaultProducts = listOf(
            Product(
                id = 1,
                name = "Laptop Gamer",
                description = "RTX 4070 + Ryzen 9",
                price = 2500.0,
                category = CategoryProduct.COMPUTERS,
                imageUrl = "https://www.itsitio.com/wp-content/uploads/2020/07/G531-1-scaled-1.jpg"
            ),
            Product(
                id = 2,
                name = "Mechanical Keyboard",
                description = "RGB Switch Blue",
                price = 120.0,
                category = CategoryProduct.ACCESSORIES,
                imageUrl = "https://i.insider.com/5fd7cf6b78a5740019a15560?width=1200&format=jpeg"
            ),
            Product(
                id = 3,
                name = "Gaming Mouse",
                description = "16000 DPI",
                price = 75.0,
                category = CategoryProduct.ACCESSORIES,
                imageUrl = "https://dlcdnwebimgs.asus.com/gain/1F5AFFA6-D3DC-42CA-B37D-03DAAE123012/w750/h470/fwebp"
            ),
            Product(
                id = 4,
                name = "Iphone 27",
                description = "144Hz IPS",
                price = 1220.0,
                category = CategoryProduct.COMPUTERS,
                imageUrl = "https://i.blogs.es/60b358/ios-27/500_333.webp"
            )
        )
    }
}
