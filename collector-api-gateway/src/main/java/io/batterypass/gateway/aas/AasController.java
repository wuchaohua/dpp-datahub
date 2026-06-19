package io.batterypass.gateway.controller;

import io.batterypass.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.Map;

@RestController
@RequestMapping("/aas")
public class AasController {

    @GetMapping("/{batteryId}")
    public Mono<ResponseEntity<ApiResponse<Map<String, Object>>>> getAasShell(@PathVariable String batteryId) {
        Map<String, Object> shell = Map.of(
                "idShort", batteryId,
                "idType", "IRI",
                "globalAssetId", Map.of("value", batteryId)
        );
        return Mono.just(ResponseEntity.ok(ApiResponse.success(shell)));
    }

    @GetMapping("/{batteryId}/submodels")
    public Mono<ResponseEntity<ApiResponse<String[]>>> listSubmodels(@PathVariable String batteryId) {
        String[] submodels = {
                "GeneralProductInformation", "CarbonFootprint", "Circularity",
                "MaterialComposition", "PerformanceAndDurability", "Labels", "SupplyChainDueDiligence"
        };
        return Mono.just(ResponseEntity.ok(ApiResponse.success(submodels)));
    }

    @GetMapping("/{batteryId}/submodels/{submodelId}")
    public Mono<ResponseEntity<ApiResponse<Map<String, Object>>>> getSubmodel(
            @PathVariable String batteryId, @PathVariable String submodelId) {
        return Mono.just(ResponseEntity.ok(
                ApiResponse.success(Map.of("submodel", submodelId, "batteryId", batteryId))));
    }
}
