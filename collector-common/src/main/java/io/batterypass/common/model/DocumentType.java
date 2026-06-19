package io.batterypass.common.model;

public enum DocumentType {
    BILL_OF_MATERIAL("BillOfMaterial"),
    SAFETY_DATA_SHEET("SafetyDataSheet"),
    TEST_REPORT("TestReport"),
    DECLARATION_OF_CONFORMITY("DeclarationOfConformity"),
    SUSTAINABILITY_REPORT("SustainabilityReport");

    private final String value;
    DocumentType(String value) { this.value = value; }
    public String getValue() { return value; }
}
