package com.pheney.diary.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class ExportResponse {
    private String downloadUrl;
    private String filename;
}
