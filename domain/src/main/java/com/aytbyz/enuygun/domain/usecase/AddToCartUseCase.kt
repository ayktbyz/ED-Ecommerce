package com.aytbyz.enuygun.domain.usecase

import com.aytbyz.enuygun.domain.model.request.AddToCartRequest
import com.aytbyz.enuygun.domain.model.response.AddToCartResponse
import com.aytbyz.enuygun.domain.repository.CartRepository

class AddToCartUseCase(
    private val repository: CartRepository
) {
    suspend operator fun invoke(request: AddToCartRequest): AddToCartResponse {
        return repository.addToCart(request)
    }
}