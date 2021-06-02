package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.assertj.core.util.Lists;

import java.util.List;

@Data
public class SessionsDTO {

    @NotNull
    @JsonProperty(value = "sessions")
    private List<SessionDTO> data = Lists.newArrayList();
}
