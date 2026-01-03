package com.burger.burgerking.store.domain;

import java.util.Arrays;

public enum StoreServiceType {

    KING_ORDER("01", "킹오더"),
    DELIVERY("02", "딜리버리"),
    OPEN_24("03", "24시"),
    KING_MORNING("04", "킹모닝"),
    DRIVE_THRU("05", "드라이브스루"),
    PARKING("06", "주차");

    private final String code;
    private final String displayName;

    StoreServiceType(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static StoreServiceType fromCode(String code) {
        return Arrays.stream(values())
                .filter(type -> type.code.equals(code))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Unknown StoreServiceType code: " + code)
                );
    }
    public static StoreServiceType from(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Service type value is null");
        }
        String normalized = value.trim().toUpperCase();
        return Arrays.stream(values())
                .filter(v -> v.name().equals(normalized))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid service type: " + value)
                );
    }
}
