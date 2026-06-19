package io.batterypass.common.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerformanceAndDurability {
    @NotNull
    private BatteryTechnicalPropertiesEntity batteryTechnicalProperties;
    @NotNull
    private BatteryConditionEntity batteryCondition;
}