package com.pheney.diary.service;

import com.pheney.diary.dto.request.CreateDiaryRequest;
import com.pheney.diary.dto.request.UpdateDiaryRequest;
import com.pheney.diary.dto.response.DiaryResponse;

import java.time.LocalDate;
import java.util.List;

public interface DiaryService {

    DiaryResponse create(Long userId, CreateDiaryRequest request);

    DiaryResponse getById(Long userId, Long id);

    DiaryResponse update(Long userId, Long id, UpdateDiaryRequest request);

    void delete(Long userId, Long id);

    List<DiaryResponse> list(Long userId, int page, int pageSize);

    long count(Long userId);

    List<DiaryResponse> search(Long userId, String keyword, int page, int pageSize);

    long countSearch(Long userId, String keyword);

    List<DiaryResponse> filter(Long userId, String keyword, String type, String mood,
                               String tag, LocalDate startDate, LocalDate endDate,
                               int page, int pageSize);

    long countFilter(Long userId, String keyword, String type, String mood,
                     String tag, LocalDate startDate, LocalDate endDate);

    DiaryResponse getByDate(Long userId, LocalDate date);

    List<DiaryResponse> getByMonth(Long userId, int year, int month);
}
