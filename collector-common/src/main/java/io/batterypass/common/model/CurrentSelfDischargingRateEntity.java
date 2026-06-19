package io.batterypass.common.model;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CurrentSelfDischargingRateEntity {
    private Double currentSelfDischargingRateValue;
    private java.time.OffsetDateTime lastUpdated;
}
