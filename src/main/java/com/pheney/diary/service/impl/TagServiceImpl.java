package com.pheney.diary.service.impl;

import com.pheney.diary.dto.response.DiaryResponse;
import com.pheney.diary.dto.response.TagResponse;
import com.pheney.diary.entity.Diary;
import com.pheney.diary.entity.Tag;
import com.pheney.diary.mapper.DiaryMapper;
import com.pheney.diary.mapper.DiaryTagMapper;
import com.pheney.diary.mapper.TagMapper;
import com.pheney.diary.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private DiaryTagMapper diaryTagMapper;

    @Autowired
    private DiaryMapper diaryMapper;

    @Override
    public List<TagResponse> getAllTags(Long userId) {
        List<Map<String, Object>> result = tagMapper.findTagsWithCount(userId);
        List<TagResponse> responses = new ArrayList<>();
        for (Map<String, Object> row : result) {
            TagResponse tagResponse = new TagResponse();
            tagResponse.setName((String) row.get("name"));
            tagResponse.setCount(((Number) row.get("count")).intValue());
            responses.add(tagResponse);
        }
        return responses;
    }

    @Override
    public List<DiaryResponse> getDiariesByTag(Long userId, String tagName, int page, int pageSize) {
        Tag tag = tagMapper.findByUserIdAndName(userId, tagName);
        if (tag == null) {
            return new ArrayList<>();
        }

        List<Long> diaryIds = diaryTagMapper.findDiaryIdsByTagId(tag.getId());
        if (diaryIds.isEmpty()) {
            return new ArrayList<>();
        }

        int offset = (page - 1) * pageSize;
        List<Diary> diaries = new ArrayList<>();
        for (int i = offset; i < Math.min(offset + pageSize, diaryIds.size()); i++) {
            Diary diary = diaryMapper.findById(diaryIds.get(i));
            if (diary != null && diary.getUserId().equals(userId)) {
                diaries.add(diary);
            }
        }

        List<DiaryResponse> responses = new ArrayList<>();
        for (Diary diary : diaries) {
            responses.add(buildSimpleDiaryResponse(diary));
        }
        return responses;
    }

    @Override
    public long countDiariesByTag(Long userId, String tagName) {
        Tag tag = tagMapper.findByUserIdAndName(userId, tagName);
        if (tag == null) {
            return 0;
        }
        List<Long> diaryIds = diaryTagMapper.findDiaryIdsByTagId(tag.getId());
        return diaryIds.size();
    }

    @Override
    @Transactional
    public void renameTag(Long userId, String oldName, String newName) {
        Tag tag = tagMapper.findByUserIdAndName(userId, oldName);
        if (tag != null) {
            tagMapper.updateName(tag.getId(), newName);
        }
    }

    @Override
    @Transactional
    public void deleteTag(Long userId, String tagName) {
        Tag tag = tagMapper.findByUserIdAndName(userId, tagName);
        if (tag != null) {
            diaryTagMapper.deleteByTagId(tag.getId());
            tagMapper.deleteById(tag.getId());
        }
    }

    private DiaryResponse buildSimpleDiaryResponse(Diary diary) {
        DiaryResponse response = new DiaryResponse();
        response.setId(diary.getId());
        response.setTitle(diary.getTitle());
        response.setContent(diary.getContent());
        response.setMood(diary.getMood());
        response.setType(diary.getType());
        response.setCreatedAt(diary.getCreatedAt());
        response.setUpdatedAt(diary.getUpdatedAt());
        return response;
    }
}
