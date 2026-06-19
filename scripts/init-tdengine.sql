CREATE DATABASE IF NOT EXISTS battery_pass;

-- 超级表: 原始动态数据
CREATE STABLE IF NOT EXISTS battery_dynamic_raw (
    ts TIMESTAMP,
    battery_id BINARY(64),
    param_name BINARY(64),
    param_value DOUBLE,
    quality INT,
    unit BINARY(32)
) TAGS(battery_type BINARY(32), manufacturer BINARY(64));

-- 超级表: 聚合数据 1小时
CREATE STABLE IF NOT EXISTS battery_dynamic_agg_1h (
    ts TIMESTAMP,
    battery_id BINARY(64),
    param_name BINARY(64),
    avg_value DOUBLE,
    min_value DOUBLE,
    max_value DOUBLE,
    count INT
) TAGS(battery_type BINARY(32), manufacturer BINARY(64));

-- 超级表: 聚合数据 1天
CREATE STABLE IF NOT EXISTS battery_dynamic_agg_1d (
    ts TIMESTAMP,
    battery_id BINARY(64),
    param_name BINARY(64),
    avg_value DOUBLE,
    min_value DOUBLE,
    max_value DOUBLE,
    count INT
) TAGS(battery_type BINARY(32), manufacturer BINARY(64));

-- 告警表
CREATE STABLE IF NOT EXISTS battery_alarm (
    ts TIMESTAMP,
    battery_id BINARY(64),
    alarm_type BINARY(64),
    alarm_level BINARY(16),
    alarm_message BINARY(255),
    acknowledged BOOL
) TAGS(battery_type BINARY(32), manufacturer BINARY(64));
