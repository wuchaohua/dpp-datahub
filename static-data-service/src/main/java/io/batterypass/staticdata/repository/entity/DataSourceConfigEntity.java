package io.batterypass.staticdata.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "data_source_config")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataSourceConfigEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(name = "system_type", nullable = false)
    private String systemType;
    @Column(name = "connector_type", nullable = false)
    private String connectorType;
    @Column(name = "endpoint_url", nullable = false, columnDefinition = "text")
    private String endpointUrl;
    @Column(name = "auth_type")
    private String authType;
    @Column(name = "auth_config", columnDefinition = "jsonb")
    private String authConfig;
    @Column(name = "mapping_rule_id")
    private Long mappingRuleId;
    @Column(name = "schedule_cron")
    private String scheduleCron;
    @Column(nullable = false)
    private Boolean enabled = true;
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @PrePersist
    protected void onCreate() { createdAt = OffsetDateTime.now(); }
}
