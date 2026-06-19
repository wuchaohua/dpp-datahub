package io.batterypass.common.model;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class SparePartComponent {
    private String partName;
    private String partNumber;
}