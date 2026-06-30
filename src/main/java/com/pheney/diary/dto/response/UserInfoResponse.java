package com.pheney.diary.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserInfoResponse {
    private Long id;
    private String openid;
    private String nickname;
    private String avatar;
    private LocalDateTime createdAt;
}
