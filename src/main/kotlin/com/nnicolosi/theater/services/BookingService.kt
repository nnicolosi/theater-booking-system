package com.nnicolosi.theater.services

import com.nnicolosi.theater.data.BookingRepository
import com.nnicolosi.theater.data.SeatRepository
import com.nnicolosi.theater.domain.Booking
import com.nnicolosi.theater.domain.Performance
import com.nnicolosi.theater.domain.Seat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BookingService()  {

    @Autowired
    lateinit var bookingRepository: BookingRepository

    @Autowired
    lateinit var seatRepository: SeatRepository

    fun findSeat(seatNum: Int, seatRow: Char): Seat? {
        return seatRepository.findAll().firstOrNull { it.seatNum == seatNum && it.rowId == seatRow }
    }

    fun findBooking(seat: Seat, performance: Performance): Booking? {
        return bookingRepository.findAll().firstOrNull { it.seat == seat && it.performance == performance}
    }

    fun isSeatFree(seat: Seat, performance: Performance): Boolean {
        return findBooking(seat, performance) == null
    }

    fun reserveSeat(seat: Seat, performance: Performance, customerName: String): Booking {
        val booking = Booking(0, customerName)
        booking.seat = seat
        booking.performance = performance

        return bookingRepository.save(booking)
    }
}