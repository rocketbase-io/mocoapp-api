
package io.rocketbase.mocoapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contact implements Serializable {
    private Long id;
    private String gender;
    private String firstname;
    private String lastname;
    private String title;

    @JsonProperty("job_position")
    private String jobPosition;

    @JsonProperty("mobile_phone")
    private String mobilePhone;

    @JsonProperty("work_fax")
    private String workFax;

    @JsonProperty("work_phone")
    private String workPhone;

    @JsonProperty("work_email")
    private String workEmail;

    @JsonProperty("work_address")
    private String workAddress;

    @JsonProperty("home_email")
    private String homeEmail;

    @JsonProperty("home_address")
    private String homeAddress;
    private String birthday;
    private String info;

    @JsonProperty("avatar_url")
    private String avatarUrl;
    private ArrayList<String> tags;
    private Company company;

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Instant createdAt;

    @JsonProperty("updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Instant updatedAt;

}
