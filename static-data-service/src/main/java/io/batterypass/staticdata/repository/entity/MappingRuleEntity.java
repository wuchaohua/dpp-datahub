package io.batterypass.staticdata.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "mapping_rule")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MappingRuleEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(name = "source_system", nullable = false)
    private String sourceSystem;
    @Column(name = "target_model", nullable = false)
    private String targetModel;
    @Column(name = "rule_definition", nullable = false, columnDefinition = "jsonb")
    private String ruleDefinition;
    @Column(name = "validation_schema", columnDefinition = "text")
    private String validationSchema;
    @Column(nullable = false)
    private String version = "1.0";
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @PrePersist
    protected void onCreate() { createdAt = OffsetDateTime.now(); }
}
