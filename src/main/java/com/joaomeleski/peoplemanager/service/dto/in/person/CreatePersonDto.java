package com.joaomeleski.peoplemanager.service.dto.in.person;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * A DTO for the {@link com.joaomeleski.peoplemanager.domain.entity.Person} entity
 */
@Builder
public record CreatePersonDto(@NotNull @NotBlank @Schema(example = "João") String firstName,
                              @NotNull @NotBlank @Schema(example = "Dier") String lastName,
                              @NotNull @NotBlank @Schema(example = "08241589891") String cpf,
                              @NotNull @Schema(example = "2023-09-04") LocalDate birthDate,
                              Set<CreatePersonAddressDto> addresses) implements Serializable {
    /**
     * A DTO for the {@link com.joaomeleski.peoplemanager.domain.entity.Address} entity
     */
    @Builder
    public record CreatePersonAddressDto(@Size(min = 2, max = 2) @Schema(example = "RS") String state,
                                         @Size(min = 2, max = 25) @Schema(example = "Porto Alegre") String city,
                                         @Size(min = 2, max = 30) @Schema(example = "Jardim Carvalho") String neighborhood,
                                         @Size(min = 2, max = 30) @Schema(example = "Cotiporã") String street,
                                         @Min(1) @Schema(example = "16") Integer number,
                                         @Schema(example = "1")  boolean mainAddress) implements Serializable {
    }
}