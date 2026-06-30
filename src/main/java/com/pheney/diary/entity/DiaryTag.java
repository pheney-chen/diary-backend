package com.pheney.diary.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("diary_tags")
public class DiaryTag {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long diaryId;
    private Long tagId;
}
