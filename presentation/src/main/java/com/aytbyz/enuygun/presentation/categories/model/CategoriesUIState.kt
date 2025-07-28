package com.aytbyz.enuygun.presentation.categories.model

import com.aytbyz.enuygun.domain.model.response.Category

data class CategoriesUIState(
    val categories: List<Category> = emptyList()
)