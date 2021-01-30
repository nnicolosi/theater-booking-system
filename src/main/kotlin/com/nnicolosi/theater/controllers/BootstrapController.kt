package com.nnicolosi.theater.controllers

import com.nnicolosi.theater.repositories.ISeatRepository
import com.nnicolosi.theater.services.BootstrapService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.view.RedirectView

@Controller()
@RequestMapping("/bootstrap")
class BootstrapController {

    @Autowired
    lateinit var bootstrapService: BootstrapService

    @Autowired
    lateinit var seatRepository: ISeatRepository

    @RequestMapping("/initialize")
    fun initialize(): RedirectView {
        if (seatRepository.findAll().size == 0) {
            val seats = bootstrapService.seats
            seatRepository.saveAll(seats)
        }

        return RedirectView("/")
    }
}