package com.nnicolosi.theater.services

import com.nnicolosi.theater.domain.Seat
import com.nnicolosi.theater.repositories.ISeatRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SeatService {

    @Autowired
    lateinit var seatRepository: ISeatRepository

    fun findSeat(seatNum: Int, seatRow: Char): Seat? {
        return findAll().firstOrNull { it.seatNum == seatNum && it.rowId == seatRow }
    }

    fun findAll(): List<Seat> = seatRepository.findAll()

    fun saveAll(seats: List<Seat>): List<Seat> = seatRepository.saveAll(seats)
}