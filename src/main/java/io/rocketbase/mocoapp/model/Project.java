
package io.rocketbase.mocoapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.http.annotation.Contract;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project implements Serializable {

    private Long id;
    private String identifier;
    private String name;

    private boolean active;
    private boolean billable;

    @JsonProperty("fixed_price")
    private boolean fixedPrice;
    private boolean retainer;

    @JsonProperty("start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonProperty("finish_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate finishDate;

    private String currency;

    @JsonProperty("billing_variant")
    private String billingVariant;

    @JsonProperty("billing_address")
    private String billingAddress;

    @JsonProperty("billing_email_to")
    private String billingEmailTo;

    @JsonProperty("billing_email_cc")
    private String billingEmailCc;

    @JsonProperty("billing_notes")
    private String billingNotes;

    @JsonProperty("setting_include_time_report")
    private boolean settingIncludeTimeReport;

    private BigDecimal budget;

    @JsonProperty("budgetMonthly")
    private BigDecimal budgetMonthly;

    @JsonProperty("budget_expenses")
    private BigDecimal budgetExpenses;

    @JsonProperty("hourly_rate")
    private BigDecimal hourlyRate;
    private String info;
    private String color;
    private List<String> tags;
    private List<String> labels;

    @JsonProperty("custom_properties")
    private Map<String, Object> customProperties;
    private Contact leader;

    @JsonProperty("co_leader")
    private Contact coLeader;
    private Contact customer;
    private List<Task> tasks;

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Instant createdAt;

    @JsonProperty("updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Instant updatedAt;
}
