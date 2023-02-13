package com.joaomeleski.peoplemanager.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValueOrDefaultTest {

    @Test
    void mustReturnDefaultValue_whenValueIsNull() {
        String value = null;
        String defaultValue = "default";
        String actualValue = ValueOrDefault.getValueOrDefault(value, defaultValue);

        assertEquals(defaultValue, actualValue);
    }

    @Test
    void mustReturnDefaultValue_whenValueIsEmpty() {
        String value = "";
        String defaultValue = "default";
        String actualValue = ValueOrDefault.getValueOrDefault(value, defaultValue);

        assertEquals(defaultValue, actualValue);
    }

    @Test
    void mustReturnDefaultValue_whenValueIsBlank() {
        String value = " ";
        String defaultValue = "default";
        String actualValue = ValueOrDefault.getValueOrDefault(value, defaultValue);

        assertEquals(defaultValue, actualValue);
    }

    @Test
    void mustReturnValue_whenValueIsNotNull() {
        String value = "value";
        String defaultValue = "default";
        String actualValue = ValueOrDefault.getValueOrDefault(value, defaultValue);

        assertEquals(value, actualValue);
    }

    @Test
    void mustReturnValue_whenValueIsNotEmpty() {
        String value = "value";
        String defaultValue = "default";
        String actualValue = ValueOrDefault.getValueOrDefault(value, defaultValue);

        assertEquals(value, actualValue);
    }

    @Test
    void mustReturnValue_whenValueIsNotBlank() {
        String value = "value";
        String defaultValue = "default";
        String actualValue = ValueOrDefault.getValueOrDefault(value, defaultValue);

        assertEquals(value, actualValue);
    }

}