package com.aytbyz.enuygun.domain.usecase

import com.aytbyz.enuygun.domain.repository.LocalRepository
import javax.inject.Inject

class UpdateCartItemQuantityUseCase @Inject constructor(
    private val repository: LocalRepository
) {
    suspend operator fun invoke(productId: Int, quantity: Int) {
        repository.updateCartItemQuantity(productId, quantity)
    }
}