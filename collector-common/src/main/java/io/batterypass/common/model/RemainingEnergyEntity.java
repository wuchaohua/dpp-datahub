package io.batterypass.common.model;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RemainingEnergyEntity {
    private Double remainingEnergyValue;
    private java.time.OffsetDateTime lastUpdated;
}
