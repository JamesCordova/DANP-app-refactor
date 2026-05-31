package com.aero.refactorapp.data.repository

import android.util.Log
import com.aero.refactorapp.data.remote.ProductRemoteDataSource
import com.aero.refactorapp.domain.model.Product
import com.aero.refactorapp.domain.model.ProductCategory
import com.aero.refactorapp.domain.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: ProductRemoteDataSource
) : ProductRepository {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    override val products: StateFlow<List<Product>> = _products.asStateFlow()

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    override val categories: StateFlow<List<String>> = _categories.asStateFlow()

    private val _favoriteProductIds = MutableStateFlow<Set<Int>>(emptySet())
    override val favoriteProductIds: StateFlow<Set<Int>> = _favoriteProductIds.asStateFlow()

    init {
        Log.d("ProductRepo", "Initializing ProductRepositoryImpl")
        refreshProducts()
    }

    private fun refreshProducts() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.d("ProductRepo", "Fetching products...")
                val remoteProducts = remoteDataSource.getProducts()
                Log.d("ProductRepo", "Fetched ${remoteProducts.size} products")

                Log.d("ProductRepo", "Fetching categories...")
                val remoteCategories = remoteDataSource.getCategories()
                Log.d("ProductRepo", "Fetched ${remoteCategories.size} categories")

                _categories.value = remoteCategories.map { it.name }

                val mappedProducts = remoteProducts.map { dto ->
                    val categoryName = remoteCategories.find { it.id == dto.categoryId }?.name ?: "Sin categoría"
                    Product(
                        id = dto.id,
                        name = dto.name,
                        description = dto.description ?: "",
                        price = dto.price,
                        category = ProductCategory(categoryName),
                        imageUrl = dto.imageUrl ?: ""
                    )
                }
                _products.value = mappedProducts
                Log.d("ProductRepo", "Products updated: ${mappedProducts.size}")
            } catch (e: Exception) {
                Log.e("ProductRepo", "Error refreshing products", e)
            }
        }
    }

    override fun toggleFavorite(productId: Int) {
        val current = _favoriteProductIds.value.toMutableSet()
        if (current.contains(productId)) {
            current.remove(productId)
        } else {
            current.add(productId)
        }
        _favoriteProductIds.value = current
    }
}
