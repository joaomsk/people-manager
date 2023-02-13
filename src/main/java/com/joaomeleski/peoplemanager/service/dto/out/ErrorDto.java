package com.joaomeleski.peoplemanager.service.dto.out;

import java.time.LocalDateTime;

public record ErrorDto(String message, LocalDateTime timestamp) {
}
