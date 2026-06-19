package io.batterypass.common.model;

public enum BatteryCategoryEnum {
    LMT("lmt", "Light Means of Transport"),
    EV("ev", "Electric Vehicle"),
    INDUSTRIAL("industrial", "Industrial"),
    STATIONARY("stationary", "Stationary Energy Storage");

    private final String code;
    private final String description;

    BatteryCategoryEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() { return code; }
    public String getDescription() { return description; }

    public static BatteryCategoryEnum fromCode(String code) {
        for (BatteryCategoryEnum c : values()) {
            if (c.code.equalsIgnoreCase(code)) return c;
        }
        throw new IllegalArgumentException("Unknown battery category: " + code);
    }
}
