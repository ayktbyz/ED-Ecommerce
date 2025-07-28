package com.aytbyz.enuygun.presentation.home.model

import com.aytbyz.enuygun.domain.model.response.Product
import com.aytbyz.enuygun.presentation.components.bottomsheet.FilterSortingOptions

data class HomeUIState(
    val searchQuery: String? = null,
    val products: List<Product> = emptyList(),
    val showSortBottomSheet: Boolean = false,
    val selectedSortOption: FilterSortingOptions? = null
)