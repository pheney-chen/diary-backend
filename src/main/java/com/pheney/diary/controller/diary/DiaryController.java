package com.pheney.diary.controller.diary;

import com.pheney.diary.common.PageResult;
import com.pheney.diary.common.R;
import com.pheney.diary.dto.request.CreateDiaryRequest;
import com.pheney.diary.dto.request.UpdateDiaryRequest;
import com.pheney.diary.dto.response.DiaryResponse;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/diaries")
public class DiaryController {

    /**
     * 创建日记
     */
    @PostMapping
    public R<DiaryResponse> createDiary(@RequestBody CreateDiaryRequest request) {
        return R.success();
    }

    /**
     * 获取日记详情
     */
    @GetMapping("/{id}")
    public R<DiaryResponse> getDiary(@PathVariable Long id) {
        return R.success();
    }

    /**
     * 更新日记
     */
    @PutMapping("/{id}")
    public R<DiaryResponse> updateDiary(@PathVariable Long id, @RequestBody UpdateDiaryRequest request) {
        return R.success();
    }

    /**
     * 删除日记
     */
    @DeleteMapping("/{id}")
    public R<Void> deleteDiary(@PathVariable Long id) {
        return R.success();
    }

    /**
     * 日记列表（分页）
     */
    @GetMapping
    public R<PageResult<DiaryResponse>> getDiaryList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int pageSize) {
        return R.success();
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
        return R.success();
    }

    /**
     * 搜索日记
     */
    @GetMapping("/search")
    public R<PageResult<DiaryResponse>> searchDiaries(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int pageSize) {
        return R.success();
    }

    /**
     * 按日期获取日记
     */
    @GetMapping("/by-date")
    public R<DiaryResponse> getDiaryByDate(@RequestParam String date) {
        return R.success();
    }

    /**
     * 按月获取日记（日历页用）
     */
    @GetMapping("/by-month")
    public R<PageResult<DiaryResponse>> getDiariesByMonth(
            @RequestParam int year,
            @RequestParam int month) {
        return R.success();
    }
}
