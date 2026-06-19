package io.batterypass.common.model;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatteryConditionEntity {
    private StateOfChargeEntity stateOfCharge;
    private Double energyThroughput;
    private CapacityThroughputEntity capacityThroughput;
    private NumberOfFullCyclesEntity numberOfFullCycles;
    private StateOfCertifiedEnergyEntity stateOfCertifiedEnergy;
    private CapacityFadeEntity capacityFade;
    private RemainingEnergyEntity remainingEnergy;
    private RemainingCapacityEntity remainingCapacity;
    private List<NegativeEventEntity> negativeEvents;
    private TemperatureConditionsEntity temperatureInformation;
    private List<RemainingPowerCapabilityDynamic> remainingPowerCapability;
    private Float powerFade;
    private Float roundTripEfficiencyFade;
    private EvolutionOfSelfDischargeEntity evolutionOfSelfDischarge;
    private CurrentSelfDischargingRateEntity currentSelfDischargingRate;
    private List<InternalResistanceIncrease> internalResistanceIncrease;
    private Float roundTripEfficiencyAt50PctCycleLife;
    private RemainingRoundTripEnergyEfficiencyEntity remainingRoundTripEnergyEfficiency;
    private java.time.OffsetDateTime lastUpdated;
}