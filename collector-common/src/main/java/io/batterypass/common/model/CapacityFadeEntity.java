package io.batterypass.common.model;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CapacityFadeEntity {
    private Double capacityFadeValue;
    private java.time.OffsetDateTime lastUpdated;
}
