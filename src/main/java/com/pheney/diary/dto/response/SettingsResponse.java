package com.pheney.diary.dto.response;

import lombok.Data;

@Data
public class SettingsResponse {
    private String theme;
    private String defaultMood;
    private Boolean reminderEnabled;
    private String reminderTime;
}
