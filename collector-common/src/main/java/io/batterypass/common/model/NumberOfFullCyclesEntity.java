package io.batterypass.common.model;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class NumberOfFullCyclesEntity {
    private Long numberOfFullCyclesValue;
    private java.time.OffsetDateTime lastUpdated;
}
