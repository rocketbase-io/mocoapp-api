package io.rocketbase.mocoapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityCreate {

    @JsonProperty("date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    private Double hours;
    private Long seconds;
    private String description;

    private Boolean billable;

    @JsonProperty("project_id")
    private long projectId;

    @JsonProperty("task_id")
    private long taskId;

    private String tag;

    @JsonProperty("remote_service")
    private String remoteService;

    @JsonProperty("remote_id")
    private String remoteId;

    @JsonProperty("remote_url")
    private String remoteUrl;
}
