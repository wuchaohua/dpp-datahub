package io.batterypass.common.model;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Circularity {
    private Double renewableContent;
    private List<RecycledContent> recycledContent;
    private EndOfLifeInformation endOfLifeInformation;
    private SafetyMeasures safetyMeasures;
    private List<SparePartSupplier> sparePartSources;
}