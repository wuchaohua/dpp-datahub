package io.batterypass.staticdata;

import io.batterypass.common.dto.ApiResponse;
import io.batterypass.common.dto.BatteryPassportDTO;
import io.batterypass.common.dto.DataSourceConfigDTO;
import io.batterypass.common.dto.MappingRuleDTO;
import io.batterypass.common.exception.ConnectorException;
import io.batterypass.common.exception.MappingException;
import io.batterypass.common.exception.ValidationException;
import io.batterypass.common.model.*;
import io.batterypass.common.util.JsonUtils;
import io.batterypass.staticdata.connector.ConnectorFactory;
import io.batterypass.staticdata.mapping.MappingEngine;
import io.batterypass.staticdata.repository.*;
import io.batterypass.staticdata.repository.entity.*;
import io.batterypass.staticdata.validation.DataValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.OffsetDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class StaticDataService {
    private final ConnectorFactory connectorFactory;
    private final MappingEngine mappingEngine;
    private final DataValidator dataValidator;
    private final BatteryGeneralInfoRepository generalInfoRepo;
    private final DataSourceConfigRepository configRepo;
    private final MappingRuleRepository mappingRuleRepo;
    private final DataSyncLogRepository syncLogRepo;

    @Transactional
    public BatteryPassportData collectBatteryData(String batteryId, Long dataSourceId) {
        DataSourceConfigEntity config = configRepo.findById(dataSourceId)
                .orElseThrow(() -> new ConnectorException("Data source not found: " + dataSourceId));
        Map<String, Object> connConfig = buildConnectorConfig(config);
        var syncLog = DataSyncLogEntity.builder()
                .dataSourceId(dataSourceId).batteryId(batteryId)
                .targetModel(config.getSystemType()).status("IN_PROGRESS")
                .startedAt(OffsetDateTime.now()).retryCount(0).build();
        syncLog = syncLogRepo.save(syncLog);
        try {
            var rawData = connectorFactory.getConnector(config.getConnectorType())
                    .fetch(batteryId, connConfig);
            syncLog.setRawPayload(JsonUtils.toJson(rawData));
            syncLogRepo.save(syncLog);
            syncLog.setProcessedPayload(JsonUtils.toJson(rawData));
            syncLog.setStatus("SUCCESS");
            syncLog.setCompletedAt(OffsetDateTime.now());
            syncLogRepo.save(syncLog);
            return rawData;
        } catch (Exception e) {
            syncLog.setStatus("FAILED");
            syncLog.setErrorMessage(e.getMessage());
            syncLog.setCompletedAt(OffsetDateTime.now());
            syncLogRepo.save(syncLog);
            throw e;
        }
    }

    @Transactional
    public BatteryPassportDTO ingestStaticData(BatteryPassportDTO dto) {
        if (dto.getGeneralProductInformation() != null) {
            var info = dto.getGeneralProductInformation();
            var entity = BatteryGeneralInfoEntity.builder()
                    .productIdentifier(info.getProductIdentifier())
                    .batteryPassportId(info.getBatteryPassportIdentifier())
                    .batteryCategory(info.getBatteryCategory().getCode())
                    .batteryStatus(info.getBatteryStatus().getValue())
                    .batteryMass(info.getBatteryMass())
                    .manufacturingDate(info.getManufacturingDate())
                    .puttingIntoService(info.getPuttingIntoService())
                    .warrantyPeriod(info.getWarrantyPeriod())
                    .manufacturerInfo(JsonUtils.toJson(info.getManufacturerInformation()))
                    .operatorInfo(JsonUtils.toJson(info.getOperatorInformation()))
                    .manufacturingPlace(JsonUtils.toJson(info.getManufacturingPlace()))
                    .build();
            generalInfoRepo.save(entity);
        }
        // Log the ingestion
        log.info("Ingested static data for battery: {}", dto.getBatteryId());
        return dto;
    }

    @Transactional(readOnly = true)
    public BatteryPassportDTO getBatteryData(String batteryId) {
        var entity = generalInfoRepo.findByProductIdentifier(batteryId)
                .orElseThrow(() -> new NoSuchElementException("Battery not found: " + batteryId));
        return BatteryPassportDTO.builder()
                .batteryId(entity.getProductIdentifier())
                .batteryPassportId(entity.getBatteryPassportId())
                .build();
    }

    // --- Data source management ---
    @Transactional
    public DataSourceConfigDTO createDataSource(DataSourceConfigDTO dto) {
        var entity = DataSourceConfigEntity.builder()
                .name(dto.getName()).systemType(dto.getSystemType())
                .connectorType(dto.getConnectorType()).endpointUrl(dto.getEndpointUrl())
                .authType(dto.getAuthType()).enabled(true).build();
        configRepo.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<DataSourceConfigDTO> listDataSources() {
        return configRepo.findByEnabledTrue().stream()
                .map(e -> DataSourceConfigDTO.builder()
                        .id(e.getId()).name(e.getName())
                        .systemType(e.getSystemType()).connectorType(e.getConnectorType())
                        .endpointUrl(e.getEndpointUrl()).enabled(e.getEnabled()).build())
                .toList();
    }

    @Transactional
    public void deleteDataSource(Long id) {
        configRepo.deleteById(id);
    }

    // --- Mapping rule management ---
    @Transactional
    public MappingRuleDTO createMappingRule(MappingRuleDTO dto) {
        var entity = MappingRuleEntity.builder()
                .name(dto.getRuleId()).sourceSystem(dto.getSourceSystem())
                .targetModel(dto.getTargetModel())
                .ruleDefinition(JsonUtils.toJson(dto.getMappings()))
                .validationSchema(dto.getValidationSchema()).build();
        mappingRuleRepo.save(entity);
        mappingEngine.refreshAll();
        return dto;
    }

    public List<MappingRuleDTO> listMappingRules() {
        return mappingRuleRepo.findAll().stream()
                .map(e -> MappingRuleDTO.builder()
                        .ruleId(e.getName()).sourceSystem(e.getSourceSystem())
                        .targetModel(e.getTargetModel()).version(e.getVersion()).build())
                .toList();
    }

    private Map<String, Object> buildConnectorConfig(DataSourceConfigEntity config) {
        Map<String, Object> conf = new HashMap<>();
        conf.put("endpointUrl", config.getEndpointUrl());
        conf.put("authType", config.getAuthType());
        if (config.getAuthConfig() != null) {
            var auth = JsonUtils.fromJson(config.getAuthConfig(), Map.class);
            conf.putAll(auth);
        }
        return conf;
    }
}
