package com.aytbyz.enuygun.data.repository

import com.aytbyz.enuygun.data.api.CategoryApi
import com.aytbyz.enuygun.data.mapper.toDomain
import com.aytbyz.enuygun.domain.model.response.Category
import com.aytbyz.enuygun.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val apiService: CategoryApi
) : CategoryRepository {

    override fun getCategories(): Flow<List<Category>> = flow {
        val dtoList = apiService.getCategories()
        val domainList = dtoList.map { it.toDomain() }
        emit(domainList)
    }
}