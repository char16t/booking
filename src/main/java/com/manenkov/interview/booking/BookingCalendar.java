package com.manenkov.interview.booking;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Date;
import java.util.List;

/**
 * Booking calendar.
 */
@Getter
@EqualsAndHashCode
public final class BookingCalendar {
    /**
     * Date.
     */
    private final Date date;

    /**
     * Booking calendar items.
     */
    private final List<Event> events;

    /**
     * Ctor.
     *
     * @param date Day
     * @param events Booking calendar items.
     */
    public BookingCalendar(final Date date, final List<Event> events) {
        this.date = date;
        this.events = events;
    }
}
