package io.batterypass.staticdata.connector;

import io.batterypass.common.connector.Connector;
import io.batterypass.common.model.BatteryPassportData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.Map;

@Slf4j
@Component
public class JdbcConnector implements Connector {
    @Override
    public String getConnectorType() { return "JDBC"; }

    @Override
    public BatteryPassportData fetch(String batteryId, Map<String, Object> config) {
        log.info("JDBC connector: would query {} for battery {}", config.get("endpointUrl"), batteryId);
        throw new UnsupportedOperationException("JDBC connector not yet implemented");
    }

    @Override
    public boolean healthCheck(Map<String, Object> config) { return false; }
}
