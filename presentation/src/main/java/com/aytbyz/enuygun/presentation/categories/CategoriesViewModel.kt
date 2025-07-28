package com.aytbyz.enuygun.presentation.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aytbyz.enuygun.domain.usecase.GetCategoriesUseCase
import com.aytbyz.enuygun.presentation.categories.model.CategoriesUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.update

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CategoriesUIState())
    val state: StateFlow<CategoriesUIState> = _state

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            getCategoriesUseCase()
                .collect { categories ->
                    _state.update {
                        it.copy(categories = categories)
                    }
                }
        }
    }
}