package com.nnicolosi.theater.repositories

import com.nnicolosi.theater.domain.Booking
import org.springframework.data.jpa.repository.JpaRepository

interface IBookingRepository: JpaRepository<Booking, Long>
