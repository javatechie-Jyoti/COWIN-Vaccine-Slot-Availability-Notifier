package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SessionDTO {

    @NotNull
    @JsonProperty(value ="center_id")
    private String center_id;

    @NotNull
    @JsonProperty(value="name")
    private String name;

    @NotNull
    @JsonProperty(value="available_capacity")
    private String available_capacity;

    @NotNull
    @JsonProperty(value="min_age_limit")
    private String min_age_limit;

    @NotNull
    @JsonProperty(value="available_capacity_dose2")
    private String available_capacity_dose2;

    @NotNull
    @JsonProperty(value="available_capacity_dose1")
    private String available_capacity_dose1;

    @NotNull
    @JsonProperty(value="vaccine")
    private String vaccine;
}
