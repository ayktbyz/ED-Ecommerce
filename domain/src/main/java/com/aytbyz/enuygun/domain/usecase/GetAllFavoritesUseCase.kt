package com.aytbyz.enuygun.domain.usecase

import com.aytbyz.enuygun.domain.model.response.Product
import com.aytbyz.enuygun.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFavoritesUseCase @Inject constructor(
    private val repository: LocalRepository
) {
    operator fun invoke(): Flow<List<Product>> {
        return repository.getAllFavorites()
    }
}