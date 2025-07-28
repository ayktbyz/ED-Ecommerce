package com.aytbyz.enuygun.domain.repository

import com.aytbyz.enuygun.domain.model.response.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(): Flow<List<Category>>
}