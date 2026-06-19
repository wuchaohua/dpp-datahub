package io.batterypass.common.model;
import lombok.*;
import java.util.List;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class SparePartSupplier {
    private String nameOfSupplier;
    private String emailAddressOfSupplier;
    private String supplierWebAddress;
    private PostalAddressEntity addressOfSupplier;
    private List<SparePartComponent> components;
}