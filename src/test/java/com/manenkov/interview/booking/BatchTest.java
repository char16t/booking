package com.manenkov.interview.booking;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 * Test case for {@link Batch}.
 */
public final class BatchTest {
    /**
     * Batch can be converted to booking calendar.
     */
    @Test
    public void asBookingCalendar() throws ParseException {
        final BookingCalendar calendar = new Batch(
            new Date(),
            new Date(),
            new LinkedList<BookingRequest>() {{
                add(createRequest("2011-03-17 10:17:06", "EMP001", "2011-03-21 09:00", 2));
                add(createRequest("2011-03-16 12:34:56", "EMP002", "2011-03-21 09:00", 2));
                add(createRequest("2011-03-16 09:28:23", "EMP003", "2011-03-22 14:00", 2));
                add(createRequest("2011-03-17 11:23:45", "EMP004", "2011-03-22 16:00", 1));
                add(createRequest("2011-03-15 17:29:12", "EMP005", "2011-03-21 16:00", 3));
            }}
        ).asBookingCalendar();
    }

    private BookingRequest createRequest(final String submission,
                                         final String employee,
                                         final String start,
                                         final int duration) throws ParseException {
        Date submissionDate = new Date();
        Date startDate = new Date();

        final DateFormat submissionFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        final DateFormat startFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");

        submissionDate = submissionFormat.parse(submission);
        startDate = startFormat.parse(start);

        return new BookingRequest(submissionDate, startDate, employee, duration);
    }
}
