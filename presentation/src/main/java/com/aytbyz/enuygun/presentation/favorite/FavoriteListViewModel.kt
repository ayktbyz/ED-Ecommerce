package com.aytbyz.enuygun.presentation.favorite

import androidx.lifecycle.viewModelScope
import com.aytbyz.enuygun.domain.model.response.Product
import com.aytbyz.enuygun.domain.usecase.GetAllFavoritesUseCase
import com.aytbyz.enuygun.domain.usecase.RemoveFavoriteUseCase
import com.aytbyz.enuygun.presentation.base.screen.BaseViewModel
import com.aytbyz.enuygun.presentation.base.screen.model.BaseUIEvent
import com.aytbyz.enuygun.presentation.favorite.intent.FavoriteListIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import android.content.Context
import com.aytbyz.enuygun.domain.usecase.AddToCartLocalUseCase
import com.aytbyz.enuygun.presentation.R
import javax.inject.Inject

@HiltViewModel
class FavoriteListViewModel @Inject constructor(
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
    private val addToCartLocalUseCase: AddToCartLocalUseCase,
    @ApplicationContext private val context: Context
) : BaseViewModel() {

    private val _state = MutableStateFlow<List<Product>>(emptyList())
    val state: StateFlow<List<Product>> = _state

    init {
        getFavorites()
    }

    private fun getFavorites() {
        viewModelScope.launch {
            getAllFavoritesUseCase().collectLatest {
                _state.value = it
            }
        }
    }

    private fun addToCart(product: Product) {
        viewModelScope.launch {
            addToCartLocalUseCase(product)
            sendUiEvent(
                BaseUIEvent.ShowToast(
                    context.getString(R.string.added_to_cart)
                )
            )
        }
    }

    private fun toggleFavorite(product: Product) {
        viewModelScope.launch {
            removeFavoriteUseCase(product)
            sendUiEvent(
                BaseUIEvent.ShowToast(
                    context.getString(R.string.removed_from_favorites)
                )
            )
        }
    }

    fun onIntent(intent: FavoriteListIntent) {
        when (intent) {
            is FavoriteListIntent.OnFavoriteToggle -> {
                toggleFavorite(intent.product)
            }

            is FavoriteListIntent.OnAddToCart -> {
                addToCart(intent.product)
            }
        }
    }
}