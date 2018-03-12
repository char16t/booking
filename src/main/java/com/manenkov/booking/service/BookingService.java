package com.manenkov.booking.service;

import com.manenkov.booking.model.Batch;
import com.manenkov.booking.model.BookingCalendar;
import com.manenkov.booking.model.BookingRequest;
import com.manenkov.booking.model.Day;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingCalendar calendar = new BookingCalendar();

    public Day getMeetings(final LocalDate date) {
        return calendar.getDays().stream()
            .filter(day -> day.getDay().equals(date))
            .findFirst()
            .orElse(new Day.Null(date));
    }

    public BookingCalendar process(final Batch batch) {
        final Map<LocalDate, List<BookingRequest>> requestsByDay =
            batch.getBookingRequests().stream()
                .collect(Collectors.groupingBy(request ->
                    request.getMeetingTime()
                    .toInstant(ZoneOffset.UTC)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
                ));

        requestsByDay.forEach((date, requests) -> {
            // Filter booking requests at office hours
            final List<BookingRequest> validRequests = requests.stream()
                .filter(request -> atOfficeHours(request, batch.getStartOfficeHours(), batch.getEndOfficeHours()))
                .collect(Collectors.toList());

            // Remove overlaps
            final List<BookingRequest> sortedRequests = validRequests.stream().sorted(Comparator.comparing(BookingRequest::getMeetingTime)).collect(Collectors.toList());
            BookingRequest previous = new BookingRequest(LocalDateTime.MIN, "", LocalDateTime.MIN, 0);
            BookingRequest current;
            for (final BookingRequest request : sortedRequests) {
                current = request;
                final LocalTime previousEndTime = previous.getMeetingTime().toLocalTime().plusHours(previous.getDuration());
                final LocalTime currentStartTime = current.getMeetingTime().toLocalTime();
                if (currentStartTime.isBefore(previousEndTime)) {
                    validRequests.remove(current);
                }
                previous = current;
            }

            // Add meetings to calendar
            calendar.addDays(
                new Day(
                    date,
                    validRequests.stream().map(BookingRequest::toMeeting).collect(Collectors.toList())
                )
            );
        });

        return calendar;
    }

    private boolean atOfficeHours(BookingRequest request, LocalTime startOfficeHours, LocalTime endOfficeHours) {
        final boolean afterStartTime = request.getMeetingTime().toLocalTime().isAfter(startOfficeHours);
        final boolean atStartTime = request.getMeetingTime().toLocalTime().equals(startOfficeHours);
        final boolean beforeEndTime = request.getMeetingTime().toLocalTime().plusHours(request.getDuration()).isBefore(endOfficeHours);
        final boolean atEndTime = request.getMeetingTime().toLocalTime().plusHours(request.getDuration()).equals(endOfficeHours);
        return (afterStartTime || atStartTime) && (beforeEndTime || atEndTime);
    }
}
