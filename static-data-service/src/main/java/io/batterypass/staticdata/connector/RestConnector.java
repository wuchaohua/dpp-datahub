package io.batterypass.staticdata.connector;

import io.batterypass.common.connector.Connector;
import io.batterypass.common.model.BatteryPassportData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Slf4j
@Component
public class RestConnector implements Connector {
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String getConnectorType() { return "REST"; }

    @Override
    public BatteryPassportData fetch(String batteryId, Map<String, Object> config) {
        String url = config.get("endpointUrl") + "/" + batteryId;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (config.containsKey("apiKey")) {
            headers.set("Authorization", "Bearer " + config.get("apiKey"));
        }
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, String.class);
            log.info("REST connector fetched data for battery {}: status {}", batteryId, response.getStatusCode());
            // Parse response to BatteryPassportData via mapping engine
            return BatteryPassportData.builder().batteryId(batteryId).build();
        } catch (Exception e) {
            log.error("REST connector failed for battery {}: {}", batteryId, e.getMessage());
            throw new io.batterypass.common.exception.ConnectorException(
                    "REST fetch failed for " + batteryId + ": " + e.getMessage(), e);
        }
    }

    @Override
    public boolean healthCheck(Map<String, Object> config) {
        String url = (String) config.getOrDefault("endpointUrl", "");
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url + "/health", HttpMethod.GET, null, String.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }
}
