package com.aytbyz.enuygun.presentation.home.intent

import com.aytbyz.enuygun.presentation.components.bottomsheet.FilterSortingOptions

sealed class HomeIntent {
    data object OnSortClick : HomeIntent()
    data class OnSearchChange(val query: String) : HomeIntent()
    data class OnSortSelected(val option: FilterSortingOptions) : HomeIntent()
}