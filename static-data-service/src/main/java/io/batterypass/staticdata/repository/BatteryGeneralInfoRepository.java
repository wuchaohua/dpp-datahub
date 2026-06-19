package io.batterypass.staticdata.repository;

import io.batterypass.staticdata.repository.entity.BatteryGeneralInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BatteryGeneralInfoRepository extends JpaRepository<BatteryGeneralInfoEntity, Long> {
    Optional<BatteryGeneralInfoEntity> findByProductIdentifier(String productIdentifier);
    Optional<BatteryGeneralInfoEntity> findByBatteryPassportId(String batteryPassportId);
}