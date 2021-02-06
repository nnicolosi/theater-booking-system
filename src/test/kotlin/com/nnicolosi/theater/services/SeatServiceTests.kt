package com.nnicolosi.theater.services

import com.nnicolosi.theater.domain.Seat
import com.nnicolosi.theater.repositories.ISeatRepository
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.math.BigDecimal

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SeatServiceTests {

    private val seatRepositoryMock: ISeatRepository = mockk()
    val seatService = SeatService(seatRepositoryMock)

    @BeforeEach
    fun init() {
        clearAllMocks()
    }

    @Nested
    inner class FindSeat {
        @Test
        fun `returns a single matching seat from repository`() {
            val seats: List<Seat> = listOf(
                Seat(1, 'A', 1, BigDecimal(10.00), "Seat 1-A"),
                Seat(2, 'A', 1, BigDecimal(10.00), "Seat 1-A"),
                Seat(3, 'A', 2, BigDecimal(10.00), "Seat 2-A")
            )

            every { seatRepositoryMock.findAll() } returns seats

            val result = seatService.findSeat(1, 'A')

            result?.seatNum shouldBe 1
            result?.rowId shouldBe 'A'
        }

        @Test
        fun `returns null if matching seat not found`() {
            val seats: List<Seat> = listOf(
                Seat(1, 'A', 1, BigDecimal(10.00), "Seat 1-A"),
                Seat(2, 'A', 2, BigDecimal(10.00), "Seat 2-A")
            )

            every { seatRepositoryMock.findAll() } returns seats

            val result = seatService.findSeat(3, 'A')

            result shouldBe  null
        }
    }
}
