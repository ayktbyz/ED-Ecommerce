package com.aytbyz.enuygun.domain.usecase

import com.aytbyz.enuygun.domain.model.response.Product
import com.aytbyz.enuygun.domain.repository.LocalRepository
import javax.inject.Inject

class RemoveFavoriteUseCase @Inject constructor(
    private val repository: LocalRepository
) {
    suspend operator fun invoke(product: Product) {
        repository.removeFavorite(product)
    }
}