package com.aytbyz.enuygun.domain.usecase

import com.aytbyz.enuygun.domain.model.response.Category
import com.aytbyz.enuygun.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    operator fun invoke(): Flow<List<Category>> = repository.getCategories()
}