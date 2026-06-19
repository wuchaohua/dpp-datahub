# Battery Pass Data Collector (电池护照数据采集中台)

## 项目概述

Battery Pass Data Collector 是一个面向电池护照平台的**数据采集中台**，包含**静态数据中台**和**动态数据中台**两个子系统，最终通过统一数据采集接口为电池护照平台提供标准化数据。

## 架构设计

```
┌──────────────────────────────────────────────────────────────┐
│                    Battery Passport Platform                   │
└──────────────────────────┬───────────────────────────────────┘
                           │
            ┌──────────────┴──────────────┐
            │  Unified Data Collection API │
            └──────────┬────────┬─────────┘
                       │        │
           ┌───────────┘        └───────────┐
           │                                │
  ┌────────▼────────┐            ┌─────────▼────────┐
  │  Static Data MW │            │  Dynamic Data MW │
  │ (静态数据中台)    │            │ (动态数据中台)     │
  │  · SRM/ERP/PLM   │            │  · BMS/EMS        │
  │  · 映射引擎       │            │  · MQTT/OPCUA     │
  │  · PostgreSQL    │            │  · TDengine       │
  └─────────────────┘            └──────────────────┘
```

## 技术栈

| 组件 | 选型 |
|------|------|
| 语言 | Java 17 |
| 框架 | Spring Boot 3.x + Spring Cloud 2023.x |
| 注册/配置 | Nacos |
| 静态存储 | PostgreSQL 15 |
| 时序存储 | TDengine 3.x |
| 缓存 | Redis |
| 消息队列 | RabbitMQ |
| 对象存储 | MinIO |
| 监控 | Prometheus + Grafana |
| 部署 | Docker Compose / Kubernetes (Helm) |

## 模块说明

### collector-common
公共模块，包含 Battery Pass 数据模型 POJO (7个子模型)、Connector 接口、DTO 和工具类。

### static-data-service (静态数据中台)
从企业数字化系统(SRM/ERP/PLM/MES)采集静态数据，通过连接器工厂和映射引擎将企业数据转换为 Battery Pass 标准模型，存入 PostgreSQL。

### dynamic-data-service (动态数据中台)
从 BMS/EMS 系统通过 MQTT/OPC UA/Modbus 协议采集动态数据，通过摄取管道写入 TDengine 时序数据库。

### collector-api-gateway (统一数据采集接口)
对外提供 REST API 和 AAS 接口，统一查询静态和动态数据，支持 OAuth2 认证。

### admin-service (管理后台)
数据源管理、映射规则管理、监控仪表盘、审计日志查询。

## EU Battery Regulation 2023/1542 合规

数据模型完全对齐 [BatteryPassDataModel v1.2.0](https://github.com/batterypass/BatteryPassDataModel) 的 7 个子模型：

- GeneralProductInformation (Art. 38, Art. 77)
- CarbonFootprintForBatteries (Art. 9, Annex VI)
- Circularity (Art. 10, Recycled Content)
- MaterialComposition (Annex VII)
- PerformanceAndDurability (Art. 11)
- Labels (Art. 13)
- SupplyChainDueDiligence (Annex X)

## 快速开始

### 前置条件

- JDK 17+
- Maven 3.8+
- Docker & Docker Compose

### 构建

```bash
mvn clean package -DskipTests
```

### 部署 (Docker Compose)

```bash
docker-compose up -d
```

### 访问接口

- API Gateway: http://localhost:8080
- Admin Service: http://localhost:8083
- Nacos Console: http://localhost:8848/nacos
- Grafana: http://localhost:3000 (admin/admin)

### 部署 (Kubernetes)

```bash
helm install battery-pass-collector ./helm-chart
```

## API 文档

### 静态数据

| 方法 | 路径 | 描述 |
|------|------|------|
| POST | /api/v1/static/ingest | 推送静态数据 |
| GET | /api/v1/static/battery/{batteryId} | 查询电池静态数据 |
| POST | /api/v1/static/collect | 触发从数据源采集 |

### 动态数据

| 方法 | 路径 | 描述 |
|------|------|------|
| GET | /api/v1/dynamic/battery/{id}/realtime | 获取实时数据 |
| GET | /api/v1/dynamic/battery/{id}/history | 获取历史时序数据 |

### 电池护照

| 方法 | 路径 | 描述 |
|------|------|------|
| GET | /api/v1/passport/battery/{id} | 获取完整电池护照数据 |
| POST | /api/v1/passport/battery/{id}/publish | 触发护照签发 |

### AAS 接口

| 方法 | 路径 | 描述 |
|------|------|------|
| GET | /aas/{batteryId} | 获取 AAS Shell |
| GET | /aas/{batteryId}/submodels | 列出子模型 |
| GET | /aas/{batteryId}/submodels/{id} | 获取子模型 |

## 开发计划

### Phase 1 (✓ 已完成): 骨架与静态能力
- [x] Maven 多模块工程初始化
- [x] Battery Pass 数据模型 POJO (7个子模型全覆盖)
- [x] PostgreSQL 表设计 + JPA Entity 映射
- [x] REST 通用连接器 + 映射引擎基础版
- [x] 管理 API：数据源配置 CRUD

### Phase 2 (进行中): 动态数据能力
- [x] MQTT 适配器实现
- [x] TDengine 超级表创建
- [x] 数据摄取管道
- [ ] OPC UA 适配器
- [ ] Modbus 适配器

### Phase 3: 互操作与合规
- [ ] AAS 接口完整实现 (Eclipse BaSyx)
- [ ] JSON Schema 全量校验
- [ ] GS1 Digital Link 支持

### Phase 4: 运维与交付
- [x] Docker Compose 交付包
- [x] Helm Chart K8s 部署包
- [x] Prometheus + Grafana 监控
- [ ] 性能压测 + 安全扫描
