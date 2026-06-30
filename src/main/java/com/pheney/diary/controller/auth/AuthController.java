package com.pheney.diary.controller.auth;

import com.pheney.diary.common.R;
import com.pheney.diary.dto.request.WxLoginRequest;
import com.pheney.diary.dto.request.UpdateUserInfoRequest;
import com.pheney.diary.dto.response.LoginResponse;
import com.pheney.diary.dto.response.UserInfoResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    /**
     * 微信登录
     */
    @PostMapping("/wx-login")
    public R<LoginResponse> wxLogin(@RequestBody WxLoginRequest request) {
        return R.success();
    }
}
