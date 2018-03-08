package com.manenkov.interview.booking;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Date;
import java.util.List;

/**
 * Batch of booking requests.
 */
@Getter
@EqualsAndHashCode
public final class Batch {

    /**
     * Company office hours. Start.
     */
    private final Date startOfficeTime;

    /**
     * Company office hours. End.
     */
    private final Date endOfficeTime;

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
    public Batch(final Date startOfficeTime, final Date endOfficeTime, final List<BookingRequest> bookingRequests) {
        this.startOfficeTime = startOfficeTime;
        this.endOfficeTime = endOfficeTime;
        this.bookingRequests = bookingRequests;
    }


}
