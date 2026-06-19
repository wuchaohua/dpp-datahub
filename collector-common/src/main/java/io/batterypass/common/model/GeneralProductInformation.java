package io.batterypass.common.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeneralProductInformation {
    @NotNull
    private String productIdentifier;
    @NotNull
    private String batteryPassportIdentifier;
    @NotNull
    private BatteryCategoryEnum batteryCategory;
    @NotNull
    private ContactInformationEntity manufacturerInformation;
    @NotNull
    private java.time.OffsetDateTime manufacturingDate;
    @NotNull
    private BatteryStatusEnumeration batteryStatus;
    @NotNull
    private Float batteryMass;
    @NotNull
    private PostalAddressEntity manufacturingPlace;
    @NotNull
    private ContactInformationEntity operatorInformation;
    private java.time.OffsetDateTime puttingIntoService;
    private String warrantyPeriod;
}
