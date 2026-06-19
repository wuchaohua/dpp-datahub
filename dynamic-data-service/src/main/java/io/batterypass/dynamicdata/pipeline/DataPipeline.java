package io.batterypass.dynamicdata.pipeline;

import io.batterypass.common.dto.DataPoint;
import io.batterypass.dynamicdata.store.TDengineStore;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.concurrent.*;

@Slf4j
@Component
public class DataPipeline {
    private final TDengineStore tdengineStore;
    private final BlockingQueue<DataPoint> queue = new LinkedBlockingQueue<>(10000);
    private volatile boolean running = true;

    public DataPipeline(TDengineStore tdengineStore) {
        this.tdengineStore = tdengineStore;
    }

    public void ingest(DataPoint dataPoint) {
        if (!queue.offer(dataPoint)) {
            log.warn("Pipeline queue full, dropping data point for battery {}", dataPoint.getBatteryId());
        }
    }

    @PostConstruct
    public void start() {
        Thread worker = new Thread(this::processLoop, "pipeline-worker");
        worker.setDaemon(true);
        worker.start();
        log.info("Data pipeline started");
    }

    private void processLoop() {
        while (running) {
            try {
                DataPoint dp = queue.poll(1, TimeUnit.SECONDS);
                if (dp != null) {
                    tdengineStore.insertDataPoint(
                            dp.getBatteryId(), dp.getParamName(),
                            dp.getParamValue(), dp.getTimestamp(),
                            dp.getQuality(), dp.getUnit());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
                log.error("Pipeline processing error: {}", e.getMessage());
            }
        }
    }

    public void shutdown() {
        running = false;
        log.info("Data pipeline shutting down");
    }
}
