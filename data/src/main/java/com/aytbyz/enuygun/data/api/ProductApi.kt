package com.aytbyz.enuygun.data.api

import com.aytbyz.enuygun.data.dto.ProductResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {
    @GET("products/search")
    suspend fun searchProducts(
        @Query("q") query: String,
        @Query("sortBy") sortBy: String? = null,
        @Query("order") order: String? = null
    ): ProductResponseDto

    @GET("products")
    suspend fun getProducts(
        @Query("sortBy") sortBy: String? = null,
        @Query("order") order: String? = null
    ): ProductResponseDto
}