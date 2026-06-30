package com.pheney.diary.controller.tag;

import com.pheney.diary.common.PageResult;
import com.pheney.diary.common.R;
import com.pheney.diary.dto.request.RenameTagRequest;
import com.pheney.diary.dto.response.DiaryResponse;
import com.pheney.diary.dto.response.TagResponse;
import com.pheney.diary.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 获取所有标签
     */
    @GetMapping
    public R<List<TagResponse>> getAllTags() {
        Long userId = getCurrentUserId();
        List<TagResponse> tags = tagService.getAllTags(userId);
        return R.success(tags);
    }

    /**
     * 获取标签下的日记
     */
    @GetMapping("/{name}/diaries")
    public R<PageResult<DiaryResponse>> getTagDiaries(
            @PathVariable String name,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int pageSize) {
        Long userId = getCurrentUserId();
        List<DiaryResponse> list = tagService.getDiariesByTag(userId, name, page, pageSize);
        long total = tagService.countDiariesByTag(userId, name);
        return R.success(PageResult.of(list, total, page, pageSize));
    }

    /**
     * 重命名标签
     */
    @PutMapping("/{name}")
    public R<Void> renameTag(@PathVariable String name, @RequestBody RenameTagRequest request) {
        Long userId = getCurrentUserId();
        tagService.renameTag(userId, name, request.getNewName());
        return R.success();
    }

    /**
     * 删除标签
     */
    @DeleteMapping("/{name}")
    public R<Void> deleteTag(@PathVariable String name) {
        Long userId = getCurrentUserId();
        tagService.deleteTag(userId, name);
        return R.success();
    }

    private Long getCurrentUserId() {
        return 1L;
    }
}
