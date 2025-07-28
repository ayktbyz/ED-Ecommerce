package com.aytbyz.enuygun.domain.repository

import com.aytbyz.enuygun.domain.model.request.AddToCartRequest
import com.aytbyz.enuygun.domain.model.response.AddToCartResponse

interface CartRepository {
    suspend fun addToCart(request: AddToCartRequest): AddToCartResponse
}