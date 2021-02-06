package com.nnicolosi.theater.services

import com.nnicolosi.theater.domain.Booking
import com.nnicolosi.theater.domain.Performance
import com.nnicolosi.theater.domain.Seat
import com.nnicolosi.theater.repositories.IBookingRepository
import io.kotest.matchers.shouldBe
import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.math.BigDecimal

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookingServiceTests {

    private val bookingRepositoryMock: IBookingRepository = mockk()
    val bookingService = BookingService(bookingRepositoryMock)

    @BeforeEach
    fun init() {
        clearAllMocks()
    }

    @Nested
    inner class FindBooking {
        @Test
        fun `returns a single matching booking from repository`() {
            val seat1A = Seat(1, 'A', 1, BigDecimal(10.00), "Seat 1-A")
            val seat2A = Seat(2, 'A', 2, BigDecimal(10.00), "Seat 2-A")

            val performance1 = Performance(1, "Performance One")
            val performance2 = Performance(2, "Performance Two")

            val bookingOne = Booking(1, "Customer One")
            bookingOne.seat = seat1A
            bookingOne.performance = performance1

            val bookingTwo = Booking(2, "Customer Two")
            bookingTwo.seat = seat2A
            bookingTwo.performance = performance1

            val bookingThree = Booking(3, "Customer Three")
            bookingThree.seat = seat1A
            bookingThree.performance = performance2

            val bookings = listOf(bookingOne, bookingTwo, bookingThree)

            every { bookingRepositoryMock.findAll() } returns bookings

            val result = bookingService.findBooking(seat1A, performance2)

            result?.id shouldBe 3
        }

        @Test
        fun `returns null if matching booking not found`() {
            val seat1A = Seat(1, 'A', 1, BigDecimal(10.00), "Seat 1-A")
            val seat2A = Seat(2, 'A', 2, BigDecimal(10.00), "Seat 2-A")

            val performance1 = Performance(1, "Performance One")
            val performance2 = Performance(2, "Performance Two")

            val bookingOne = Booking(1, "Customer One")
            bookingOne.seat = seat1A
            bookingOne.performance = performance1

            val bookingTwo = Booking(2, "Customer Two")
            bookingTwo.seat = seat2A
            bookingTwo.performance = performance1

            val bookingThree = Booking(3, "Customer Three")
            bookingThree.seat = seat1A
            bookingThree.performance = performance2

            val bookings = listOf(bookingOne, bookingTwo, bookingThree)

            every { bookingRepositoryMock.findAll() } returns bookings

            val result = bookingService.findBooking(seat2A, performance2)

            result shouldBe null
        }
    }

    @Nested
    inner class IsSeatAvailableForBooking {
        @Test
        fun `returns false if booking exists in repository`() {
            val seat1A = Seat(1, 'A', 1, BigDecimal(10.00), "Seat 1-A")

            val performance = Performance(1, "Performance One")

            val bookingOne = Booking(1, "Customer One")
            bookingOne.seat = seat1A
            bookingOne.performance = performance

            val bookings = listOf(bookingOne)

            every { bookingRepositoryMock.findAll() } returns bookings

            val result = bookingService.isSeatAvailableForBooking(seat1A, performance)

            result shouldBe false
        }

        @Test
        fun `returns true if booking does not exist in repository`() {
            val seat1A = Seat(1, 'A', 1, BigDecimal(10.00), "Seat 1-A")
            val seat2A = Seat(2, 'A', 2, BigDecimal(10.00), "Seat 2-A")

            val performance = Performance(1, "Performance One")

            val bookingOne = Booking(1, "Customer One")
            bookingOne.seat = seat1A
            bookingOne.performance = performance

            val bookings = listOf(bookingOne)

            every { bookingRepositoryMock.findAll() } returns bookings

            val result = bookingService.isSeatAvailableForBooking(seat2A, performance)

            result shouldBe true
        }
    }

    @Nested
    inner class CreateBooking {
        @Test
        fun `constructs booking from supplied parameters and saves to repository`() {
            val seat1A = Seat(1, 'A', 1, BigDecimal(10.00), "Seat 1-A")
            val performance = Performance(1, "Performance One")

            val expected = Booking(0, "Customer Name")
            expected.seat = seat1A
            expected.performance = performance

            val bookingCapture = slot<Booking>()

            every { bookingRepositoryMock.save(capture(bookingCapture) ) } answers { bookingCapture.captured }

            bookingService.createBooking(seat1A, performance, "Customer Name")

            bookingCapture.captured shouldBe expected
        }
    }
}