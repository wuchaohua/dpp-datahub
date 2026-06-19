package io.batterypass.common.model;

public enum BatteryComponent {
    CELL("Cell"),
    MODULE("Module"),
    PACK("Pack"),
    ANODE("Anode"),
    CATHODE("Cathode"),
    ELECTROLYTE("Electrolyte");

    private final String value;
    BatteryComponent(String value) { this.value = value; }
    public String getValue() { return value; }
}
