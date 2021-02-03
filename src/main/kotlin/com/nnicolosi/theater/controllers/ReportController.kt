package com.nnicolosi.theater.controllers

import com.nnicolosi.theater.services.ReportService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import javax.websocket.server.PathParam
import kotlin.reflect.full.declaredMemberFunctions

@Controller
@RequestMapping("/reports")
class ReportController (val reportService: ReportService) {

    private fun getListOfReports() = reportService::class.declaredMemberFunctions.map { it.name }

    @RequestMapping("")
    fun main() = ModelAndView("reports", mapOf("reports" to getListOfReports()))

    @RequestMapping("/getReport")
    fun getReport(@PathParam("report") report: String): ModelAndView {
        val matchedReport = reportService::class.declaredMemberFunctions.filter { it.name == report }.firstOrNull()
        val result = matchedReport?.call(reportService) ?: ""

        return ModelAndView("reports", mapOf("reports" to getListOfReports(), "result" to result))
    }
}