package com.manenkov.booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = {"day"})
public class Day {

    @JsonProperty("day")
    private final LocalDate day;

    @JsonProperty("meetings")
    private final List<Meeting> meetings;

    public Day(final LocalDate day, final Meeting... meetings) {
        this.day = day;
        this.meetings = Arrays.asList(meetings);
    }

    public void addMeetings(final Meeting... meetings) {
        addMeetings(Arrays.asList(meetings));
    }

    public void addMeetings(final Collection<Meeting> meetings) {
        for (final Meeting meeting : meetings) {
            addMeeting(meeting);
        }
        Collections.sort(this.meetings);
    }

    private void addMeeting(final Meeting m) {
        if (!this.meetings.contains(m)) {
            this.meetings.add(m);
        }
    }

    public static class Null extends Day {
        public Null(final LocalDate day) {
            super(day, new LinkedList<>());
        }
    }
}
