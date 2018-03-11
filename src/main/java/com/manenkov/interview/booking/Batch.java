package com.manenkov.interview.booking;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Batch of booking requests.
 */
@Getter
@EqualsAndHashCode
public final class Batch {

    /**
     * Company office hours. Start.
     */
    private final LocalTime startOfficeTime;

    /**
     * Company office hours. End.
     */
    private final LocalTime endOfficeTime;

    /**
     * Booking requests.
     */
    private final List<BookingRequest> bookingRequests;

    /**
     * Ctor.
     *
     * @param startOfficeTime Start company office hours.
     * @param endOfficeTime End company office hours.
     * @param bookingRequests Booking requests.
     */
    public Batch(final LocalTime startOfficeTime, final LocalTime endOfficeTime, final List<BookingRequest> bookingRequests) {
        this.startOfficeTime = startOfficeTime;
        this.endOfficeTime = endOfficeTime;
        this.bookingRequests = bookingRequests;
    }

    /**
     * Converts batch to the booking calendar.
     *
     * @return Batch as a booking calendar.
     */
    public BookingCalendar asBookingCalendar() {
        final List<BookingCalendarGroup> groups = new LinkedList<>();
        final BookingCalendar bookingCalendar = new BookingCalendar(groups);

        final Map<Date, List<BookingRequest>> requestsGroupedByDate = bookingRequests
            .stream()
            .collect(Collectors.groupingBy(request ->
                Date.from(request.getStartTime().toInstant().truncatedTo(ChronoUnit.DAYS))
            ));

        for (final Date date : requestsGroupedByDate.keySet()) {
            final List<Event> events = new LinkedList<>();
            final BookingCalendarGroup group = new BookingCalendarGroup(date, events);
            for (final BookingRequest request : requestsGroupedByDate.get(date)) {
               group.getEvents().add(new Event(request.getEmployee(), request.getStartTime()));
            }
            groups.add(group);
        }

        return bookingCalendar;
    }
}
