package com.pheney.diary.dto.request;

import lombok.Data;

@Data
public class AiAnalyzeRequest {
    private Long diaryId;
    private String mode;
    private String content;
    private String mood;
}
