package io.batterypass.dynamicdata.adapter;

import io.batterypass.common.dto.DataPoint;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

@Slf4j
@Component
public class MqttAdapter implements MqttCallback {
    private MqttClient client;

    @Value("${mqtt.broker.url:tcp://localhost:1883}")
    private String brokerUrl;

    @Value("${mqtt.broker.client-id:dynamic-data-service}")
    private String clientId;

    @Value("${mqtt.topics.telemetry:/bms/+/telemetry}")
    private String telemetryTopic;

    @Value("${mqtt.topics.alarm:/bms/+/alarm}")
    private String alarmTopic;

    @Value("${mqtt.qos:1}")
    private int qos;

    private Consumer<DataPoint> dataPointHandler;

    @PostConstruct
    public void connect() {
        try {
            client = new MqttClient(brokerUrl, clientId, new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            options.setKeepAliveInterval(30);
            client.setCallback(this);
            client.connect(options);
            client.subscribe(telemetryTopic, qos);
            client.subscribe(alarmTopic, qos);
            log.info("MQTT connected to {} and subscribed to {} / {}", brokerUrl, telemetryTopic, alarmTopic);
        } catch (MqttException e) {
            log.error("MQTT connection failed: {}", e.getMessage());
        }
    }

    @PreDestroy
    public void disconnect() {
        if (client != null && client.isConnected()) {
            try {
                client.disconnect();
                client.close();
            } catch (MqttException e) {
                log.warn("MQTT disconnect error: {}", e.getMessage());
            }
        }
    }

    public void setDataPointHandler(Consumer<DataPoint> handler) {
        this.dataPointHandler = handler;
    }

    @Override
    public void connectionLost(Throwable cause) {
        log.warn("MQTT connection lost: {}", cause.getMessage());
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        try {
            String payload = new String(message.getPayload(), StandardCharsets.UTF_8);
            String batteryId = extractBatteryId(topic);
            if (dataPointHandler != null) {
                DataPoint dp = DataPoint.builder()
                        .batteryId(batteryId)
                        .paramName(topic.contains("alarm") ? "alarm" : "telemetry")
                        .paramValue(0.0)
                        .timestamp(System.currentTimeMillis())
                        .quality(1)
                        .build();
                dataPointHandler.accept(dp);
            }
            log.debug("MQTT message from {}: {}", topic, payload.length() > 200 ? payload.substring(0, 200) + "..." : payload);
        } catch (Exception e) {
            log.error("Error processing MQTT message: {}", e.getMessage());
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // Not used for subscriber
    }

    private String extractBatteryId(String topic) {
        String[] parts = topic.split("/");
        if (parts.length >= 3) return parts[2];
        return "unknown";
    }
}
