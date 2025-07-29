package com.aytbyz.enuygun.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.aytbyz.enuygun.presentation.R
import com.aytbyz.enuygun.presentation.components.bottomsheet.ProductFilterBottomSheet
import com.aytbyz.enuygun.presentation.components.bottomsheet.ProductSortBottomSheet
import com.aytbyz.enuygun.presentation.home.intent.HomeIntent

@Composable
fun HomeScreen(
    goToProductDetail: (Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value
    val filterState = viewModel.filter.collectAsState().value

    val gridState = rememberLazyGridState()

    LaunchedEffect(Unit) {
        snapshotFlow { gridState.isScrollInProgress }
            .collect {
                viewModel.refreshFavorites()
            }
    }

    LaunchedEffect(uiState.products, uiState.isLoadingMore) {
        snapshotFlow { gridState.layoutInfo }
            .collect { layoutInfo ->
                val totalItems = layoutInfo.totalItemsCount
                val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

                val viewportEnd = layoutInfo.viewportEndOffset
                val lastItemBottom = layoutInfo.visibleItemsInfo.lastOrNull()?.offset?.y?.plus(
                    layoutInfo.visibleItemsInfo.lastOrNull()?.size?.height ?: 0
                ) ?: 0

                val isScreenFilled = totalItems > 0 && lastItemBottom >= viewportEnd

                if (!isScreenFilled && uiState.canLoadMore && !uiState.isLoadingMore) {
                    viewModel.onIntent(HomeIntent.LoadMore)
                }

                else if (lastVisibleItem >= totalItems - 4 && uiState.canLoadMore && !uiState.isLoadingMore) {
                    viewModel.onIntent(HomeIntent.LoadMore)
                }
            }
    }

    if (uiState.showFilterBottomSheet) {
        ProductFilterBottomSheet(
            filterState = filterState,
            onApply = {
                viewModel.onIntent(HomeIntent.OnApplyFilter(it))
            },
            onClear = {
                viewModel.onIntent(HomeIntent.OnClearFilter)
            },
            onDismiss = {
                viewModel.onIntent(HomeIntent.OnDismissFilterBottomSheet)
            }
        )
    }

    if (uiState.showSortBottomSheet) {
        ProductSortBottomSheet(
            selected = uiState.selectedSortOption,
            onSelect = {
                viewModel.onIntent(HomeIntent.OnSortSelected(it))
            },
            onDismiss = {
                viewModel.onIntent(HomeIntent.OnDismissSortBottomSheet)
            }
        )
    }

    BaseScreen(
        eventFlow = viewModel.eventFlow,
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
                    },
                    onFilterClick = {
                        viewModel.onIntent(HomeIntent.OnFilterClick)
                    }
                )
            },
            onBackClick = {}
        )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 14.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val fullText = stringResource(id = R.string.product_count_label, uiState.total)

                Text(
                    buildAnnotatedString {
                        val boldPart = fullText.substringBefore(" ")
                        val normalPart = fullText.removePrefix("$boldPart ")

                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("$boldPart ")
                        }
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                            append(normalPart)
                        }
                    },
                    style = MaterialTheme.typography.titleMedium
                )
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                state = gridState,
                modifier = Modifier
                    .background(Color.White)
                    .padding(horizontal = 4.dp),
                contentPadding = PaddingValues(2.dp)
            ) {
                items(uiState.products) { product ->
                    ProductCard(
                        product = product,
                        modifier = Modifier.padding(2.dp),
                        isFavorite = product.isFavorite,
                        onFavoriteClick = {
                            viewModel.onIntent(HomeIntent.OnFavoriteToggle(product))
                        },
                        onClick = {
                            goToProductDetail(product.id)
                        }
                    )
                }
            }
        }
    }
}