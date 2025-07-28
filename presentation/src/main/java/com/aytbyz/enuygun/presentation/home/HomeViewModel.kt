package com.aytbyz.enuygun.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aytbyz.enuygun.domain.usecase.GetProductsUseCase
import com.aytbyz.enuygun.presentation.home.intent.HomeIntent
import com.aytbyz.enuygun.presentation.home.model.HomeUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProducts: GetProductsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState: StateFlow<HomeUIState> = _uiState

    var isFavorite by mutableStateOf(false)

    init {
        fetchProducts()
    }

    fun toggleFavorite() {
        isFavorite = !isFavorite
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            getProducts(
                query = _uiState.value.searchQuery,
                sortBy = _uiState.value.selectedSortOption?.field?.key,
                sortDirection = _uiState.value.selectedSortOption?.direction?.key
            ).collectLatest { products ->
                _uiState.update { it.copy(products = products) }
            }
        }
    }

    fun onIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.OnSortClick -> {
                _uiState.value = _uiState.value.copy(showSortBottomSheet = true)
            }

            is HomeIntent.OnSearchChange -> {
                _uiState.update { it.copy(searchQuery = intent.query) }
                fetchProducts()
            }

            is HomeIntent.OnSortSelected -> {
                _uiState.update {
                    it.copy(
                        selectedSortOption = intent.option,
                        showSortBottomSheet = false
                    )
                }
                fetchProducts()
            }
        }
    }

    fun dismissSortBottomSheet() {
        _uiState.value = _uiState.value.copy(showSortBottomSheet = false)
    }
}