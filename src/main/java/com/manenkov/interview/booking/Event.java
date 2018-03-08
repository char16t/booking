package com.manenkov.interview.booking;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Date;

/**
 * Booking calendar item.
 */
@Getter
@EqualsAndHashCode
public final class Event {

    /**
     * Employee's identifier.
     */
    private final String employee;

    /**
     * Event time.
     */
    private final Date time;

    /**
     * Ctor.
     *
     * @param employee Employee identifier.
     * @param time Event time.
     */
    public Event(final String employee, final Date time) {
        this.employee = employee;
        this.time = time;
    }
}
