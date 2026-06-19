package io.batterypass.common.model;

public enum LifecycleStage {
    RAW_MATERIAL_EXTRACTION("RawMaterialExtraction"),
    MAIN_PRODUCTION("MainProduction"),
    DISTRIBUTION("Distribution"),
    END_OF_LIFE_COLLECTION("EndOfLifeCollection"),
    RECYCLING("Recycling");

    private final String value;
    LifecycleStage(String value) { this.value = value; }
    public String getValue() { return value; }
}
