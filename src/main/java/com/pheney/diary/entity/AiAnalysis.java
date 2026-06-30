package com.pheney.diary.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("ai_analyses")
public class AiAnalysis {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long diaryId;
    private String mode;
    private String content;
    private LocalDateTime createdAt;
}
