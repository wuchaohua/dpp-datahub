package io.batterypass.common.dto;

import io.batterypass.common.model.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatteryPassportDTO {
    private String batteryId;
    private String batteryPassportId;
    private GeneralProductInformation generalProductInformation;
    private CarbonFootprintForBatteries carbonFootprint;
    private Circularity circularity;
    private MaterialComposition materialComposition;
    private Labeling labeling;
    private SupplyChainDueDiligence supplyChainDueDiligence;
    private PerformanceAndDurability performanceAndDurability;
    private BatteryConditionEntity batteryCondition;
}
