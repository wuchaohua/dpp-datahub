-- ============================================================
-- Battery Passport Data Collector - PostgreSQL Schema
-- Version: 1.0.0
-- ============================================================

CREATE DATABASE IF NOT EXISTS battery_pass;

-- 1. 电池基本信息 (GeneralProductInformation)
CREATE TABLE IF NOT EXISTS battery_general_info (
    id BIGSERIAL PRIMARY KEY,
    product_identifier VARCHAR(255) NOT NULL,
    battery_passport_id VARCHAR(255) NOT NULL,
    battery_category VARCHAR(50) NOT NULL,
    battery_status VARCHAR(50) NOT NULL,
    battery_mass FLOAT NOT NULL,
    manufacturing_date TIMESTAMPTZ NOT NULL,
    putting_into_service TIMESTAMPTZ,
    warranty_period VARCHAR(50),
    manufacturer_info JSONB,
    operator_info JSONB,
    manufacturing_place JSONB,
    created_at TIMESTAMPTZ DEFAULT NOW(),
    updated_at TIMESTAMPTZ DEFAULT NOW(),
    UNIQUE(product_identifier),
    UNIQUE(battery_passport_id)
);

-- 2. 碳足迹 (CarbonFootprintForBatteries)
CREATE TABLE IF NOT EXISTS battery_carbon_footprint (
    id BIGSERIAL PRIMARY KEY,
    battery_id BIGINT NOT NULL REFERENCES battery_general_info(id),
    absolute_carbon_footprint DOUBLE PRECISION,
    battery_carbon_footprint DOUBLE PRECISION,
    carbon_footprint_performance_class VARCHAR(255),
    carbon_footprint_study_url TEXT,
    footprint_stages JSONB,
    version VARCHAR(20),
    created_at TIMESTAMPTZ DEFAULT NOW()
);

-- 3. 循环性 (Circularity)
CREATE TABLE IF NOT EXISTS battery_circularity (
    id BIGSERIAL PRIMARY KEY,
    battery_id BIGINT NOT NULL REFERENCES battery_general_info(id),
    renewable_content DOUBLE PRECISION,
    recycled_content JSONB,
    end_of_life_info JSONB,
    safety_measures JSONB,
    spare_part_sources JSONB,
    created_at TIMESTAMPTZ DEFAULT NOW()
);

-- 4. 材料成分 (MaterialComposition)
CREATE TABLE IF NOT EXISTS battery_material_composition (
    id BIGSERIAL PRIMARY KEY,
    battery_id BIGINT NOT NULL REFERENCES battery_general_info(id),
    chemistry_short_name VARCHAR(50),
    chemistry_clear_name VARCHAR(255),
    battery_materials JSONB,
    hazardous_substances JSONB,
    created_at TIMESTAMPTZ DEFAULT NOW()
);

-- 5. 技术参数 (TechnicalProperties from Performance submodel)
CREATE TABLE IF NOT EXISTS battery_technical_properties (
    id BIGSERIAL PRIMARY KEY,
    battery_id BIGINT NOT NULL REFERENCES battery_general_info(id),
    rated_capacity DOUBLE PRECISION,
    rated_energy DOUBLE PRECISION,
    nominal_voltage DOUBLE PRECISION,
    maximum_voltage DOUBLE PRECISION,
    minimum_voltage DOUBLE PRECISION,
    rated_maximum_power DOUBLE PRECISION,
    expected_lifetime INTEGER,
    expected_number_of_cycles INTEGER,
    properties_json JSONB,
    created_at TIMESTAMPTZ DEFAULT NOW()
);

-- 6. 标签/认证 (Labels)
CREATE TABLE IF NOT EXISTS battery_labels (
    id BIGSERIAL PRIMARY KEY,
    battery_id BIGINT NOT NULL REFERENCES battery_general_info(id),
    labeling_subject VARCHAR(255),
    documents JSONB,
    created_at TIMESTAMPTZ DEFAULT NOW()
);

-- 7. 供应链尽职调查 (SupplyChainDueDiligence)
CREATE TABLE IF NOT EXISTS battery_due_diligence (
    id BIGSERIAL PRIMARY KEY,
    battery_id BIGINT NOT NULL REFERENCES battery_general_info(id),
    documents JSONB,
    created_at TIMESTAMPTZ DEFAULT NOW()
);

-- 8. 数据源配置
CREATE TABLE IF NOT EXISTS data_source_config (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    system_type VARCHAR(50) NOT NULL,
    connector_type VARCHAR(50) NOT NULL,
    endpoint_url TEXT NOT NULL,
    auth_type VARCHAR(50),
    auth_config JSONB,
    mapping_rule_id BIGINT,
    schedule_cron VARCHAR(100),
    enabled BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMPTZ DEFAULT NOW()
);

-- 9. 映射规则
CREATE TABLE IF NOT EXISTS mapping_rule (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    source_system VARCHAR(50) NOT NULL,
    target_model VARCHAR(255) NOT NULL,
    rule_definition JSONB NOT NULL,
    validation_schema TEXT,
    version VARCHAR(20) DEFAULT '1.0',
    created_at TIMESTAMPTZ DEFAULT NOW()
);

-- 10. 数据同步日志
CREATE TABLE IF NOT EXISTS data_sync_log (
    id BIGSERIAL PRIMARY KEY,
    data_source_id BIGINT REFERENCES data_source_config(id),
    battery_id VARCHAR(255),
    target_model VARCHAR(255),
    status VARCHAR(20) NOT NULL,
    error_message TEXT,
    raw_payload TEXT,
    processed_payload JSONB,
    started_at TIMESTAMPTZ NOT NULL,
    completed_at TIMESTAMPTZ,
    retry_count INT DEFAULT 0
);

-- Indexes
CREATE INDEX IF NOT EXISTS idx_battery_general_info_product_id ON battery_general_info(product_identifier);
CREATE INDEX IF NOT EXISTS idx_battery_general_info_passport_id ON battery_general_info(battery_passport_id);
CREATE INDEX IF NOT EXISTS idx_data_source_config_enabled ON data_source_config(enabled);
CREATE INDEX IF NOT EXISTS idx_data_sync_log_battery_id ON data_sync_log(battery_id);
CREATE INDEX IF NOT EXISTS idx_data_sync_log_status ON data_sync_log(status);

-- Seed data: default mapping rule for ERP to GeneralProductInformation
INSERT INTO mapping_rule (name, source_system, target_model, rule_definition, version) VALUES
('ERP-to-GeneralProductInfo', 'ERP', 'GeneralProductInformation',
 '[{"source":"$.materialNumber","target":"productIdentifier","transformer":null},{"source":"$.manufacturerCode","target":"manufacturerInformation.identifier","transformer":null},{"source":"$.prodDate","target":"manufacturingDate","transformer":"ISO8601Date"},{"source":"$.weightKg","target":"batteryMass","transformer":null},{"source":"LMT","target":"batteryCategory","transformer":"constant"}]',
 '1.0')
ON CONFLICT DO NOTHING;
