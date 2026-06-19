package io.batterypass.staticdata.web;

import io.batterypass.common.dto.*;
import io.batterypass.staticdata.StaticDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/static")
@RequiredArgsConstructor
public class StaticDataController {
    private final StaticDataService staticDataService;

    @PostMapping("/ingest")
    public ResponseEntity<ApiResponse<BatteryPassportDTO>> ingest(@RequestBody BatteryPassportDTO dto) {
        return ResponseEntity.ok(ApiResponse.success(staticDataService.ingestStaticData(dto)));
    }

    @GetMapping("/battery/{batteryId}")
    public ResponseEntity<ApiResponse<BatteryPassportDTO>> getBattery(@PathVariable String batteryId) {
        return ResponseEntity.ok(ApiResponse.success(staticDataService.getBatteryData(batteryId)));
    }

    @PostMapping("/collect")
    public ResponseEntity<ApiResponse<?>> collect(
            @RequestParam String batteryId, @RequestParam Long dataSourceId) {
        return ResponseEntity.ok(ApiResponse.success(
                staticDataService.collectBatteryData(batteryId, dataSourceId)));
    }

    // --- Data source management ---
    @PostMapping("/datasources")
    public ResponseEntity<ApiResponse<DataSourceConfigDTO>> createDataSource(@RequestBody DataSourceConfigDTO dto) {
        return ResponseEntity.ok(ApiResponse.success(staticDataService.createDataSource(dto)));
    }

    @GetMapping("/datasources")
    public ResponseEntity<ApiResponse<List<DataSourceConfigDTO>>> listDataSources() {
        return ResponseEntity.ok(ApiResponse.success(staticDataService.listDataSources()));
    }

    @DeleteMapping("/datasources/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDataSource(@PathVariable Long id) {
        staticDataService.deleteDataSource(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    // --- Mapping rule management ---
    @PostMapping("/mapping-rules")
    public ResponseEntity<ApiResponse<MappingRuleDTO>> createMappingRule(@RequestBody MappingRuleDTO dto) {
        return ResponseEntity.ok(ApiResponse.success(staticDataService.createMappingRule(dto)));
    }

    @GetMapping("/mapping-rules")
    public ResponseEntity<ApiResponse<List<MappingRuleDTO>>> listMappingRules() {
        return ResponseEntity.ok(ApiResponse.success(staticDataService.listMappingRules()));
    }

    @GetMapping("/health")
    public ResponseEntity<ApiResponse<String>> health() {
        return ResponseEntity.ok(ApiResponse.success("static-data-service is running"));
    }
}
