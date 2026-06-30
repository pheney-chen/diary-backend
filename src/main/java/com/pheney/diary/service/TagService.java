package com.pheney.diary.service;

import com.pheney.diary.dto.response.TagResponse;

import java.util.List;

public interface TagService {

    List<TagResponse> getAllTags(Long userId);

    List<DiaryResponse> getDiariesByTag(Long userId, String tagName, int page, int pageSize);

    long countDiariesByTag(Long userId, String tagName);

    void renameTag(Long userId, String oldName, String newName);

    void deleteTag(Long userId, String tagName);
}
