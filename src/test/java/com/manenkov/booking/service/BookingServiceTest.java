package com.manenkov.booking.service;

import com.manenkov.Application;
import com.manenkov.booking.model.BookingCalendar;
import com.manenkov.booking.model.Day;
import com.manenkov.booking.model.Meeting;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class BookingServiceTest {

    private BookingService bookingService;

    @Before
    public void setUp() throws Exception {
        final BookingCalendar calendar = new BookingCalendar();
        calendar.addDays(
            new Day(
                LocalDate.of(2018, 3, 1),
                new Meeting(
                    LocalTime.of(9, 0),
                    LocalTime.of(10, 0),
                    "EMP001",
                    LocalDateTime.MIN
                ),
                new Meeting(
                    LocalTime.of(12, 0),
                    LocalTime.of(14, 0),
                    "EMP002",
                    LocalDateTime.MIN
                )
            ),
            new Day(
                LocalDate.of(2018, 3, 2),
                new Meeting(
                    LocalTime.of(9, 0),
                    LocalTime.of(10, 0),
                    "EMP003",
                    LocalDateTime.MIN
                )
            )
        );

        this.bookingService = new BookingService(calendar);
    }

    @Test
    public void getMeetingsTest() {
        final Day expected = new Day(
            LocalDate.of(2018, 3, 1),
            new Meeting(
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                "EMP001",
                LocalDateTime.MIN
            ),
            new Meeting(
                LocalTime.of(12, 0),
                LocalTime.of(14, 0),
                "EMP002",
                LocalDateTime.MIN
            )
        );

        final Day actual = bookingService.getMeetings(LocalDate.of(2018, 3, 1));
        assertThat(actual.getDay(), is(equalTo(expected.getDay())));
        assertThat(actual.getMeetings(), is(equalTo(expected.getMeetings())));
    }
}
