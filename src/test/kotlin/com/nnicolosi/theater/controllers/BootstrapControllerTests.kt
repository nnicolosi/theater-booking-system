package com.nnicolosi.theater.controllers

import com.nnicolosi.theater.services.BootstrapService
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beInstanceOf
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.web.servlet.view.RedirectView

class BootstrapControllerTests {

    private val bootstrapServiceMock: BootstrapService = mockk()
    val bootstrapController = BootstrapController(bootstrapServiceMock)

    @Nested
    inner class Initialize {
        @Test
        fun `initialize endpoint should call initialize method of bootstrap service`() {
            every { bootstrapServiceMock.initialize() } returns Unit

            bootstrapController.initialize()

            verify(exactly = 1) { bootstrapServiceMock.initialize() }
        }

        @Test
        fun `initialize endpoint should return redirect to home page`() {
            every { bootstrapServiceMock.initialize() } returns Unit

            val result = bootstrapController.initialize()

            result should beInstanceOf<RedirectView>()
            result.url shouldBe "/"
        }
    }
}