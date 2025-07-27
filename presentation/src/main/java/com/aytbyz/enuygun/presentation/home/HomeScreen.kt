package com.aytbyz.enuygun.presentation.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.aytbyz.enuygun.presentation.base.BaseScreen
import com.aytbyz.enuygun.presentation.base.topbar.ENTopBarConfig
import com.aytbyz.enuygun.presentation.components.topbar.SearchFilterSortTopBar

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val products = viewModel.products.collectAsState().value

    val searchQuery = remember { mutableStateOf("") }

    BaseScreen(
        topBarConfig = ENTopBarConfig(
            showBackButton = false,
            customTopBar = {
                SearchFilterSortTopBar(
                    searchQuery = searchQuery.value,
                    onSearchChange = { searchQuery.value = it },
                    onFilterClick = {},
                    onSortClick = {}
                )
            },
            onBackClick = {}
        )
    ) {
        LazyColumn {
            itemsIndexed(products) { index, product ->
                Text(text = "${index + 1}. ${product.title}")
            }
        }
    }
}