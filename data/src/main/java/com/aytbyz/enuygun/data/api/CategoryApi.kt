package com.aytbyz.enuygun.data.api

import com.aytbyz.enuygun.data.dto.CategoryDto
import retrofit2.http.GET

interface CategoryApi {
    @GET("products/categories")
    suspend fun getCategories(): List<CategoryDto>
}