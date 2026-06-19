package io.batterypass.common.model;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RecycledContent {
    private RecycledMaterial recycledMaterial;
    private Double preConsumerShare;
    private Double postConsumerShare;
}