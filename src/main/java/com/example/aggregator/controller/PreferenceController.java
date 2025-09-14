package com.example.aggregator.controller;

import com.example.aggregator.dto.PreferenceDTO;
import com.example.aggregator.entity.User;
import com.example.aggregator.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preferences")
@RequiredArgsConstructor
public class PreferenceController {

    private final UserService userService;

    @GetMapping
    public List<String> getPreferences(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getPreferences();
    }

    @PutMapping
    public String updatePreferences(@AuthenticationPrincipal UserDetails userDetails,
                                    @RequestBody PreferenceDTO preferences) {
        userService.updatePreferences(userDetails.getUsername(), preferences.getPreferences());
        return "Preferences updated successfully!";
    }
}

