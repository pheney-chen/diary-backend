package com.pheney.diary.dto.request;

import lombok.Data;

@Data
public class UpdateSettingsRequest {
    private String theme;
    private String defaultMood;
    private Boolean reminderEnabled;
    private String reminderTime;
}
