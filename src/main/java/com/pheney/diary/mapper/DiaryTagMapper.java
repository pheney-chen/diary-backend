package com.pheney.diary.mapper;

import com.pheney.diary.entity.DiaryTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiaryTagMapper {

    void insert(DiaryTag diaryTag);

    List<Long> findTagIdsByDiaryId(@Param("diaryId") Long diaryId);

    List<Long> findDiaryIdsByTagId(@Param("tagId") Long tagId);

    void deleteByDiaryId(@Param("diaryId") Long diaryId);

    void deleteByTagId(@Param("tagId") Long tagId);
}
