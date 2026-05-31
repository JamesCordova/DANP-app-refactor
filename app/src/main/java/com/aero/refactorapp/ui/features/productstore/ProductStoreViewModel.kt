package com.aero.refactorapp.ui.features.productstore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aero.refactorapp.domain.model.Product
import com.aero.refactorapp.domain.model.ProductCategory
import com.aero.refactorapp.domain.model.User
import com.aero.refactorapp.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class HomeUiState(
    val products: List<Product> = emptyList(),
    val categories: List<String> = emptyList(),
    val selectedCategory: String = "Todos",
    val favoriteProductIds: Set<Int> = emptySet(),
    val searchQuery: String = "",
    val currentUser: User = User(
        id = 1,
        name = "Usuario Demo",
        email = "usuario@example.com",
        favoriteProductIds = emptyList()
    )
) {
	val filteredProducts: List<Product>
		get() {
			val categoryFiltered = if (selectedCategory == "Todos") {
				products
			} else {
				products.filter { it.category.label == selectedCategory }
			}

			return if (searchQuery.isBlank()) {
				categoryFiltered
			} else {
				categoryFiltered.filter {
					it.name.contains(searchQuery, ignoreCase = true) ||
						it.description.contains(searchQuery, ignoreCase = true)
				}
			}
		}
}

@HiltViewModel
class ProductStoreViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {
    private val _selectedCategory = MutableStateFlow("Todos")
    private val _searchQuery = MutableStateFlow("")

    val uiState: StateFlow<HomeUiState> = combine(
        productRepository.products,
        productRepository.categories,
        productRepository.favoriteProductIds,
        _selectedCategory,
        _searchQuery
    ) { products, categories, favorites, category, query ->
        HomeUiState(
            products = products,
            categories = listOf("Todos") + categories,
            favoriteProductIds = favorites,
            selectedCategory = category,
            searchQuery = query
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeUiState()
    )

	fun onCategorySelected(category: String) {
		_selectedCategory.value = category
	}

	fun onSearchQueryChanged(query: String) {
		_searchQuery.value = query
	}

	fun toggleFavorite(productId: Int) {
		productRepository.toggleFavorite(productId)
	}

	fun isFavorite(productId: Int): Boolean {
		return uiState.value.favoriteProductIds.contains(productId)
	}

	fun getFavoriteCount(): Int {
		return uiState.value.favoriteProductIds.size
	}
}
