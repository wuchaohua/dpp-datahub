package io.batterypass.common.model;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class LabelingDocument {
    private DocumentType documentType;
    private String documentURL;
    private String mimeType;
}