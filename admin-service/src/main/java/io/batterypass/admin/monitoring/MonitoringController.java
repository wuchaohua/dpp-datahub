package io.batterypass.admin.monitoring;

import io.batterypass.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin/monitoring")
public class MonitoringController {

    @GetMapping("/dashboard")
    public ResponseEntity<ApiResponse<Map<String, Object>>> dashboard() {
        return ResponseEntity.ok(ApiResponse.success(Map.of(
                "totalBatteries", 0,
                "activeDataSources", 0,
                "syncSuccessRate", 0.0,
                "lastSyncTime", "N/A"
        )));
    }

    @GetMapping("/sync-logs")
    public ResponseEntity<ApiResponse<?>> syncLogs(
            @RequestParam(required = false) String batteryId,
            @RequestParam(required = false) String status) {
        return ResponseEntity.ok(ApiResponse.success("Sync logs"));
    }

    @GetMapping("/sync-stats")
    public ResponseEntity<ApiResponse<Map<String, Object>>> syncStats() {
        return ResponseEntity.ok(ApiResponse.success(Map.of(
                "totalSyncs", 0, "successCount", 0,
                "failureCount", 0, "inProgress", 0
        )));
    }
}
