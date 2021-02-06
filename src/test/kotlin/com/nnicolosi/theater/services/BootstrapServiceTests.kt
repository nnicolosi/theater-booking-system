package com.nnicolosi.theater.services

import com.nnicolosi.theater.domain.Performance
import com.nnicolosi.theater.domain.Seat
import io.kotest.matchers.shouldBe
import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.math.BigDecimal

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BootstrapServiceTests {
    private val seatService: SeatService = mockk()
    private val performanceService: PerformanceService = mockk()
    val bootstrapService = BootstrapService(seatService, performanceService)

    @BeforeEach
    fun init() {
        clearAllMocks()
    }

    @Nested
    inner class Initialize {
        @Test
        fun `does not initialize seats if any already exist`() {
            every { seatService.findAll() } returns listOf(Seat(1, 'A', 1, BigDecimal(10.00), "Seat 1-A"))
            every { performanceService.findAll() } returns listOf(Performance(1, "Performance"))

            bootstrapService.initialize()

            verify(exactly = 1) { seatService.findAll() }
            verify(exactly = 0) { seatService.saveAll(any()) }
        }

        @Test
        fun `does not initialize performances if any already exist`() {
            every { seatService.findAll() } returns listOf(Seat(1, 'A', 1, BigDecimal(10.00), "Seat 1-A"))
            every { performanceService.findAll() } returns listOf(Performance(1, "Performance"))

            bootstrapService.initialize()

            verify(exactly = 1) { performanceService.findAll() }
            verify(exactly = 0) { performanceService.saveAll(any()) }
        }

        @Test
        fun `creates seats (15 rows of 36 seats each for a total of 540 seats)`() {
            every { seatService.findAll() } returns emptyList()
            every { performanceService.findAll() } returns emptyList()

            val seats = slot<List<Seat>>()
            every { seatService.saveAll(capture(seats) ) } answers { seats.captured }

            val performances = slot<List<Performance>>()
            every { performanceService.saveAll(capture(performances) ) } answers { performances.captured }

            bootstrapService.initialize()

            seats.captured.size shouldBe 540
        }

        @Test
        fun `creates performances (3)`() {
            every { seatService.findAll() } returns emptyList()
            every { performanceService.findAll() } returns emptyList()

            val seats = slot<List<Seat>>()
            every { seatService.saveAll(capture(seats) ) } answers { seats.captured }

            val performances = slot<List<Performance>>()
            every { performanceService.saveAll(capture(performances) ) } answers { performances.captured }

            bootstrapService.initialize()

            performances.captured.size shouldBe 3
        }
    }
}