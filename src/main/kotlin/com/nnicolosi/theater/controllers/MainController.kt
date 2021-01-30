package com.nnicolosi.theater.controllers

import com.nnicolosi.theater.repositories.IPerformanceRepository
import com.nnicolosi.theater.repositories.ISeatRepository
import com.nnicolosi.theater.domain.Booking
import com.nnicolosi.theater.domain.Performance
import com.nnicolosi.theater.domain.Seat
import com.nnicolosi.theater.services.BookingService
import com.nnicolosi.theater.services.TheaterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

@Controller
class MainController {

    @Autowired
    lateinit var bookingService: BookingService

    @Autowired
    lateinit var theaterService: TheaterService

    @Autowired
    lateinit var seatRepository: ISeatRepository

    @Autowired
    lateinit var performanceRepository: IPerformanceRepository

    @RequestMapping("")
    fun homePage(): ModelAndView {
        val model = mapOf(
            "bean" to CheckAvailabilityBackingBean(),
            "performances" to performanceRepository.findAll(),
            "seatNums" to 1..36,
            "seatRows" to 'A'..'O'
        )

        return ModelAndView("seatBooking", model)
    }

    @RequestMapping("checkAvailability", method = [RequestMethod.POST])
    fun checkAvailability(bean: CheckAvailabilityBackingBean): ModelAndView {
        val selectedSeat: Seat = bookingService.findSeat(bean.selectedSeatNum, bean.selectedSeatRow)!!
        val selectedPerformance = performanceRepository.findById(bean.selectedPerformance!!).get()

        bean.seat = selectedSeat
        bean.performance = selectedPerformance

        val isAvailable = bookingService.isSeatFree(selectedSeat, selectedPerformance)

        if (!isAvailable) {
            bean.booking = bookingService.findBooking(selectedSeat, selectedPerformance)
        }

        bean.available = isAvailable

        val model = mapOf(
            "bean" to bean,
            "performances" to performanceRepository.findAll(),
            "seatNums" to 1..36,
            "seatRows" to 'A'..'O'
        )

        return ModelAndView("seatBooking", model)
    }

    @RequestMapping("booking", method = [RequestMethod.POST])
    fun bookSeat(bean: CheckAvailabilityBackingBean): ModelAndView {
        val booking = bookingService.reserveSeat(bean.seat!!, bean.performance!!, bean.customerName)

        return ModelAndView("bookingConfirmed", "booking", booking)
    }

    @RequestMapping("bootstrap")
    fun createInitialData(): ModelAndView {
        // create the data and save to the db
        val seats = theaterService.seats
        seatRepository.saveAll(seats)

        return homePage()
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