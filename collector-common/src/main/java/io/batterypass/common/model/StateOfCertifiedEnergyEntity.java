package io.batterypass.common.model;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class StateOfCertifiedEnergyEntity {
    private Double stateOfCertifiedEnergyValue;
    private java.time.OffsetDateTime lastUpdated;
}
