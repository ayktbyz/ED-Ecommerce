package com.aytbyz.enuygun.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aytbyz.enuygun.presentation.base.screen.BaseScreen
import com.aytbyz.enuygun.presentation.base.topbar.ENTopBarConfig
import com.aytbyz.enuygun.presentation.components.product.ProductCard
import com.aytbyz.enuygun.presentation.components.topbar.SearchFilterSortTopBar
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.graphics.Color
import com.aytbyz.enuygun.presentation.components.bottomsheet.ProductFilterBottomSheet
import com.aytbyz.enuygun.presentation.home.intent.HomeIntent

@Composable
fun HomeScreen(
    goToProductDetail: (Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value

    if (uiState.showSortBottomSheet) {
        ProductFilterBottomSheet(
            selected = uiState.selectedSortOption,
            onSelect = {
                viewModel.onIntent(HomeIntent.OnSortSelected(it))
            },
            onDismiss = { viewModel.dismissSortBottomSheet() }
        )
    }

    BaseScreen(
        topBarConfig = ENTopBarConfig(
            showBackButton = false,
            customTopBar = {
                SearchFilterSortTopBar(
                    searchQuery = uiState.searchQuery,
                    onSearchChange = {
                        viewModel.onIntent(HomeIntent.OnSearchChange(it))
                    },
                    onSortClick = {
                        viewModel.onIntent(HomeIntent.OnSortClick)
                    }
                )
            },
            onBackClick = {}
        )
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.background(Color.White)
                .padding(horizontal = 4.dp),
            contentPadding = PaddingValues(2.dp)
        ) {
            items(uiState.products) { product ->
                ProductCard(
                    product = product,
                    modifier = Modifier.padding(2.dp),
                    isFavorite = viewModel.isFavorite,
                    onFavoriteClick = {
                        viewModel.toggleFavorite()
                    },
                    onClick = {
                        goToProductDetail(product.id)
                    }
                )
            }
        }
    }
}