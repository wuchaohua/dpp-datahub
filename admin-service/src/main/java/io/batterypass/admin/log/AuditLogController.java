package io.batterypass.admin.log;

import io.batterypass.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin/logs")
public class AuditLogController {

    @GetMapping
    public ResponseEntity<ApiResponse<?>> queryLogs(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String level,
            @RequestParam(defaultValue = "20") int pageSize) {
        return ResponseEntity.ok(ApiResponse.success("Audit logs"));
    }

    @GetMapping("/export")
    public ResponseEntity<ApiResponse<String>> exportLogs(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        return ResponseEntity.ok(ApiResponse.success("Export URL"));
    }
}
