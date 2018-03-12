package com.manenkov.booking.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public final class BookingRequest {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("submission_time")
    private final LocalDateTime submissionTime;

    @JsonProperty("employee_id")
    private final String employeeId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonProperty("meeting_time")
    private final LocalDateTime meetingTime;

    @JsonProperty("duration")
    private final Integer duration;

    public Meeting toMeeting() {
        return new Meeting(
            meetingTime.toLocalTime(),
            meetingTime.toLocalTime().plusHours(this.duration),
            this.employeeId,
            this.submissionTime
        );
    }
}
