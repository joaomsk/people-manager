package com.joaomeleski.peoplemanager.util;

public class ValueOrDefault {
    public static <T> T getValueOrDefault(T value, T defaultValue) {
        return value == null || (value instanceof String && ((String) value).isBlank()) ? defaultValue : value;
    }
}
