package io.batterypass.dynamicdata.web;

import io.batterypass.common.dto.ApiResponse;
import io.batterypass.dynamicdata.store.TDengineStore;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dynamic")
@RequiredArgsConstructor
public class DynamicDataController {
    private final TDengineStore tdengineStore;

    @GetMapping("/battery/{batteryId}/realtime")
    public ResponseEntity<ApiResponse<String>> getRealtime(@PathVariable String batteryId) {
        return ResponseEntity.ok(ApiResponse.success("Real-time data endpoint for " + batteryId));
    }

    @GetMapping("/battery/{batteryId}/history")
    public ResponseEntity<ApiResponse<String>> getHistory(
            @PathVariable String batteryId,
            @RequestParam(required = false) String param,
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to,
            @RequestParam(required = false, defaultValue = "raw") String agg) {
        return ResponseEntity.ok(ApiResponse.success(
                "History endpoint for " + batteryId + " param=" + param + " agg=" + agg));
    }

    @GetMapping("/health")
    public ResponseEntity<ApiResponse<String>> health() {
        return ResponseEntity.ok(ApiResponse.success("dynamic-data-service is running"));
    }
}
