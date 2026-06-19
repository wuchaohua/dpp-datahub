package io.batterypass.common.model;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class EvolutionOfSelfDischargeEntity {
    private Double evolutionOfSelfDischargeValue;
    private java.time.OffsetDateTime lastUpdated;
}
