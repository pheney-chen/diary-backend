package com.pheney.diary.controller.tag;

import com.pheney.diary.common.PageResult;
import com.pheney.diary.common.R;
import com.pheney.diary.dto.request.RenameTagRequest;
import com.pheney.diary.dto.response.DiaryResponse;
import com.pheney.diary.dto.response.TagResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    /**
     * 获取所有标签
     */
    @GetMapping
    public R<List<TagResponse>> getAllTags() {
        return R.success();
    }

    /**
     * 获取标签下的日记
     */
    @GetMapping("/{name}/diaries")
    public R<PageResult<DiaryResponse>> getTagDiaries(
            @PathVariable String name,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int pageSize) {
        return R.success();
    }

    /**
     * 重命名标签
     */
    @PutMapping("/{name}")
    public R<Void> renameTag(@PathVariable String name, @RequestBody RenameTagRequest request) {
        return R.success();
    }

    /**
     * 删除标签
     */
    @DeleteMapping("/{name}")
    public R<Void> deleteTag(@PathVariable String name) {
        return R.success();
    }
}
