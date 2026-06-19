package io.batterypass.common.dto;

import lombok.*;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataSourceConfigDTO {
    private Long id;
    private String name;
    private String systemType;          // SRM/ERP/PLM/MES/BMS/EMS
    private String connectorType;       // REST/SOAP/JDBC/MQTT/OPCUA/MODBUS
    private String endpointUrl;
    private String authType;            // BASIC/OAUTH2/APIKEY/NONE
    private String mappingRuleId;
    private String scheduleCron;
    private Boolean enabled;
}
