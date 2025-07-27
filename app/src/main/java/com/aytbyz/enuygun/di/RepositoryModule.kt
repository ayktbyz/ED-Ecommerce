package com.aytbyz.enuygun.di

import com.aytbyz.enuygun.data.api.ProductApi
import com.aytbyz.enuygun.data.repository.ProductRepositoryImpl
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
}