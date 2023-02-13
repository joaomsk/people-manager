package com.joaomeleski.peoplemanager.service.dto.in.address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record CreateAddressDto(@Schema(example = "RS") String state,
                               @Schema(example = "Porto Alegre") String city,
                               @Schema(example = "Jardim Carvalho") String neighborhood,
                               @Schema(example = "Rua Bom Principio") String street,
                               @Schema(example = "10") Integer number,
                               boolean mainAddress) {
}
