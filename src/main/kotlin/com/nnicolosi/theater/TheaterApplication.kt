package com.nnicolosi.theater

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TheaterApplication

fun main(args: Array<String>) {
    runApplication<TheaterApplication>(*args)
}
