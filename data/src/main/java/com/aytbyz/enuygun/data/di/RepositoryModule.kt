package com.aytbyz.enuygun.data.di

import com.aytbyz.enuygun.data.api.CartApi
import com.aytbyz.enuygun.data.api.CategoryApi
import com.aytbyz.enuygun.data.api.ProductApi
import com.aytbyz.enuygun.data.repository.CartRepositoryImpl
import com.aytbyz.enuygun.data.repository.CategoryRepositoryImpl
import com.aytbyz.enuygun.data.repository.ProductRepositoryImpl
import com.aytbyz.enuygun.domain.repository.CartRepository
import com.aytbyz.enuygun.domain.repository.CategoryRepository
import com.aytbyz.enuygun.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideProductRepository(api: ProductApi): ProductRepository {
        return ProductRepositoryImpl(api)
    }

    @Provides
    fun provideCartRepository(api: CartApi): CartRepository {
        return CartRepositoryImpl(api)
    }

    @Provides
    fun provideCategoryRepositoryImpl(api: CategoryApi): CategoryRepository {
        return CategoryRepositoryImpl(api)
    }
}