package com.nnicolosi.theater.services

import com.nnicolosi.theater.domain.Performance
import com.nnicolosi.theater.domain.Seat
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class BootstrapService (
    val seatService: SeatService,
    val performanceService: PerformanceService
        ) {

    fun initialize() {
        if (seatService.findAll().isEmpty()) {
            seatService.saveAll(generateSeats())
        }
        if (performanceService.findAll().isEmpty()) {
            performanceService.saveAll(generatePerformances())
        }
    }

    fun generateSeats(): List<Seat> {
        val seats = mutableListOf<Seat>()

        fun getPrice(row: Int, num: Int) : BigDecimal {
            return when {
                row >=14 -> BigDecimal(14.50)
                num <=3 || num >= 34 -> BigDecimal(16.50)
                row == 1 -> BigDecimal(21)
                else -> BigDecimal(18)
            }

        }

        fun getDescription(row: Int, num: Int) : String {
            return when {
                row == 15 -> "Back Row"
                row == 14 -> "Cheaper Seat"
                num <=3 || num >= 34 -> "Restricted View"
                row <=2 -> "Best View"
                else -> "Standard Seat"
            }
        }

        for (row in 1..15) {
            for (num in 1..36) {
                seats.add(Seat(0, (row+64).toChar(), num, getPrice(row,num), getDescription(row,num) ))
            }
        }

        return seats
    }

    fun generatePerformances(): List<Performance> {
        return listOf(
            Performance(0, "Hamilton"),
            Performance(0, "Pirates of Penzance"),
            Performance(0, "West Side Story")
        )
    }
}
