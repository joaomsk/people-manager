package com.joaomeleski.peoplemanager.infrastructure.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static stub.Stubs.buildAddressStub;
import static stub.Stubs.buildCreatePersonAddressDtoStub;

class AddressMapperTest {
    @Test
    void givenAddressDTOThenReturnAddressEntity() {
        var stubCreateAddressDto = Set.of(buildCreatePersonAddressDtoStub());
        var convertedAddress = AddressMapper.mapToAddress(stubCreateAddressDto);

        Assertions.assertEquals(stubCreateAddressDto.size(), convertedAddress.size());
        Assertions.assertEquals(stubCreateAddressDto.iterator().next().state(), convertedAddress.iterator().next().getState());
        Assertions.assertEquals(stubCreateAddressDto.iterator().next().city(), convertedAddress.iterator().next().getCity());
        Assertions.assertEquals(stubCreateAddressDto.iterator().next().neighborhood(), convertedAddress.iterator().next().getNeighborhood());
        Assertions.assertEquals(stubCreateAddressDto.iterator().next().street(), convertedAddress.iterator().next().getStreet());
        Assertions.assertEquals(stubCreateAddressDto.iterator().next().number(), convertedAddress.iterator().next().getNumber());
        Assertions.assertEquals(stubCreateAddressDto.iterator().next().mainAddress(), convertedAddress.iterator().next().isMainAddress());
    }

    @Test
    void givenAddressEntityThenReturnCreatedAddressDto() {
        var stubAddressDto = Set.of(buildAddressStub());
        var convertedAddress = AddressMapper.mapToCreatedPersonAddressDto(stubAddressDto);

        Assertions.assertEquals(stubAddressDto.size(), convertedAddress.size());
        Assertions.assertEquals(stubAddressDto.iterator().next().getId(), convertedAddress.iterator().next().id());
        Assertions.assertEquals(stubAddressDto.iterator().next().getState(), convertedAddress.iterator().next().state());
        Assertions.assertEquals(stubAddressDto.iterator().next().getCity(), convertedAddress.iterator().next().city());
        Assertions.assertEquals(stubAddressDto.iterator().next().getNeighborhood(), convertedAddress.iterator().next().neighborhood());
        Assertions.assertEquals(stubAddressDto.iterator().next().getStreet(), convertedAddress.iterator().next().street());
        Assertions.assertEquals(stubAddressDto.iterator().next().getNumber(), convertedAddress.iterator().next().number());
        Assertions.assertEquals(stubAddressDto.iterator().next().isMainAddress(), convertedAddress.iterator().next().mainAddress());
    }

    @Test
    void givenAddressEntityThenReturnGetPersonAddressDto() {
        var stubAddressDto = Set.of(buildAddressStub());
        var convertedAddress = AddressMapper.mapToGetPersonAddressDto(stubAddressDto);

        Assertions.assertEquals(stubAddressDto.size(), convertedAddress.size());
        Assertions.assertEquals(stubAddressDto.iterator().next().getId(), convertedAddress.iterator().next().id());
        Assertions.assertEquals(stubAddressDto.iterator().next().getState(), convertedAddress.iterator().next().state());
        Assertions.assertEquals(stubAddressDto.iterator().next().getCity(), convertedAddress.iterator().next().city());
        Assertions.assertEquals(stubAddressDto.iterator().next().getNeighborhood(), convertedAddress.iterator().next().neighborhood());
        Assertions.assertEquals(stubAddressDto.iterator().next().getStreet(), convertedAddress.iterator().next().street());
        Assertions.assertEquals(stubAddressDto.iterator().next().getNumber(), convertedAddress.iterator().next().number());
        Assertions.assertEquals(stubAddressDto.iterator().next().isMainAddress(), convertedAddress.iterator().next().mainAddress());
    }
}