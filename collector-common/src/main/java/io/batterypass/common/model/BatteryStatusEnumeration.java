package io.batterypass.common.model;

public enum BatteryStatusEnumeration {
    ORIGINAL("Original"),
    REPURPOSED("Repurposed"),
    REUSED("Reused"),
    REMANUFACTURED("Remanufactured"),
    WASTE("Waste");

    private final String value;
    BatteryStatusEnumeration(String value) { this.value = value; }
    public String getValue() { return value; }

    public static BatteryStatusEnumeration fromValue(String v) {
        for (BatteryStatusEnumeration s : values()) {
            if (s.value.equalsIgnoreCase(v)) return s;
        }
        throw new IllegalArgumentException("Unknown battery status: " + v);
    }
}
