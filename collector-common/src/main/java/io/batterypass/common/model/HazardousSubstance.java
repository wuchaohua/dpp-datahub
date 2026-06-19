package io.batterypass.common.model;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class HazardousSubstance {
    private String hazardousSubstanceName;
    private String hazardousSubstanceIdentifier;
    private HazardousSubstanceClass hazardousSubstanceClass;
    private Double hazardousSubstanceConcentration;
    private BatteryLocation hazardousSubstanceLocation;
}