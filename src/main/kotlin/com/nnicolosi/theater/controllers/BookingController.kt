package com.nnicolosi.theater.controllers

import com.nnicolosi.theater.domain.Booking
import com.nnicolosi.theater.domain.Performance
import com.nnicolosi.theater.domain.Seat
import com.nnicolosi.theater.services.BookingService
import com.nnicolosi.theater.services.PerformanceService
import com.nnicolosi.theater.services.SeatService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/booking")
class BookingController {

    @Autowired
    lateinit var bookingService: BookingService

    @Autowired
    lateinit var seatService: SeatService

    @Autowired
    lateinit var performanceService: PerformanceService

    @RequestMapping("", method = [RequestMethod.GET])
    fun begin(): ModelAndView {
        val model = mapOf(
            "bean" to CheckAvailabilityBackingBean(),
            "performances" to performanceService.findAll(),
            "seatNums" to 1..36,
            "seatRows" to 'A'..'O'
        )

        return ModelAndView("booking/create", model)
    }

    @RequestMapping("checkAvailability", method = [RequestMethod.POST])
    fun checkAvailability(bean: CheckAvailabilityBackingBean): ModelAndView {
        val selectedSeat: Seat = seatService.findSeat(bean.selectedSeatNum, bean.selectedSeatRow)!!
        val selectedPerformance = performanceService.findById(bean.selectedPerformance!!).get()

        bean.seat = selectedSeat
        bean.performance = selectedPerformance

        val isAvailable = bookingService.isSeatAvailableForBooking(selectedSeat, selectedPerformance)

        if (!isAvailable) {
            bean.booking = bookingService.findBooking(selectedSeat, selectedPerformance)
        }

        bean.available = isAvailable

        val model = mapOf(
            "bean" to bean,
            "performances" to performanceService.findAll(),
            "seatNums" to 1..36,
            "seatRows" to 'A'..'O'
        )

        return ModelAndView("booking/create", model)
    }

    @RequestMapping("", method = [RequestMethod.POST])
    fun createBooking(bean: CheckAvailabilityBackingBean): ModelAndView {
        val booking = bookingService.createBooking(bean.seat!!, bean.performance!!, bean.customerName)

        return ModelAndView("booking/confirmation", "booking", booking)
    }
}


class CheckAvailabilityBackingBean() {
    var selectedSeatNum: Int = 1
    var selectedSeatRow: Char = 'A'
    var selectedPerformance: Long? = null
    var customerName: String = ""

    var available: Boolean? = null
    var seat: Seat? = null
    var performance: Performance? = null
    var booking: Booking? = null
}