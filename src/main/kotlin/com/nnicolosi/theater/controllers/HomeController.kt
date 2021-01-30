package com.nnicolosi.theater.controllers

import com.nnicolosi.theater.repositories.ISeatRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

@Controller
class HomeController {

    @Autowired
    lateinit var seatRepository: ISeatRepository

    @RequestMapping("", method = [RequestMethod.GET])
    fun home(): ModelAndView {
        val bootstrapped = seatRepository.findAll().size > 0

        return ModelAndView("home", mapOf("bootstrapped" to bootstrapped))
    }
}