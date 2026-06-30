package com.pheney.diary.controller.stats;

import com.pheney.diary.common.R;
import com.pheney.diary.dto.response.StatsResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    /**
     * 获取统计数据
     */
    @GetMapping("/overview")
    public R<StatsResponse> getOverview() {
        return R.success();
    }
}
