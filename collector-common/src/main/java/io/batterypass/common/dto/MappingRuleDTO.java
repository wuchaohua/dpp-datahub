package io.batterypass.common.dto;

import lombok.*;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MappingRuleDTO {
    private String ruleId;
    private String sourceSystem;
    private String targetModel;
    private Map<String, Object> mappings;
    private String validationSchema;
    private String version;
}
