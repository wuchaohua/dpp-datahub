package io.batterypass.staticdata.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "battery_general_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatteryGeneralInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_identifier", nullable = false, unique = true)
    private String productIdentifier;
    @Column(name = "battery_passport_id", nullable = false, unique = true)
    private String batteryPassportId;
    @Column(name = "battery_category", nullable = false)
    private String batteryCategory;
    @Column(name = "battery_status", nullable = false)
    private String batteryStatus;
    @Column(name = "battery_mass", nullable = false)
    private Float batteryMass;
    @Column(name = "manufacturing_date", nullable = false)
    private OffsetDateTime manufacturingDate;
    @Column(name = "putting_into_service")
    private OffsetDateTime puttingIntoService;
    @Column(name = "warranty_period")
    private String warrantyPeriod;
    @Column(name = "manufacturer_info", columnDefinition = "jsonb")
    private String manufacturerInfo;
    @Column(name = "operator_info", columnDefinition = "jsonb")
    private String operatorInfo;
    @Column(name = "manufacturing_place", columnDefinition = "jsonb")
    private String manufacturingPlace;
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
    @PrePersist
    protected void onCreate() { createdAt = OffsetDateTime.now(); updatedAt = OffsetDateTime.now(); }
    @PreUpdate
    protected void onUpdate() { updatedAt = OffsetDateTime.now(); }
}
