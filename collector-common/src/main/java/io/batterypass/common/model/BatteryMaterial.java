package io.batterypass.common.model;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BatteryMaterial {
    private String batteryMaterialName;
    private Double batteryMaterialMass;
    private Boolean isCriticalRawMaterial;
    private BatteryLocation batteryMaterialLocation;
}