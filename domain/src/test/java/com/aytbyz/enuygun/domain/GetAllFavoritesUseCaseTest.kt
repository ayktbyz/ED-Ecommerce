package com.aytbyz.enuygun.domain

import app.cash.turbine.test
import com.aytbyz.enuygun.domain.model.response.Product
import com.aytbyz.enuygun.domain.repository.LocalRepository
import com.aytbyz.enuygun.domain.usecase.GetAllFavoritesUseCase
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetAllFavoritesUseCaseTest {

    private lateinit var repository: LocalRepository
    private lateinit var useCase: GetAllFavoritesUseCase

    @Before
    fun setup() {
        repository = mockk()
        useCase = GetAllFavoritesUseCase(repository)
    }

    @Test
    fun `invoke returns favorite products correctly`() = runTest {
        val dummyProducts = listOf(
            Product(
                id = 1,
                title = "Laptop",
                description = "Gaming laptop",
                category = "electronics",
                thumbnail = "url",
                price = 1500.0,
                images = listOf("img1"),
                rating = 4.5f,
                discountPercentage = 10.0,
                isFavorite = true
            ),
            Product(
                id = 2,
                title = "Phone",
                description = "Smartphone",
                category = "electronics",
                thumbnail = "url2",
                price = 800.0,
                images = listOf("img2"),
                rating = 4.3f,
                discountPercentage = 5.0,
                isFavorite = true
            )
        )

        every { repository.getAllFavorites() } returns flowOf(dummyProducts)

        val result = useCase()

        result.test {
            val items = awaitItem()
            assertEquals(dummyProducts, items)
            awaitComplete()
            println("✅ GetAllFavoritesUseCaseTest passed")
        }
    }
}