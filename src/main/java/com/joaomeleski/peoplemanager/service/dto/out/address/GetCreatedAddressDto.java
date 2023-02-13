package com.joaomeleski.peoplemanager.service.dto.out.address;

import lombok.Builder;

@Builder
public record GetCreatedAddressDto(Long id,
                                   String street,
                                   Number number,
                                   String neighborhood,
                                   String city,
                                   String state,
                                   boolean mainAddress) {
}
