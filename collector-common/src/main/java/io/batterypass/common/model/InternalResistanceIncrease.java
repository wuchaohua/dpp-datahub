package io.batterypass.common.model;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class InternalResistanceIncrease {
    private Double internalResistanceIncreaseValue;
    private BatteryComponent batteryComponent;
    private java.time.OffsetDateTime lastUpdated;
}
