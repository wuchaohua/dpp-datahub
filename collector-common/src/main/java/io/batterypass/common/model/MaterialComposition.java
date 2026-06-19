package io.batterypass.common.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialComposition {
    @NotNull
    private BatteryChemistryEntity batteryChemistry;
    private List<BatteryMaterial> batteryMaterials;
    private List<HazardousSubstance> hazardousSubstances;
}