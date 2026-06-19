package io.batterypass.dynamicdata.adapter;

import io.batterypass.common.dto.DataPoint;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import java.util.function.Consumer;

@Slf4j
@Component
@ConditionalOnProperty(name = "modbus.enabled", havingValue = "true", matchIfMissing = false)
public class ModbusAdapter {
    private Consumer<DataPoint> dataPointHandler;

    public void setDataPointHandler(Consumer<DataPoint> handler) {
        this.dataPointHandler = handler;
    }

    @PostConstruct
    public void init() {
        log.info("Modbus adapter placeholder initialized");
    }
}
