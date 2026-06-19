package io.batterypass.staticdata.connector;

import io.batterypass.common.connector.Connector;
import io.batterypass.common.exception.ConnectorException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.*;

@Slf4j
@Component
public class ConnectorFactory {
    private final Map<String, Connector> connectorMap = new HashMap<>();
    private final List<Connector> connectors;

    public ConnectorFactory(List<Connector> connectors) {
        this.connectors = connectors;
    }

    @PostConstruct
    public void init() {
        for (Connector c : connectors) {
            connectorMap.put(c.getConnectorType().toUpperCase(), c);
            log.info("Registered connector: {}", c.getConnectorType());
        }
    }

    public Connector getConnector(String type) {
        Connector c = connectorMap.get(type.toUpperCase());
        if (c == null) {
            throw new ConnectorException("No connector found for type: " + type);
        }
        return c;
    }

    public List<String> getAvailableTypes() {
        return new ArrayList<>(connectorMap.keySet());
    }
}
