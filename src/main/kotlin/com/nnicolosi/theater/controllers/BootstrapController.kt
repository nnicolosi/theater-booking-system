package com.nnicolosi.theater.controllers

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

    @RequestMapping("/initialize")
    fun initialize(): RedirectView {
        bootstrapService.initialize()

        return RedirectView("/")
    }
}