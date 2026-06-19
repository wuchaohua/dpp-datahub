package io.batterypass.dynamicdata.anomaly;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AnomalyDetector {

    @PostConstruct
    public void init() {
        log.info("Anomaly detector initialized (rules engine + statistical detection)");
    }
}
