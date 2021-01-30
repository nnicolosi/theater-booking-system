package com.nnicolosi.theater.domain

import javax.persistence.*

@Entity
data class Performance(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val title: String) {

    @OneToMany(mappedBy = "performance")
    lateinit var bookings: List<Booking>
}