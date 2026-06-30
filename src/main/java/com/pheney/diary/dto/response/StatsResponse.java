package com.pheney.diary.dto.response;

import lombok.Data;
import java.util.Map;

@Data
public class StatsResponse {
    private Integer total;
    private Integer thisMonth;
    private Integer consecutiveDays;
    private Map<String, Integer> types;
}
