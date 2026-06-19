package io.batterypass.gateway.controller;

import io.batterypass.common.dto.ApiResponse;
import io.batterypass.common.dto.BatteryPassportDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/passport")
public class PassportController {

    @GetMapping("/battery/{batteryId}")
    public Mono<ResponseEntity<ApiResponse<BatteryPassportDTO>>> getPassport(@PathVariable String batteryId) {
        return Mono.just(ResponseEntity.ok(
                ApiResponse.success(BatteryPassportDTO.builder().batteryId(batteryId).build())));
    }

    @PostMapping("/battery/{batteryId}/publish")
    public Mono<ResponseEntity<ApiResponse<String>>> publishPassport(@PathVariable String batteryId) {
        return Mono.just(ResponseEntity.ok(
                ApiResponse.success("Passport publish triggered for " + batteryId)));
    }

    @GetMapping("/health")
    public Mono<ResponseEntity<ApiResponse<String>>> health() {
        return Mono.just(ResponseEntity.ok(ApiResponse.success("gateway is running")));
    }
}
