package io.batterypass.dynamicdata.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import javax.sql.DataSource;
import java.sql.Timestamp;

@Slf4j
@Repository
public class TDengineStore {
    private final JdbcTemplate jdbcTemplate;

    public TDengineStore(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insertDataPoint(String batteryId, String paramName, double value, long ts, int quality, String unit) {
        try {
            jdbcTemplate.update(
                "INSERT INTO battery_dynamic_raw (ts, battery_id, param_name, param_value, quality, unit) VALUES (?, ?, ?, ?, ?, ?)",
                new Timestamp(ts), batteryId, paramName, value, quality, unit);
        } catch (Exception e) {
            log.warn("TDengine insert failed: {}", e.getMessage());
        }
    }
}