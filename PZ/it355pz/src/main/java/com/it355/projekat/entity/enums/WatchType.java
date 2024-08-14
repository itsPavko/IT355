package com.it355.projekat.entity.enums;

public enum WatchType {

    MALE("Male"),
    FEMALE("Female");

    private final String value;

    WatchType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
