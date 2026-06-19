package io.batterypass.admin.mapping;

import io.batterypass.common.dto.ApiResponse;
import io.batterypass.common.dto.MappingRuleDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/mapping-rules")
public class MappingRuleController {

    @GetMapping
    public ResponseEntity<ApiResponse<?>> listAll() {
        return ResponseEntity.ok(ApiResponse.success("Mapping rules list"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable Long id, @RequestBody MappingRuleDTO dto) {
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @PostMapping("/{id}/validate")
    public ResponseEntity<ApiResponse<String>> validate(@PathVariable Long id, @RequestBody String payload) {
        return ResponseEntity.ok(ApiResponse.success("Validation result for rule " + id));
    }

    @PostMapping("/reload")
    public ResponseEntity<ApiResponse<String>> reloadAll() {
        return ResponseEntity.ok(ApiResponse.success("All mapping rules reloaded"));
    }
}
