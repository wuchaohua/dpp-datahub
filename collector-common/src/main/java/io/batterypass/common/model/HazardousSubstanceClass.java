package io.batterypass.common.model;

public enum HazardousSubstanceClass {
    ACUTE_TOXICITY("AcuteToxicity"),
    CARCINOGENICITY("Carcinogenicity"),
    MUTAGENICITY("Mutagenicity"),
    REPRODUCTIVE_TOXICITY("ReproductiveToxicity"),
    CORROSIVITY("Corrosivity"),
    FLAMMABILITY("Flammability");

    private final String value;
    HazardousSubstanceClass(String value) { this.value = value; }
    public String getValue() { return value; }
}
