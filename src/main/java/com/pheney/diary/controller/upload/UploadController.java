package com.pheney.diary.controller.upload;

import com.pheney.diary.common.R;
import com.pheney.diary.dto.response.UploadResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

    /**
     * 上传图片
     */
    @PostMapping("/image")
    public R<List<UploadResponse>> uploadImage(@RequestParam("file") List<MultipartFile> files) {
        return R.success();
    }

    /**
     * 上传语音
     */
    @PostMapping("/voice")
    public R<UploadResponse> uploadVoice(@RequestParam("file") MultipartFile file) {
        return R.success();
    }

    /**
     * 上传视频
     */
    @PostMapping("/video")
    public R<UploadResponse> uploadVideo(@RequestParam("file") MultipartFile file) {
        return R.success();
    }

    /**
     * 获取上传凭证
     */
    @GetMapping("/token")
    public R<String> getUploadToken() {
        return R.success();
    }
}
