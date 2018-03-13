package com.manenkov.booking.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

/**
 * Batch of booking requests.
 */
@Getter
@AllArgsConstructor
public final class Batch {

    /**
     * Company office hours. Start.
     */
    @JsonFormat(pattern = "HHmm")
    @JsonProperty("start_office_hours")
    private final LocalTime startOfficeHours;

    /**
     * Company office hours. End.
     */
    @JsonFormat(pattern = "HHmm")
    @JsonProperty("end_office_hours")
    private final LocalTime endOfficeHours;

    /**
     * Booking requests.
     */
    @JsonProperty("booking_requests")
    private final List<BookingRequest> bookingRequests;

    public Batch(final LocalTime startOfficeHours,
                 final LocalTime endOfficeHours,
                 final BookingRequest... bookingRequests) {
        this(startOfficeHours, endOfficeHours, Arrays.asList(bookingRequests));
    }
}
