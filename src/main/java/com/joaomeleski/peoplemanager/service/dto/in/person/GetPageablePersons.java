package com.joaomeleski.peoplemanager.service.dto.in.person;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record GetPageablePersons(int page,
                                 @Schema(example = "10") int size) {
}
