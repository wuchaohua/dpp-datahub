package io.batterypass.common.dto;

import lombok.*;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataPoint {
    private String batteryId;
    private String paramName;
    private Double paramValue;
    private Long timestamp;
    private Integer quality;          // 0=bad, 1=good
    private String unit;
    private Map<String, String> tags; // battery_type, manufacturer, etc.
}
