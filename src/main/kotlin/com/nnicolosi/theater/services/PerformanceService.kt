package com.nnicolosi.theater.services

import com.nnicolosi.theater.domain.Performance
import com.nnicolosi.theater.repositories.IPerformanceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PerformanceService {

    @Autowired
    lateinit var performanceRepository: IPerformanceRepository

    fun findAll(): List<Performance> = performanceRepository.findAll()

    fun findById(id: Long): Optional<Performance> = performanceRepository.findById(id)

    fun save(performance: Performance): Performance = performanceRepository.save(performance)
}