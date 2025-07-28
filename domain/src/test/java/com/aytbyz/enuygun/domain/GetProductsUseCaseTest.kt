package com.aytbyz.enuygun.domain

import app.cash.turbine.test
import com.aytbyz.enuygun.domain.model.ProductPage
import com.aytbyz.enuygun.domain.model.response.Product
import com.aytbyz.enuygun.domain.repository.ProductRepository
import com.aytbyz.enuygun.domain.usecase.GetProductsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@OptIn(ExperimentalCoroutinesApi::class)
class GetProductsUseCaseTest {

    private lateinit var repository: ProductRepository
    private lateinit var useCase: GetProductsUseCase

    @Before
    fun setup() {
        repository = mockk()
        useCase = GetProductsUseCase(repository)
    }

    @Test
    fun `invoke returns product page correctly`() = runTest {
        val fakeProducts = listOf(
            Product(
                id = 1,
                title = "Phone",
                description = "Test phone",
                price = 1000.0,
                discountPercentage = 10.0,
                rating = 4.5.toFloat(),
                category = "electronics",
                thumbnail = "",
                images = emptyList()
            ),
            Product(
                id = 2,
                title = "Laptop",
                description = "Test laptop",
                price = 3000.0,
                discountPercentage = 5.0,
                rating = 4.0.toFloat(),
                category = "electronics",
                thumbnail = "",
                images = emptyList()
            )
        )

        val expectedPage = ProductPage(
            products = fakeProducts,
            total = 2,
            skip = 0,
            limit = 30
        )

        coEvery {
            repository.getProducts(any(), any(), any(), any(), any())
        } returns flowOf(expectedPage)

        val flow = useCase()

        flow.test {
            val result = awaitItem()
            assertEquals(expectedPage, result)

            println("✅ TEST PASSED")
            println("Returned ProductPage: $result")

            awaitComplete()
        }
    }
}