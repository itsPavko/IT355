package com.it355.projekat.entity.enums;

public enum MemberType {

    STANDARD("Standard Packet"),
    PREMIUM("Premium Packet");

    private final String value;

    MemberType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
