package com.aytbyz.enuygun.domain.di

import com.aytbyz.enuygun.domain.repository.CartRepository
import com.aytbyz.enuygun.domain.repository.CategoryRepository
import com.aytbyz.enuygun.domain.repository.ProductRepository
import com.aytbyz.enuygun.domain.usecase.AddToCartUseCase
import com.aytbyz.enuygun.domain.usecase.GetCategoriesUseCase
import com.aytbyz.enuygun.domain.usecase.GetProductByIdUseCase
import com.aytbyz.enuygun.domain.usecase.GetProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetProductsUseCase(
        repository: ProductRepository
    ): GetProductsUseCase {
        return GetProductsUseCase(repository)
    }

    @Provides
    fun provideGetProductByIdUseCase(
        repository: ProductRepository
    ): GetProductByIdUseCase {
        return GetProductByIdUseCase(repository)
    }

    @Provides
    fun provideAddToCartUseCase(
        cartRepository: CartRepository
    ): AddToCartUseCase = AddToCartUseCase(cartRepository)

    @Provides
    fun provideGetCategoriesUseCase(
        categoryRepository: CategoryRepository
    ): GetCategoriesUseCase = GetCategoriesUseCase(categoryRepository)
}