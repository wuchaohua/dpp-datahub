package io.batterypass.staticdata.repository;

import io.batterypass.staticdata.repository.entity.DataSourceConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DataSourceConfigRepository extends JpaRepository<DataSourceConfigEntity, Long> {
    List<DataSourceConfigEntity> findByEnabledTrue();
    List<DataSourceConfigEntity> findBySystemType(String systemType);
}