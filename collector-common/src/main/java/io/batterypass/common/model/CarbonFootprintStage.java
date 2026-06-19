package io.batterypass.common.model;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CarbonFootprintStage {
    private LifecycleStage lifecycleStage;
    private Double carbonFootprint;
}