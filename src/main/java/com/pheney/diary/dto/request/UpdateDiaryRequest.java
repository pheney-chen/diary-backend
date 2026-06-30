package com.pheney.diary.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class UpdateDiaryRequest {
    private String title;
    private String content;
    private String mood;
    private List<String> tags;
    private List<String> images;
    private String voice;
    private Integer voiceDuration;
    private String video;
    private String videoThumb;
}
