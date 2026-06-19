package io.batterypass.dynamicdata;

import io.batterypass.dynamicdata.adapter.MqttAdapter;
import io.batterypass.dynamicdata.pipeline.DataPipeline;
import io.batterypass.dynamicdata.web.DynamicDataController;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DynamicDataService {
    private final MqttAdapter mqttAdapter;
    private final DataPipeline dataPipeline;

    @PostConstruct
    public void init() {
        // Wire MQTT adapter to pipeline
        mqttAdapter.setDataPointHandler(dataPipeline::ingest);
        log.info("Dynamic data service initialized: MQTT -> Pipeline -> TDengine");
    }
}
