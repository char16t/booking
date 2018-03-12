package com.manenkov.booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Booking calendar.
 */
@NoArgsConstructor
public final class BookingCalendar {

    /**
     * Groups of events by day.
     */
    @JsonProperty("calendar")
    private final List<Day> days = new LinkedList<>();

    public List<Day> getDays() {
        return Collections.unmodifiableList(days);
    }

    public void addDays(final Day... days) {
        addDays(Arrays.asList(days));
    }

    public void addDays(final List<Day> days) {
        for (final Day day : days) {
            addDay(day);
        }
    }

    private void addDay(Day d) {
        if (!this.days.contains(d)) {
            days.add(d);
            return;
        }

        for (final Day day : this.days) {
            if (day.equals(d)) {
                day.addMeetings(d.getMeetings());
            }
        }
    }


}
