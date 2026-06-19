package io.batterypass.common.model;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RemainingCapacityEntity {
    private Double remainingCapacityValue;
    private java.time.OffsetDateTime lastUpdated;
}
