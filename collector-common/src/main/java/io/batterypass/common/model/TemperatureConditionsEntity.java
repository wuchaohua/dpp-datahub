package io.batterypass.common.model;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class TemperatureConditionsEntity {
    private Double timeExtremeHighTemp;
    private Double timeExtremeLowTemp;
    private Double timeExtremeHighTempCharging;
    private Double timeExtremeLowTempCharging;
    private java.time.OffsetDateTime lastUpdated;
}
