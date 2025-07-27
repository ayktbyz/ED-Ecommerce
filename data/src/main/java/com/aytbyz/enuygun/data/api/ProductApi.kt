package com.aytbyz.enuygun.data.api

import com.aytbyz.enuygun.data.dto.ProductResponseDto
import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    suspend fun getProducts(): ProductResponseDto
}