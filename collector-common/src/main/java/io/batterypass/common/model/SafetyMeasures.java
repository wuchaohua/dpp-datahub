package io.batterypass.common.model;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class SafetyMeasures {
    private String safetyInstructions;
    private java.util.List<String> extinguishingAgent;
}