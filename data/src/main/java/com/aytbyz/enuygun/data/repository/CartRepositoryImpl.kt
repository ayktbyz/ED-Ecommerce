package com.aytbyz.enuygun.data.repository

import com.aytbyz.enuygun.data.api.CartApi
import com.aytbyz.enuygun.data.mapper.toDomain
import com.aytbyz.enuygun.domain.model.request.AddToCartRequest
import com.aytbyz.enuygun.domain.model.response.AddToCartResponse
import com.aytbyz.enuygun.domain.repository.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartService: CartApi
) : CartRepository {

    override suspend fun addToCart(request: AddToCartRequest): AddToCartResponse {
        return cartService.addToCart(request).toDomain()
    }
}