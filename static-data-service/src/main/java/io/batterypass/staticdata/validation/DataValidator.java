package io.batterypass.staticdata.validation;

import io.batterypass.common.exception.ValidationException;
import io.batterypass.common.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;

@Slf4j
@Service
public class DataValidator {
    private final Map<String, String> schemaCache = new HashMap<>();

    public void registerSchema(String modelName, String schemaJson) {
        schemaCache.put(modelName, schemaJson);
    }

    public List<String> validate(String modelName, Object data) {
        List<String> errors = new ArrayList<>();
        if (data == null) {
            errors.add("Data is null");
            return errors;
        }
        // Perform basic field-level validation
        Map<String, Object> dataMap;
        if (data instanceof Map) {
            dataMap = (Map<String, Object>) data;
        } else {
            dataMap = JsonUtils.getMapper().convertValue(data, Map.class);
        }
        // Check required fields based on target model
        switch (modelName) {
            case "GeneralProductInformation" -> validateGeneralInfo(dataMap, errors);
            case "CarbonFootprintForBatteries" -> validateCarbonFootprint(dataMap, errors);
            default -> log.debug("No specific validation for model: {}", modelName);
        }
        return errors;
    }

    private void validateGeneralInfo(Map<String, Object> data, List<String> errors) {
        checkRequired(data, "productIdentifier", errors);
        checkRequired(data, "batteryPassportIdentifier", errors);
        checkRequired(data, "batteryCategory", errors);
        checkRequired(data, "batteryStatus", errors);
        checkRequired(data, "batteryMass", errors);
        checkRequired(data, "manufacturingDate", errors);
        checkRequiredNested(data, "manufacturerInformation", "identifier", errors);
        checkRequiredNested(data, "manufacturerInformation", "postalAddress", errors);
        checkRequiredNested(data, "manufacturingPlace", "addressCountry", errors);
        checkRequiredNested(data, "manufacturingPlace", "streetAddress", errors);
        checkRequiredNested(data, "manufacturingPlace", "postalCode", errors);
    }

    private void validateCarbonFootprint(Map<String, Object> data, List<String> errors) {
        checkRequired(data, "carbonFootprintPerLifecycleStage", errors);
    }

    private void checkRequired(Map<String, Object> data, String field, List<String> errors) {
        if (data.get(field) == null) {
            errors.add("Required field '" + field + "' is missing");
        }
    }

    @SuppressWarnings("unchecked")
    private void checkRequiredNested(Map<String, Object> data, String parent, String field, List<String> errors) {
        Object parentObj = data.get(parent);
        if (parentObj instanceof Map) {
            if (((Map<String, Object>) parentObj).get(field) == null) {
                errors.add("Required field '" + parent + "." + field + "' is missing");
            }
        } else {
            errors.add("Required parent '" + parent + "' is missing");
        }
    }

    public boolean isValid(String modelName, Object data) {
        return validate(modelName, data).isEmpty();
    }
}
