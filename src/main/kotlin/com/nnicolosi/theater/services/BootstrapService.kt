package com.nnicolosi.theater.services

import com.nnicolosi.theater.domain.Seat
import com.nnicolosi.theater.repositories.ISeatRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class BootstrapService {

    @Autowired
    lateinit var seatRepository: ISeatRepository

    private val hiddenSeats = mutableListOf<Seat>()

    constructor() {

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
                hiddenSeats.add(Seat(0, (row+64).toChar(), num, getPrice(row,num), getDescription(row,num) ))
            }
        }
    }

	val seats
    get() = hiddenSeats.toList()
}
