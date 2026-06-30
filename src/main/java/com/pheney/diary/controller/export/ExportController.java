package com.pheney.diary.controller.export;

import com.pheney.diary.common.R;
import com.pheney.diary.dto.request.ExportRequest;
import com.pheney.diary.dto.response.ExportResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/export")
public class ExportController {

    /**
     * 导出数据
     */
    @PostMapping
    public R<ExportResponse> exportData(@RequestBody ExportRequest request) {
        return R.success();
    }
}
