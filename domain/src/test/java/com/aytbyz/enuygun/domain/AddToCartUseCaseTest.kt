package com.aytbyz.enuygun.domain

import com.aytbyz.enuygun.domain.model.request.AddToCartRequest
import com.aytbyz.enuygun.domain.model.request.CartProductRequest
import com.aytbyz.enuygun.domain.model.response.AddToCartResponse
import com.aytbyz.enuygun.domain.model.response.CartProduct
import com.aytbyz.enuygun.domain.repository.CartRepository
import com.aytbyz.enuygun.domain.usecase.AddToCartUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AddToCartUseCaseTest {

    private lateinit var repository: CartRepository
    private lateinit var useCase: AddToCartUseCase

    @Before
    fun setup() {
        repository = mockk()
        useCase = AddToCartUseCase(repository)
    }

    @Test
    fun `invoke returns correct AddToCartResponse`() = runTest {
        val request = AddToCartRequest(
            userId = 1,
            products = listOf(
                CartProductRequest(
                    id = 101,
                    quantity = 2,
                ),
                CartProductRequest(
                    id = 101,
                    quantity = 3,
                ),
            )
        )

        val expectedResponse = AddToCartResponse(
            id = 999,
            userId = 1,
            products = request.products.map {
                CartProduct(
                    id = it.id,
                    quantity = it.quantity
                )
            },
            total = 350.0,
            discountedTotal = 330.0
        )

        coEvery { repository.addToCart(request) } returns expectedResponse

        val result = useCase(request)

        assertEquals(expectedResponse, result)

        println("✅ AddToCartUseCaseTest passed")
    }
}