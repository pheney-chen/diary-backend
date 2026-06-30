package com.pheney.diary.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class LoginResponse {
    private String token;
    private UserInfoResponse user;
}
