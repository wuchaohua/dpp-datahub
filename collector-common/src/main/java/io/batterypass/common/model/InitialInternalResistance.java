package io.batterypass.common.model;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class InitialInternalResistance {
    private Double ohmicResistance;
    private BatteryComponent batteryComponent;
}