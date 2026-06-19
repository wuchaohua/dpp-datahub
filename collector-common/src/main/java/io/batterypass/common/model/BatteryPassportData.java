package io.batterypass.common.model;

import lombok.*;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatteryPassportData {
    private String batteryId;
    private String batteryPassportId;

    // 7 submodels from Battery Pass Data Model v1.2.0
    private GeneralProductInformation generalProductInformation;
    private CarbonFootprintForBatteries carbonFootprint;
    private Circularity circularity;
    private MaterialComposition materialComposition;
    private Labeling labeling;
    private SupplyChainDueDiligence supplyChainDueDiligence;
    private PerformanceAndDurability performanceAndDurability;

    // Dynamic snapshot
    private BatteryConditionEntity batteryCondition;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private String dataVersion;
}
