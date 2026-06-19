package io.batterypass.staticdata.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "data_sync_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataSyncLogEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "data_source_id")
    private Long dataSourceId;
    @Column(name = "battery_id")
    private String batteryId;
    @Column(name = "target_model")
    private String targetModel;
    @Column(nullable = false)
    private String status;
    @Column(name = "error_message", columnDefinition = "text")
    private String errorMessage;
    @Column(name = "raw_payload", columnDefinition = "text")
    private String rawPayload;
    @Column(name = "processed_payload", columnDefinition = "jsonb")
    private String processedPayload;
    @Column(name = "started_at", nullable = false)
    private OffsetDateTime startedAt;
    @Column(name = "completed_at")
    private OffsetDateTime completedAt;
    @Column(name = "retry_count")
    private Integer retryCount = 0;
}
