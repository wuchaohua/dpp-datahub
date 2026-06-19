package io.batterypass.common.model;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class PowerCapabilityAt {
    private Double atSoC;
    private Double powerCapabilityAt;
}