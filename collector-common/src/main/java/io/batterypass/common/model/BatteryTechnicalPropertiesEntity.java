package io.batterypass.common.model;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatteryTechnicalPropertiesEntity {
    private Double ratedCapacity;
    private Double ratedEnergy;
    private Double nominalVoltage;
    private Double maximumVoltage;
    private Double minimumVoltage;
    private Double ratedMaximumPower;
    private Double powerCapabilityRatio;
    private List<InitialInternalResistance> initialInternalResistance;
    private Double cRate;
    private String lifetimeReferenceTest;
    private Integer expectedLifetime;
    private Integer expectedNumberOfCycles;
    private Double capacityThresholdForExhaustion;
    private Double temperatureRangeIdleState;
    private Double initialSelfDischarge;
    private Double roundtripEfficiency;
    private Double cRateLifeCycleTest;
    private List<PowerCapabilityAt> originalPowerCapability;
}