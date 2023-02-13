package com.joaomeleski.peoplemanager.service.dto.out.person;

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
public record GetPersonDto(Long id, @NotNull @NotBlank String firstName, @NotNull @NotBlank String lastName,
                           @NotNull @NotBlank String cpf, @NotNull LocalDate birthDate,
                           Set<GetPersonAddressDto> addresses) implements Serializable {
    /**
     * A DTO for the {@link com.joaomeleski.peoplemanager.domain.entity.Address} entity
     */
    @Builder
    public record GetPersonAddressDto(Long id, @Size(min = 2, max = 2) String state,
                                      @Size(min = 2, max = 25) String city,
                                      @Size(min = 2, max = 30) String neighborhood,
                                      @Size(min = 2, max = 30) String street, @Min(1) Integer number,
                                      boolean mainAddress) implements Serializable {
    }
}