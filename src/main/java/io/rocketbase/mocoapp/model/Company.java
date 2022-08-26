
package io.rocketbase.mocoapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Company implements Serializable {

    private Long id;
    private String type;
    private String name;
    private String website;
    private String email;

    @JsonProperty("billing_email_cc")
    private String billingEmailCc;
    private String phone;
    private String fax;
    private String address;
    private ArrayList<String> tags;
    private Contact user;
    private String info;

    @JsonProperty("custom_properties")
    private Map<String, Object> customProperties;
    private String identifier;
    private boolean intern;

    @JsonProperty("billing_tax")
    private BigDecimal billingTax;

    @JsonProperty("billing_vat")
    private Map<String, Object> billingVat;
    private String currency;

    @JsonProperty("country_code")
    private String countryCode;

    @JsonProperty("vat_identifier")
    private String vatIdentifier;

    @JsonProperty("default_invoice_due_days")
    private int defaultInvoiceDueDays;
    private String footer;
    private ArrayList<Project> projects;

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Instant createdAt;

    @JsonProperty("updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Instant updatedAt;

    @JsonProperty("debit_number")
    private int debitNumber;


}
