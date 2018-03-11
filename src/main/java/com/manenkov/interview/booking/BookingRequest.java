package com.manenkov.interview.booking;

import lombok.EqualsAndHashCode;
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
     * Meeting duration
     */
    private final Integer duration;

    /**
     * Ctor.
     *
     * @param submissionTime Submission time.
     * @param startTime Start time.
     * @param employee Employee's identifier.
     * @param duration Meeting duration.
     */
    public BookingRequest(final Date submissionTime, final Date startTime, final String employee, final Integer duration) {
        this.submissionTime = submissionTime;
        this.startTime = startTime;
        this.employee = employee;
        this.duration = duration;
    }
}
