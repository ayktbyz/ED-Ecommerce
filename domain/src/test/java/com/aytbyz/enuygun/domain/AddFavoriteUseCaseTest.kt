package com.aytbyz.enuygun.domain

import com.aytbyz.enuygun.domain.model.response.Product
import com.aytbyz.enuygun.domain.repository.LocalRepository
import com.aytbyz.enuygun.domain.usecase.AddFavoriteUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AddFavoriteUseCaseTest {

    private lateinit var repository: LocalRepository
    private lateinit var useCase: AddFavoriteUseCase

    @Before
    fun setup() {
        repository = mockk(relaxed = true)
        useCase = AddFavoriteUseCase(repository)
    }

    @Test
    fun `invoke calls addFavorite on repository`() = runTest {
        val product =  Product(
            id = 1,
            title = "Laptop",
            description = "Test laptop",
            price = 3000.0,
            discountPercentage = 5.0,
            rating = 4.0.toFloat(),
            category = "electronics",
            thumbnail = "",
            images = emptyList()
        )
        useCase(product)

        coVerify { repository.addFavorite(product) }

        println("✅AddFavoriteUseCaseTest passed")
    }
}