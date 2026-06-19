package io.batterypass.common.model;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RemainingRoundTripEnergyEfficiencyEntity {
    private Double remainingRoundTripEnergyEfficiencyValue;
    private java.time.OffsetDateTime lastUpdated;
}
