package com.joaomeleski.peoplemanager.infrastructure.mapper;

import com.joaomeleski.peoplemanager.domain.entity.Person;
import com.joaomeleski.peoplemanager.service.dto.in.person.CreatePersonDto;
import com.joaomeleski.peoplemanager.service.dto.out.person.CreatedPersonDto;
import com.joaomeleski.peoplemanager.service.dto.out.person.GetPersonDto;

public class PersonMapper {

    public static Person mapToPerson(CreatePersonDto model) {
        return Person.builder()
                .firstName(model.firstName())
                .lastName(model.lastName())
                .cpf(model.cpf())
                .birthDate(model.birthDate())
                .addresses(AddressMapper.mapToAddress(model.addresses()))
                .build();
    }

    public static CreatedPersonDto mapToCreatedPersonDto(Person person) {
        return CreatedPersonDto.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .cpf(person.getCpf())
                .birthDate(person.getBirthDate())
                .addresses(AddressMapper.mapToCreatedPersonAddressDto(person.getAddresses()))
                .build();
    }

    public static GetPersonDto mapToGetPersonDto(Person person) {
        return GetPersonDto.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .cpf(person.getCpf())
                .birthDate(person.getBirthDate())
                .addresses(AddressMapper.mapToGetPersonAddressDto(person.getAddresses()))
                .build();

    }
}
