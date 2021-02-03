package com.nnicolosi.theater.services

import com.nnicolosi.theater.repositories.IBookingRepository
import com.nnicolosi.theater.domain.Booking
import com.nnicolosi.theater.domain.Performance
import com.nnicolosi.theater.domain.Seat
import org.springframework.stereotype.Service

@Service
class BookingService (val bookingRepository: IBookingRepository)  {

    fun findBooking(seat: Seat, performance: Performance): Booking? {
        return bookingRepository.findAll().firstOrNull { it.seat == seat && it.performance == performance}
    }

    fun isSeatAvailableForBooking(seat: Seat, performance: Performance): Boolean {
        return findBooking(seat, performance) == null
    }

    fun createBooking(seat: Seat, performance: Performance, customerName: String): Booking {
        val booking = Booking(0, customerName)
        booking.seat = seat
        booking.performance = performance

        return bookingRepository.save(booking)
    }

    fun findAll(): List<Booking> = bookingRepository.findAll()
}