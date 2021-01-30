package com.nnicolosi.theater.domain

import javax.persistence.*

@Entity
data class Booking(
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    val id: Long,
    val customerName: String) {

    @ManyToOne
    lateinit var seat: Seat

    @ManyToOne
    lateinit var performance: Performance
}