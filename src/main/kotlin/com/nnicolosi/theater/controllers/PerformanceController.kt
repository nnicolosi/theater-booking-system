package com.nnicolosi.theater.controllers

import com.nnicolosi.theater.repositories.IPerformanceRepository
import com.nnicolosi.theater.domain.Performance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.view.RedirectView

@Controller
@RequestMapping("/performances")
class PerformanceController() {

    @Autowired
    lateinit var performanceRepository: IPerformanceRepository

    @RequestMapping("")
    fun performancesHomePage() =
            ModelAndView("performances/list","performances", performanceRepository.findAll())

    @RequestMapping("/add")
    fun addPerformance() =
            ModelAndView("performances/add","performance", Performance(0,""))

    @RequestMapping("save", method = arrayOf(RequestMethod.POST))
    fun savePerformance(performance: Performance) : RedirectView {
        performanceRepository.save(performance)
        return RedirectView("/performances")
    }
}