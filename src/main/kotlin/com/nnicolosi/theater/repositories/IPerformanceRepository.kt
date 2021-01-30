package com.nnicolosi.theater.repositories

import com.nnicolosi.theater.domain.Performance
import org.springframework.data.jpa.repository.JpaRepository

interface IPerformanceRepository: JpaRepository<Performance, Long>
