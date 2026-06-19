package io.batterypass.common.model;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RemainingPowerCapabilityDynamic {
    private Double atSoC;
    private Double powerCapabilityAt;
    private java.time.OffsetDateTime lastUpdated;
}
