package com.aytbyz.enuygun.data.local.di

import android.content.Context
import androidx.room.Room
import com.aytbyz.enuygun.data.local.EnuygunDatabase
import com.aytbyz.enuygun.data.local.dao.ProductDao
import com.aytbyz.enuygun.data.repository.LocalRepositoryImpl
import com.aytbyz.enuygun.domain.repository.LocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): EnuygunDatabase {
        return Room.databaseBuilder(
            context,
            EnuygunDatabase::class.java,
            "product_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideProductDao(database: EnuygunDatabase): ProductDao {
        return database.productDao()
    }

    @Provides
    @Singleton
    fun provideLocalRepository(dao: ProductDao): LocalRepository {
        return LocalRepositoryImpl(dao)
    }
}