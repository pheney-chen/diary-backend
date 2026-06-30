package com.pheney.diary.mapper;

import com.pheney.diary.entity.DiaryImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiaryImageMapper {

    void insert(DiaryImage image);

    List<DiaryImage> findByDiaryId(@Param("diaryId") Long diaryId);

    void deleteByDiaryId(@Param("diaryId") Long diaryId);
}
