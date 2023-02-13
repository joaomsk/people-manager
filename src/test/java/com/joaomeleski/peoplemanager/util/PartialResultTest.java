package com.joaomeleski.peoplemanager.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartialResultTest {

    @Test
    void mustCreatePartialResult() {
        var partialResult = new PartialResult<>(null, 0, 0, 0);
        assertNotNull(partialResult);
    }

    @Test
    void mustCreatePartialResultWithHeaders() {
        var partialResult = new PartialResult<>(null, 0, 0, 0);
        assertNotNull(partialResult.getHeaders());
    }

    @Test
    void mustCreatePartialResultWithData() {
        var partialResult = new PartialResult<>("data", 0, 0, 0);
        assertNotNull(partialResult.getData());
    }
}