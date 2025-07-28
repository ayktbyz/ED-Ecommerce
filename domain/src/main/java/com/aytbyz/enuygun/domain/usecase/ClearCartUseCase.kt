package com.aytbyz.enuygun.domain.usecase

import com.aytbyz.enuygun.domain.repository.LocalRepository
import javax.inject.Inject

class ClearCartUseCase @Inject constructor(
    private val repository: LocalRepository
) {
    suspend operator fun invoke() {
        repository.clearCart()
    }
}