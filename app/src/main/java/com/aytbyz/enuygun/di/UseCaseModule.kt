package com.aytbyz.enuygun.di

import com.aytbyz.enuygun.domain.repository.ProductRepository
import com.aytbyz.enuygun.domain.usecase.GetAllProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetAllProductsUseCase(
        repository: ProductRepository
    ): GetAllProductsUseCase {
        return GetAllProductsUseCase(repository)
    }
}