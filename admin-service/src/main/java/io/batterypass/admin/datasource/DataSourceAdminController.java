package io.batterypass.admin.datasource;

import io.batterypass.common.dto.ApiResponse;
import io.batterypass.common.dto.DataSourceConfigDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/v1/admin/datasources")
public class DataSourceAdminController {

    @GetMapping
    public ResponseEntity<ApiResponse<List<DataSourceConfigDTO>>> listAll() {
        return ResponseEntity.ok(ApiResponse.success(Collections.emptyList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DataSourceConfigDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(new DataSourceConfigDTO()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable Long id, @RequestBody DataSourceConfigDTO dto) {
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @PostMapping("/{id}/test")
    public ResponseEntity<ApiResponse<String>> testConnection(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Connection test result for " + id));
    }
}
