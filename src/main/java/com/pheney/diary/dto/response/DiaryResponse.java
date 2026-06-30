package com.pheney.diary.dto.response;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class DiaryResponse {
    private Long id;
    private String title;
    private String content;
    private String mood;
    private String type;
    private List<String> tags;
    private List<String> images;
    private String voice;
    private Integer voiceDuration;
    private String video;
    private String videoThumb;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
