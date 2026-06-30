package com.pheney.diary.dto.response;

import lombok.Data;

@Data
public class AiAnalyzeResponse {
    private AiModeResponse mode;
    private String analysis;
    private Long timestamp;
}
