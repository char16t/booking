package com.manenkov.interview.booking;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

/**
 * Booking calendar.
 */
@Getter
@EqualsAndHashCode
public final class BookingCalendar {
    /**
     * Groups of events by day.
     */
    private final List<BookingCalendarGroup> groups;

    /**
     * Ctor.
     *
     * @param groups Groups of events by day.
     */
    public BookingCalendar(List<BookingCalendarGroup> groups) {
        this.groups = groups;
    }
}
