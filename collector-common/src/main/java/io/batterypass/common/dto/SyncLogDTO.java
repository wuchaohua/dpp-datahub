package io.batterypass.common.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SyncLogDTO {
    private Long id;
    private String dataSourceId;
    private String batteryId;
    private String targetModel;
    private String status;             // SUCCESS/FAILED/VALIDATION_ERROR
    private String errorMessage;
    private Integer retryCount;
    private String startedAt;
    private String completedAt;
}
