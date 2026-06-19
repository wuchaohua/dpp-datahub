package io.batterypass.common.model;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplyChainDueDiligence {
    private List<String> thirdPartyVerifications;
    private List<String> sustainabilityReports;
    private List<String> supplyChainDocuments;
}
