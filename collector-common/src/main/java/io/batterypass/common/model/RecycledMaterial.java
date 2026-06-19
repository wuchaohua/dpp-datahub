package io.batterypass.common.model;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RecycledMaterial {
    private String materialName;
    private String materialIdentifier;
}