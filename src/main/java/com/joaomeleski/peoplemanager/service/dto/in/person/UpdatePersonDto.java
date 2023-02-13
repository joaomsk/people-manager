package com.joaomeleski.peoplemanager.service.dto.in.person;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UpdatePersonDto(@Schema(example = "João") @Nullable String firstName,
                              @Schema(example = "Dier") @Nullable String lastName,
                              @Schema(example = "71011389690") @Nullable String cpf,
                              @Schema(example = "2001-09-04") @Nullable LocalDate birthDate) {
}
