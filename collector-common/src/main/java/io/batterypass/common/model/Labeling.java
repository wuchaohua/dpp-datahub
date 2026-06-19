package io.batterypass.common.model;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Labeling {
    private String labelingSubject;
    private List<LabelingDocument> documents;
}