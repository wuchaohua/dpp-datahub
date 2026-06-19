package io.batterypass.staticdata.repository;

import io.batterypass.staticdata.repository.entity.DataSyncLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DataSyncLogRepository extends JpaRepository<DataSyncLogEntity, Long> {
    List<DataSyncLogEntity> findByBatteryIdOrderByStartedAtDesc(String batteryId);
    List<DataSyncLogEntity> findByStatusOrderByStartedAtDesc(String status);
}