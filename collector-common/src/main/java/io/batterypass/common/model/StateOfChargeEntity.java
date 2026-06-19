package io.batterypass.common.model;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class StateOfChargeEntity {
    private Double stateOfChargeValue;
    private java.time.OffsetDateTime lastUpdated;
}
