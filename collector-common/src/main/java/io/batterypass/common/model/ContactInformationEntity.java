package io.batterypass.common.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactInformationEntity {
    @NotNull
    private String identifier;
    private String contactName;
    @NotNull
    private PostalAddressEntity postalAddress;
    private String emailAddress;
    private String webAddress;
}
