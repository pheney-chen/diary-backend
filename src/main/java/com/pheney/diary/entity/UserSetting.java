package com.pheney.diary.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("user_settings")
public class UserSetting {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private String theme;
    private String defaultMood;
    private Boolean reminderEnabled;
    private String reminderTime;
    private LocalDateTime updatedAt;
}
