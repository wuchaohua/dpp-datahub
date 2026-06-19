package io.batterypass.common.dto;

import lombok.*;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatteryPassportResponse {
    private String batteryId;
    private String batteryPassportId;
    private Map<String, Object> generalProductInformation;
    private Map<String, Object> carbonFootprint;
    private Map<String, Object> circularity;
    private Map<String, Object> materialComposition;
    private Map<String, Object> performanceAndDurability;
    private Map<String, Object> labeling;
    private Map<String, Object> supplyChainDueDiligence;
    private Map<String, Object> batteryCondition;
    private List<Map<String, Object>> dynamicHistory;
    private String dataVersion;
    private String generatedAt;
}
