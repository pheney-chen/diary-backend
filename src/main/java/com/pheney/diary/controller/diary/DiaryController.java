package com.pheney.diary.controller.diary;

import com.pheney.diary.common.PageResult;
import com.pheney.diary.common.R;
import com.pheney.diary.dto.request.CreateDiaryRequest;
import com.pheney.diary.dto.request.UpdateDiaryRequest;
import com.pheney.diary.dto.response.DiaryResponse;
import com.pheney.diary.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/api/diaries")
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    /**
     * 创建日记
     */
    @PostMapping
    public R<DiaryResponse> createDiary(@RequestBody CreateDiaryRequest request) {
        Long userId = getCurrentUserId();
        DiaryResponse response = diaryService.create(userId, request);
        return R.success(response);
    }

    /**
     * 获取日记详情
     */
    @GetMapping("/{id}")
    public R<DiaryResponse> getDiary(@PathVariable Long id) {
        Long userId = getCurrentUserId();
        DiaryResponse response = diaryService.getById(userId, id);
        if (response == null) {
            return R.error(404, "Diary not found");
        }
        return R.success(response);
    }

    /**
     * 更新日记
     */
    @PutMapping("/{id}")
    public R<DiaryResponse> updateDiary(@PathVariable Long id, @RequestBody UpdateDiaryRequest request) {
        Long userId = getCurrentUserId();
        DiaryResponse response = diaryService.update(userId, id, request);
        if (response == null) {
            return R.error(404, "Diary not found");
        }
        return R.success(response);
    }

    /**
     * 删除日记
     */
    @DeleteMapping("/{id}")
    public R<Void> deleteDiary(@PathVariable Long id) {
        Long userId = getCurrentUserId();
        diaryService.delete(userId, id);
        return R.success();
    }

    /**
     * 日记列表（分页）
     */
    @GetMapping
    public R<PageResult<DiaryResponse>> getDiaryList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int pageSize) {
        Long userId = getCurrentUserId();
        List<DiaryResponse> list = diaryService.list(userId, page, pageSize);
        long total = diaryService.count(userId);
        return R.success(PageResult.of(list, total, page, pageSize));
    }

    /**
     * 高级筛选日记
     */
    @GetMapping("/filter")
    public R<PageResult<DiaryResponse>> filterDiaries(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String mood,
            @RequestParam(required = false) String tag,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int pageSize) {
        Long userId = getCurrentUserId();
        LocalDate start = parseDate(startDate);
        LocalDate end = parseDate(endDate);

        List<DiaryResponse> list = diaryService.filter(userId, keyword, type, mood, tag, start, end, page, pageSize);
        long total = diaryService.countFilter(userId, keyword, type, mood, tag, start, end);
        return R.success(PageResult.of(list, total, page, pageSize));
    }

    /**
     * 搜索日记
     */
    @GetMapping("/search")
    public R<PageResult<DiaryResponse>> searchDiaries(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int pageSize) {
        Long userId = getCurrentUserId();
        List<DiaryResponse> list = diaryService.search(userId, keyword, page, pageSize);
        long total = diaryService.countSearch(userId, keyword);
        return R.success(PageResult.of(list, total, page, pageSize));
    }

    /**
     * 按日期获取日记
     */
    @GetMapping("/by-date")
    public R<DiaryResponse> getDiaryByDate(@RequestParam String date) {
        Long userId = getCurrentUserId();
        LocalDate localDate = LocalDate.parse(date);
        DiaryResponse response = diaryService.getByDate(userId, localDate);
        if (response == null) {
            return R.error(404, "Diary not found for this date");
        }
        return R.success(response);
    }

    /**
     * 按月获取日记（日历页用）
     */
    @GetMapping("/by-month")
    public R<PageResult<DiaryResponse>> getDiariesByMonth(
            @RequestParam int year,
            @RequestParam int month) {
        Long userId = getCurrentUserId();
        List<DiaryResponse> list = diaryService.getByMonth(userId, year, month);
        return R.success(PageResult.of(list, list.size(), 1, list.size()));
    }

    private LocalDate parseDate(String date) {
        if (date == null || date.isEmpty()) {
            return null;
        }
        return LocalDate.parse(date);
    }

    private Long getCurrentUserId() {
        return 1L;
    }
}
