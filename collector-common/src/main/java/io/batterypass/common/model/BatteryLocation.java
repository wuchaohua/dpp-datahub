package io.batterypass.common.model;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BatteryLocation {
    private String componentName;
    private String componentId;
}