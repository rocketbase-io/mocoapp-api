package io.rocketbase.mocoapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Activity {

    private Long id;

    @JsonProperty("date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    private double hours;
    private long seconds;
    private String description;
    private boolean billed;
    private boolean billable;
    private String tag;
    @JsonProperty("remote_service")
    private String remoteService;
    @JsonProperty("remote_id")
    private String remoteId;
    @JsonProperty("remote_url")
    private String remoteUrl;
    private Project project;
    private Task task;
    private Contact customer;
    private Contact user;

    @JsonProperty("hourly_rate")
    private BigDecimal hourlyRate;

    @JsonProperty("timer_started_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Instant timerStartedAt;

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Instant createdAt;

    @JsonProperty("updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Instant updatedAt;
}
