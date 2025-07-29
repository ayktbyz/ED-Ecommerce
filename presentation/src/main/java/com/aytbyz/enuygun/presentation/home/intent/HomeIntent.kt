package com.aytbyz.enuygun.presentation.home.intent

import com.aytbyz.enuygun.domain.model.response.Product
import com.aytbyz.enuygun.presentation.components.bottomsheet.FilterSortingOptions
import com.aytbyz.enuygun.presentation.components.bottomsheet.model.ProductFilterUIModel

sealed class HomeIntent {
    data object OnSortClick : HomeIntent()
    data object OnFilterClick : HomeIntent()

    data class OnSearchChange(val query: String) : HomeIntent()
    data class OnSortSelected(val option: FilterSortingOptions) : HomeIntent()
    data class OnFavoriteToggle(val product: Product) : HomeIntent()


    object LoadMore : HomeIntent()
    object OnClearFilter : HomeIntent()
    data class OnApplyFilter(val filter: ProductFilterUIModel) : HomeIntent()

    object OnDismissFilterBottomSheet : HomeIntent()
    object OnDismissSortBottomSheet : HomeIntent()
}