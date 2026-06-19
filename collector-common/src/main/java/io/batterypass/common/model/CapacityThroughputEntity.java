package io.batterypass.common.model;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CapacityThroughputEntity {
    private Double capacityThroughputValue;
    private java.time.OffsetDateTime lastUpdated;
}
