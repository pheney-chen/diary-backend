package com.pheney.diary.service.impl;

import com.pheney.diary.dto.response.DiaryResponse;
import com.pheney.diary.dto.response.TagResponse;
import com.pheney.diary.entity.Diary;
import com.pheney.diary.entity.DiaryImage;
import com.pheney.diary.mapper.DiaryImageMapper;
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

    @Autowired
    private DiaryImageMapper diaryImageMapper;

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
        int offset = (page - 1) * pageSize;
        List<Diary> diaries = diaryMapper.findByTag(userId, tagName, offset, pageSize);
        List<DiaryResponse> responses = new ArrayList<>();
        for (Diary diary : diaries) {
            responses.add(buildDiaryResponse(diary));
        }
        return responses;
    }

    @Override
    public long countDiariesByTag(Long userId, String tagName) {
        return diaryMapper.countByTag(userId, tagName);
    }

    @Override
    @Transactional
    public void renameTag(Long userId, String oldName, String newName) {
        com.pheney.diary.entity.Tag tag = tagMapper.findByUserIdAndName(userId, oldName);
        if (tag != null) {
            tagMapper.updateName(tag.getId(), newName);
        }
    }

    @Override
    @Transactional
    public void deleteTag(Long userId, String tagName) {
        com.pheney.diary.entity.Tag tag = tagMapper.findByUserIdAndName(userId, tagName);
        if (tag != null) {
            diaryTagMapper.deleteByTagId(tag.getId());
            tagMapper.deleteById(tag.getId());
        }
    }

    private DiaryResponse buildDiaryResponse(Diary diary) {
        DiaryResponse response = new DiaryResponse();
        response.setId(diary.getId());
        response.setTitle(diary.getTitle());
        response.setContent(diary.getContent());
        response.setMood(diary.getMood());
        response.setType(diary.getType());
        response.setVoice(diary.getVoice());
        response.setVoiceDuration(diary.getVoiceDuration());
        response.setVideo(diary.getVideo());
        response.setVideoThumb(diary.getVideoThumb());
        response.setCreatedAt(diary.getCreatedAt());
        response.setUpdatedAt(diary.getUpdatedAt());

        List<DiaryImage> images = diaryImageMapper.findByDiaryId(diary.getId());
        response.setImages(images.stream().map(DiaryImage::getUrl).collect(Collectors.toList()));

        List<String> tagNames = diaryMapper.findTagNamesByDiaryId(diary.getId());
        response.setTags(tagNames);

        return response;
    }
}
