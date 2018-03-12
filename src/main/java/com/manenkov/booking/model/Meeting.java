package com.manenkov.booking.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = { "start", "end" })
public final class Meeting implements Comparable {
    @JsonFormat(pattern = "HH:mm")
    @JsonProperty("start_time")
    private LocalTime start;

    @JsonFormat(pattern = "HH:mm")
    @JsonProperty("end_time")
    private LocalTime end;

    @JsonProperty("employee_id")
    private String employeeId;

    @JsonIgnore
    private LocalDateTime submissionTime;

    @Override
    public int compareTo(Object o) {
        return o instanceof Meeting ? this.start.compareTo(((Meeting) o).getStart()) : -1;
    }
}
