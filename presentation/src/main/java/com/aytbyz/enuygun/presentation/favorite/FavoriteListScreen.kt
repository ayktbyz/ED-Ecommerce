package com.aytbyz.enuygun.presentation.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aytbyz.enuygun.presentation.R
import com.aytbyz.enuygun.presentation.base.screen.BaseScreen
import com.aytbyz.enuygun.presentation.base.topbar.ENTopBarConfig
import com.aytbyz.enuygun.presentation.components.cateogory.CategoryItem
import com.aytbyz.enuygun.presentation.components.product.ProductCard
import com.aytbyz.enuygun.presentation.favorite.intent.FavoriteListIntent

@Composable
fun FavoriteListScreen(
    viewModel: FavoriteListViewModel = hiltViewModel()
) {
    val favoriteProducts = viewModel.state.collectAsState().value

    BaseScreen(
        eventFlow = viewModel.eventFlow,
        topBarConfig = ENTopBarConfig(
            title = stringResource(R.string.favorites),
            showBackButton = false,
            onBackClick = {}
        )
    ) {
        if (favoriteProducts.isEmpty()) {
            Text(
                text = stringResource(R.string.empty_favorite_list),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            )
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(favoriteProducts) { product ->
                    ProductCard(
                        product = product,
                        isFavorite = true,
                        onFavoriteClick = {
                            viewModel.onIntent(FavoriteListIntent.OnFavoriteToggle(product))
                        },
                        isAddButton = true,
                        onAddClick = {
                            viewModel.onIntent(FavoriteListIntent.OnAddToCart(product))
                        }
                    )
                }
            }
        }
    }
}