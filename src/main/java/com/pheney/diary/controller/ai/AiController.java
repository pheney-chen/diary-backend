package com.pheney.diary.controller.ai;

import com.pheney.diary.common.R;
import com.pheney.diary.dto.request.AiAnalyzeRequest;
import com.pheney.diary.dto.response.AiAnalyzeResponse;
import com.pheney.diary.dto.response.AiModeResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    /**
     * 获取 AI 模式列表
     */
    @GetMapping("/modes")
    public R<List<AiModeResponse>> getModes() {
        return R.success();
    }

    /**
     * AI 分析日记
     */
    @PostMapping("/analyze")
    public R<AiAnalyzeResponse> analyze(@RequestBody AiAnalyzeRequest request) {
        return R.success();
    }

    /**
     * AI 对话历史
     */
    @GetMapping("/history/{diaryId}")
    public R<List<AiAnalyzeResponse>> getHistory(@PathVariable Long diaryId) {
        return R.success();
    }
}
