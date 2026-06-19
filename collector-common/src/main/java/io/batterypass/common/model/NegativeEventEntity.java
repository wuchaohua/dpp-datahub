package io.batterypass.common.model;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class NegativeEventEntity {
    private String negativeEvent;
    private java.time.OffsetDateTime timestamp;
    private String severity;
}
