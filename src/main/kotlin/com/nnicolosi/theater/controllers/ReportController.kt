package com.nnicolosi.theater.controllers

import com.nnicolosi.theater.services.ReportService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import javax.websocket.server.PathParam
import kotlin.reflect.full.declaredMemberFunctions

@Controller
@RequestMapping("/reports")
class ReportController {

    @Autowired
    lateinit var reportingService: ReportService

    private fun getListOfReports() = reportingService::class.declaredMemberFunctions.map { it.name }

    @RequestMapping("")
    fun main() = ModelAndView("reports", mapOf("reports" to getListOfReports()))

    @RequestMapping("/getReport")
    fun getReport(@PathParam("report") report: String): ModelAndView {
        val matchedReport = reportingService::class.declaredMemberFunctions.filter { it.name == report }.firstOrNull()
        val result = matchedReport?.call(reportingService) ?: ""

        return ModelAndView("reports", mapOf("reports" to getListOfReports(), "result" to result))
    }
}