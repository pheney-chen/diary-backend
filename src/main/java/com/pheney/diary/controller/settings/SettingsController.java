package com.pheney.diary.controller.settings;

import com.pheney.diary.common.R;
import com.pheney.diary.dto.request.UpdateSettingsRequest;
import com.pheney.diary.dto.response.SettingsResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/settings")
public class SettingsController {

    /**
     * 获取用户设置
     */
    @GetMapping
    public R<SettingsResponse> getSettings() {
        return R.success();
    }

    /**
     * 更新用户设置
     */
    @PutMapping
    public R<SettingsResponse> updateSettings(@RequestBody UpdateSettingsRequest request) {
        return R.success();
    }
}
