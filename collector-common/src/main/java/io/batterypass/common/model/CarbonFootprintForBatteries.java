package io.batterypass.common.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarbonFootprintForBatteries {
    private Double absoluteCarbonFootprint;
    private Double batteryCarbonFootprint;
    private String carbonFootprintPerformanceClass;
    private String carbonFootprintStudy;
    @NotNull
    private List<CarbonFootprintStage> carbonFootprintPerLifecycleStage;
}