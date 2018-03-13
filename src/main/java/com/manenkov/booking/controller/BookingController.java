package com.manenkov.booking.controller;

import com.manenkov.booking.model.Batch;
import com.manenkov.booking.model.BookingCalendar;
import com.manenkov.booking.model.Day;
import com.manenkov.booking.service.BookingService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class BookingController {

    private final BookingService bookingService;

    public BookingController(final BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @RequestMapping(value = "/booking", method = RequestMethod.GET)
    public Day getMeetings(final @RequestParam("date") String date) {
        return bookingService.getMeetings(LocalDate.parse(date));
    }

    @RequestMapping(value = "/booking", method = RequestMethod.POST)
    public BookingCalendar processBatch(final @RequestBody Batch batch) {
        return bookingService.process(batch);
    }
}
