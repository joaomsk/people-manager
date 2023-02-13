package com.joaomeleski.peoplemanager.infrastructure.mapper;

import com.joaomeleski.peoplemanager.domain.entity.Address;
import com.joaomeleski.peoplemanager.service.dto.in.person.CreatePersonDto;
import com.joaomeleski.peoplemanager.service.dto.out.address.GetCreatedAddressDto;
import com.joaomeleski.peoplemanager.service.dto.out.person.CreatedPersonDto;
import com.joaomeleski.peoplemanager.service.dto.out.person.GetPersonDto;

import java.util.Set;
import java.util.stream.Collectors;

public class AddressMapper {

    public static Set<Address> mapToAddress(Set<CreatePersonDto.CreatePersonAddressDto> model) {
        return model.stream().map(
                address -> Address.builder()
                        .state(address.state())
                        .city(address.city())
                        .neighborhood(address.neighborhood())
                        .street(address.street())
                        .number(address.number())
                        .mainAddress(address.mainAddress())
                        .build()
        ).collect(Collectors.toSet());
    }

    public static Set<CreatedPersonDto.CreatedPersonAddressDto> mapToCreatedPersonAddressDto(Set<Address> addresses) {
        return addresses.stream().map(
                address -> CreatedPersonDto.CreatedPersonAddressDto.builder()
                        .id(address.getId())
                        .state(address.getState())
                        .city(address.getCity())
                        .neighborhood(address.getNeighborhood())
                        .street(address.getStreet())
                        .number(address.getNumber())
                        .mainAddress(address.isMainAddress())
                        .build()
        ).collect(Collectors.toSet());
    }

    public static Set<GetPersonDto.GetPersonAddressDto> mapToGetPersonAddressDto(Set<Address> addresses) {
        return addresses.stream().map(
                address -> GetPersonDto.GetPersonAddressDto.builder()
                        .id(address.getId())
                        .state(address.getState())
                        .city(address.getCity())
                        .neighborhood(address.getNeighborhood())
                        .street(address.getStreet())
                        .number(address.getNumber())
                        .mainAddress(address.isMainAddress())
                        .build()
        ).collect(Collectors.toSet());
    }

    public static GetCreatedAddressDto mapToGetCreatedAddressDto(Address address) {
        return GetCreatedAddressDto.builder()
                .id(address.getId())
                .state(address.getState())
                .city(address.getCity())
                .neighborhood(address.getNeighborhood())
                .street(address.getStreet())
                .number(address.getNumber())
                .mainAddress(address.isMainAddress())
                .build();
    }
}
