package com.pheney.diary.service.impl;

import com.pheney.diary.dto.request.CreateDiaryRequest;
import com.pheney.diary.dto.request.UpdateDiaryRequest;
import com.pheney.diary.dto.response.DiaryResponse;
import com.pheney.diary.entity.Diary;
import com.pheney.diary.entity.DiaryImage;
import com.pheney.diary.entity.DiaryTag;
import com.pheney.diary.entity.Tag;
import com.pheney.diary.mapper.DiaryImageMapper;
import com.pheney.diary.mapper.DiaryMapper;
import com.pheney.diary.mapper.DiaryTagMapper;
import com.pheney.diary.mapper.TagMapper;
import com.pheney.diary.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    private DiaryMapper diaryMapper;

    @Autowired
    private DiaryImageMapper diaryImageMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private DiaryTagMapper diaryTagMapper;

    @Override
    @Transactional
    public DiaryResponse create(Long userId, CreateDiaryRequest request) {
        Diary diary = new Diary();
        diary.setUserId(userId);
        diary.setTitle(request.getTitle());
        diary.setContent(request.getContent());
        diary.setMood(request.getMood());
        diary.setType(determineType(request));
        diary.setVoice(request.getVoice());
        diary.setVoiceDuration(request.getVoiceDuration());
        diary.setVideo(request.getVideo());
        diary.setVideoThumb(request.getVideoThumb());
        diary.setDiaryDate(LocalDate.now());

        diaryMapper.insert(diary);

        if (request.getImages() != null && !request.getImages().isEmpty()) {
            for (int i = 0; i < request.getImages().size(); i++) {
                DiaryImage image = new DiaryImage();
                image.setDiaryId(diary.getId());
                image.setUrl(request.getImages().get(i));
                image.setSortOrder(i);
                diaryImageMapper.insert(image);
            }
        }

        if (request.getTags() != null) {
            for (String tagName : request.getTags()) {
                Tag tag = tagMapper.findByUserIdAndName(userId, tagName);
                if (tag == null) {
                    tag = new Tag();
                    tag.setUserId(userId);
                    tag.setName(tagName);
                    tagMapper.insert(tag);
                }
                DiaryTag diaryTag = new DiaryTag();
                diaryTag.setDiaryId(diary.getId());
                diaryTag.setTagId(tag.getId());
                diaryTagMapper.insert(diaryTag);
            }
        }

        return getDiaryResponse(diary.getId(), userId);
    }

    @Override
    public DiaryResponse getById(Long userId, Long id) {
        Diary diary = diaryMapper.findById(id);
        if (diary == null || !diary.getUserId().equals(userId)) {
            return null;
        }
        return buildDiaryResponse(diary);
    }

    @Override
    @Transactional
    public DiaryResponse update(Long userId, Long id, UpdateDiaryRequest request) {
        Diary diary = diaryMapper.findById(id);
        if (diary == null || !diary.getUserId().equals(userId)) {
            return null;
        }

        if (request.getTitle() != null) diary.setTitle(request.getTitle());
        if (request.getContent() != null) diary.setContent(request.getContent());
        if (request.getMood() != null) diary.setMood(request.getMood());
        if (request.getVoice() != null) diary.setVoice(request.getVoice());
        if (request.getVoiceDuration() != null) diary.setVoiceDuration(request.getVoiceDuration());
        if (request.getVideo() != null) diary.setVideo(request.getVideo());
        if (request.getVideoThumb() != null) diary.setVideoThumb(request.getVideoThumb());

        diaryMapper.update(diary);

        if (request.getImages() != null) {
            diaryImageMapper.deleteByDiaryId(id);
            for (int i = 0; i < request.getImages().size(); i++) {
                DiaryImage image = new DiaryImage();
                image.setDiaryId(id);
                image.setUrl(request.getImages().get(i));
                image.setSortOrder(i);
                diaryImageMapper.insert(image);
            }
        }

        if (request.getTags() != null) {
            diaryTagMapper.deleteByDiaryId(id);
            for (String tagName : request.getTags()) {
                Tag tag = tagMapper.findByUserIdAndName(userId, tagName);
                if (tag == null) {
                    tag = new Tag();
                    tag.setUserId(userId);
                    tag.setName(tagName);
                    tagMapper.insert(tag);
                }
                DiaryTag diaryTag = new DiaryTag();
                diaryTag.setDiaryId(id);
                diaryTag.setTagId(tag.getId());
                diaryTagMapper.insert(diaryTag);
            }
        }

        return getDiaryResponse(id, userId);
    }

    @Override
    public void delete(Long userId, Long id) {
        Diary diary = diaryMapper.findById(id);
        if (diary != null && diary.getUserId().equals(userId)) {
            diaryMapper.deleteById(id);
        }
    }

    @Override
    public List<DiaryResponse> list(Long userId, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        List<Diary> diaries = diaryMapper.findByUserId(userId, offset, pageSize);
        return diaries.stream().map(this::buildDiaryResponse).collect(Collectors.toList());
    }

    @Override
    public long count(Long userId) {
        return diaryMapper.countByUserId(userId);
    }

    @Override
    public List<DiaryResponse> search(Long userId, String keyword, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        List<Diary> diaries = diaryMapper.search(userId, keyword, offset, pageSize);
        return diaries.stream().map(this::buildDiaryResponse).collect(Collectors.toList());
    }

    @Override
    public long countSearch(Long userId, String keyword) {
        return diaryMapper.countSearch(userId, keyword);
    }

    @Override
    public List<DiaryResponse> filter(Long userId, String keyword, String type, String mood,
                                       String tag, LocalDate startDate, LocalDate endDate,
                                       int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        List<Diary> diaries = diaryMapper.filter(userId, keyword, type, mood, tag, startDate, endDate, offset, pageSize);
        return diaries.stream().map(this::buildDiaryResponse).collect(Collectors.toList());
    }

    @Override
    public long countFilter(Long userId, String keyword, String type, String mood,
                            String tag, LocalDate startDate, LocalDate endDate) {
        return diaryMapper.countFilter(userId, keyword, type, mood, tag, startDate, endDate);
    }

    @Override
    public DiaryResponse getByDate(Long userId, LocalDate date) {
        List<Diary> diaries = diaryMapper.findByUserIdAndDate(userId, date);
        if (diaries.isEmpty()) {
            return null;
        }
        return buildDiaryResponse(diaries.get(0));
    }

    @Override
    public List<DiaryResponse> getByMonth(Long userId, int year, int month) {
        List<Diary> diaries = diaryMapper.findByUserIdAndYearMonth(userId, year, month);
        return diaries.stream().map(this::buildDiaryResponse).collect(Collectors.toList());
    }

    private String determineType(CreateDiaryRequest request) {
        boolean hasText = request.getContent() != null && !request.getContent().isEmpty();
        boolean hasImage = request.getImages() != null && !request.getImages().isEmpty();
        boolean hasVoice = request.getVoice() != null && !request.getVoice().isEmpty();
        boolean hasVideo = request.getVideo() != null && !request.getVideo().isEmpty();

        int count = 0;
        if (hasText) count++;
        if (hasImage) count++;
        if (hasVoice) count++;
        if (hasVideo) count++;

        if (count == 0 || (count == 1 && hasText)) return "text";
        if (count == 1 && hasImage) return "image";
        if (count == 1 && hasVoice) return "voice";
        if (count == 1 && hasVideo) return "video";
        return "mixed";
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

    private DiaryResponse getDiaryResponse(Long diaryId, Long userId) {
        Diary diary = diaryMapper.findById(diaryId);
        return buildDiaryResponse(diary);
    }
}
