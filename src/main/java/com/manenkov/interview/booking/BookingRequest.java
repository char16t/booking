package com.manenkov.interview.booking;

import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;

import java.util.Date;

/**
 * Booking request.
 */
@Getter
@EqualsAndHashCode
public final class BookingRequest {

    /**
     * Submission time.
     */
    private final Date submissionTime;

    /**
     * Start time.
     */
    private final Date startTime;

    /**
     * Employee's identifier.
     */
    private final String employee;

    /**
     * Ctor.
     *
     * @param submissionTime Submission time.
     * @param startTime Start time.
     * @param employee Employee's identifier.
     */
    public BookingRequest(final Date submissionTime, final Date startTime, final String employee) {
        this.submissionTime = submissionTime;
        this.startTime = startTime;
        this.employee = employee;
    }
}
