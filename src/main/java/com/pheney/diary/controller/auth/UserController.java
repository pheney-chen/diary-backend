package com.pheney.diary.controller.auth;

import com.pheney.diary.common.R;
import com.pheney.diary.dto.request.UpdateUserInfoRequest;
import com.pheney.diary.dto.response.UserInfoResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    /**
     * 获取用户信息
     */
    @GetMapping("/info")
    public R<UserInfoResponse> getUserInfo() {
        return R.success();
    }

    /**
     * 更新用户信息
     */
    @PutMapping("/info")
    public R<UserInfoResponse> updateUserInfo(@RequestBody UpdateUserInfoRequest request) {
        return R.success();
    }
}
