package com.nnicolosi.theater.repositories

import com.nnicolosi.theater.domain.Seat
import org.springframework.data.jpa.repository.JpaRepository

interface ISeatRepository: JpaRepository<Seat, Long>