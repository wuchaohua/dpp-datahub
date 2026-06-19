package io.batterypass.common.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostalAddressEntity {
    @NotNull
    private String addressCountry;
    @NotNull
    private String streetAddress;
    @NotNull
    private String postalCode;
    private String city;
}
