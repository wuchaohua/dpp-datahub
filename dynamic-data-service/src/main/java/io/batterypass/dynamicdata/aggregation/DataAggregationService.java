package io.batterypass.dynamicdata.aggregation;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataAggregationService {

    @PostConstruct
    public void init() {
        log.info("Data aggregation service initialized (TDengine continuous queries)");
    }
}
