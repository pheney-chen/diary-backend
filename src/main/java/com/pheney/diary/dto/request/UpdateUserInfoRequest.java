package com.pheney.diary.dto.request;

import lombok.Data;

@Data
public class UpdateUserInfoRequest {
    private String nickname;
    private String avatar;
}
