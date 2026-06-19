package io.batterypass.staticdata.mapping;

import com.fasterxml.jackson.core.type.TypeReference;
import io.batterypass.common.exception.MappingException;
import io.batterypass.common.util.JsonUtils;
import io.batterypass.staticdata.repository.MappingRuleRepository;
import io.batterypass.staticdata.repository.entity.MappingRuleEntity;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class MappingEngine {
    private final MappingRuleRepository mappingRuleRepository;
    private final Map<String, MappingRuleEntity> ruleCache = new ConcurrentHashMap<>();

    public MappingEngine(MappingRuleRepository mappingRuleRepository) {
        this.mappingRuleRepository = mappingRuleRepository;
    }

    @PostConstruct
    public void loadRules() {
        List<MappingRuleEntity> rules = mappingRuleRepository.findAll();
        for (MappingRuleEntity rule : rules) {
            String key = rule.getSourceSystem() + ":" + rule.getTargetModel();
            ruleCache.put(key, rule);
        }
        log.info("Loaded {} mapping rules", rules.size());
    }

    public Map<String, Object> transform(Map<String, Object> sourceData, String sourceSystem, String targetModel) {
        String key = sourceSystem + ":" + targetModel;
        MappingRuleEntity rule = ruleCache.get(key);
        if (rule == null) {
            throw new MappingException("No mapping rule found for " + key);
        }
        Map<String, Object> result = new HashMap<>();
        try {
            List<Map<String, Object>> mappings = JsonUtils.getMapper().readValue(
                    rule.getRuleDefinition(), new TypeReference<List<Map<String, Object>>>() {});
            for (Map<String, Object> mapping : mappings) {
                String sourcePath = (String) mapping.get("source");
                String targetPath = (String) mapping.get("target");
                String transformer = (String) mapping.get("transformer");
                Object value = resolveValue(sourceData, sourcePath);
                if (value != null) {
                    value = applyTransformer(value, transformer);
                }
                setValue(result, targetPath, value);
            }
        } catch (Exception e) {
            log.error("Mapping failed for rule {}: {}", key, e.getMessage());
            throw new MappingException("Mapping failed: " + e.getMessage(), e);
        }
        return result;
    }

    private Object resolveValue(Map<String, Object> data, String path) {
        if (path == null || path.isEmpty()) return null;
        // Support constant values: e.g. "LMT" is a literal
        if (!path.startsWith("$")) return path;
        String[] parts = path.substring(2).split("\\.");
        Object current = data;
        for (String part : parts) {
            if (current instanceof Map) {
                current = ((Map<String, Object>) current).get(part);
            } else {
                return null;
            }
        }
        return current;
    }

    private Object applyTransformer(Object value, String transformer) {
        if (transformer == null) return value;
        if (value instanceof String) {
            return switch (transformer.toUpperCase()) {
                case "UPPER" -> ((String) value).toUpperCase();
                case "LOWER" -> ((String) value).toLowerCase();
                default -> value;
            };
        }
        return value;
    }

    private void setValue(Map<String, Object> target, String path, Object value) {
        if (path == null || value == null) return;
        String[] parts = path.split("\\.");
        Map<String, Object> current = target;
        for (int i = 0; i < parts.length - 1; i++) {
            current.computeIfAbsent(parts[i], k -> new HashMap<String, Object>());
            current = (Map<String, Object>) current.get(parts[i]);
        }
        current.put(parts[parts.length - 1], value);
    }

    public void refreshRule(String sourceSystem, String targetModel) {
        String key = sourceSystem + ":" + targetModel;
        mappingRuleRepository.findBySourceSystemAndTargetModel(sourceSystem, targetModel)
                .ifPresent(rule -> ruleCache.put(key, rule));
    }

    public void refreshAll() {
        ruleCache.clear();
        loadRules();
    }
}
