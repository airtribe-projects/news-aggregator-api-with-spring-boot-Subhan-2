package com.example.aggregator.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class PreferenceDTO {
    @NotEmpty(message = "Preferences list cannot be empty")
    private List<String> preferences;
}
