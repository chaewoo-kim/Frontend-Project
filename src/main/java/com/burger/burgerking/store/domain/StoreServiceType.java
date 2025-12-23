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

    /** JSON / DB 코드 값 */
    public String getCode() {
        return code;
    }

    /** 화면 출력용 한글명 */
    public String getDisplayName() {
        return displayName;
    }

    /** JSON 적재 시: code → enum */
    public static StoreServiceType fromCode(String code) {
        return Arrays.stream(values())
                .filter(type -> type.code.equals(code))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Unknown StoreServiceType code: " + code)
                );
    }
}
