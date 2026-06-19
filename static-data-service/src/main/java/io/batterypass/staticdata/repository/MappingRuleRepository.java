package io.batterypass.staticdata.repository;

import io.batterypass.staticdata.repository.entity.MappingRuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MappingRuleRepository extends JpaRepository<MappingRuleEntity, Long> {
    Optional<MappingRuleEntity> findByName(String name);
    Optional<MappingRuleEntity> findBySourceSystemAndTargetModel(String sourceSystem, String targetModel);
}