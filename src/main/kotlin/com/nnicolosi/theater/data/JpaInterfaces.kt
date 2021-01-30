package com.nnicolosi.theater.data

import com.nnicolosi.theater.domain.Booking
import com.nnicolosi.theater.domain.Performance
import com.nnicolosi.theater.domain.Seat
import org.springframework.data.jpa.repository.JpaRepository

interface SeatRepository: JpaRepository<Seat, Long>

interface PerformanceRepository: JpaRepository<Performance, Long>

interface BookingRepository: JpaRepository<Booking, Long>