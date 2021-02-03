package com.nnicolosi.theater.controllers

import com.nnicolosi.theater.services.SeatService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

@Controller
class HomeController (val seatService: SeatService) {

    @RequestMapping("", method = [RequestMethod.GET])
    fun home(): ModelAndView {
        val bootstrapped = seatService.findAll().isNotEmpty()

        return ModelAndView("home", mapOf("bootstrapped" to bootstrapped))
    }
}