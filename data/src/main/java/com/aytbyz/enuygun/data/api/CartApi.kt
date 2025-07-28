package com.aytbyz.enuygun.data.api

import com.aytbyz.enuygun.data.dto.AddToCartResponseDto
import com.aytbyz.enuygun.domain.model.request.AddToCartRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface CartApi {
    @POST("carts/add")
    suspend fun addToCart(
        @Body request: AddToCartRequest
    ): AddToCartResponseDto
}