package com.aytbyz.enuygun.domain

import com.aytbyz.enuygun.domain.repository.LocalRepository
import com.aytbyz.enuygun.domain.usecase.UpdateCartItemQuantityUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class UpdateCartItemQuantityUseCaseTest {

    private lateinit var repository: LocalRepository
    private lateinit var useCase: UpdateCartItemQuantityUseCase

    @Before
    fun setup() {
        repository = mockk(relaxed = true)
        useCase = UpdateCartItemQuantityUseCase(repository)
    }

    @Test
    fun `invoke calls repository with correct parameters`() = runTest {
        val productId = 101
        val quantity = 5

        useCase(productId, quantity)

        coVerify { repository.updateCartItemQuantity(productId, quantity) }
        println("✅ UpdateCartItemQuantityUseCaseTest passed")
    }
}