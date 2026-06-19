package io.batterypass.staticdata.scheduler;

import io.batterypass.staticdata.repository.DataSourceConfigRepository;
import io.batterypass.staticdata.repository.entity.DataSourceConfigEntity;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataSyncScheduler {

    private final DataSourceConfigRepository dataSourceConfigRepository;

    @PostConstruct
    public void init() {
        List<DataSourceConfigEntity> enabledSources = dataSourceConfigRepository.findByEnabledTrue();
        log.info("Found {} enabled data sources for scheduling", enabledSources.size());
        for (DataSourceConfigEntity source : enabledSources) {
            if (source.getScheduleCron() != null && !source.getScheduleCron().isEmpty()) {
                log.info("Would schedule: {} with cron '{}'", source.getName(), source.getScheduleCron());
            }
        }
    }
}
