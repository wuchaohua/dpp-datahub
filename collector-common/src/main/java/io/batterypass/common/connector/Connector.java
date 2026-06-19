package io.batterypass.common.connector;

import io.batterypass.common.model.BatteryPassportData;
import java.util.Map;

public interface Connector {
    String getConnectorType();
    BatteryPassportData fetch(String batteryId, Map<String, Object> config);
    boolean healthCheck(Map<String, Object> config);
}
