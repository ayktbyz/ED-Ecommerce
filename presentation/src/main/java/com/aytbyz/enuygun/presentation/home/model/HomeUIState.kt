package com.aytbyz.enuygun.presentation.home.model

import com.aytbyz.enuygun.domain.model.response.Product
import com.aytbyz.enuygun.presentation.components.bottomsheet.FilterSortingOptions

data class HomeUIState(
    val total: Int = 0,
    val searchQuery: String? = null,
    val products: List<Product> = emptyList(),
    val showSortBottomSheet: Boolean = false,
    val selectedSortOption: FilterSortingOptions? = null,
    val canLoadMore: Boolean = true,
    val isLoadingMore: Boolean = false,
    val currentSkip: Int = 0,
    val limit: Int = 30
)