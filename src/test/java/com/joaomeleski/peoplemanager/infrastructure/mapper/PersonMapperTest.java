package com.joaomeleski.peoplemanager.infrastructure.mapper;

import com.joaomeleski.peoplemanager.domain.entity.Person;
import com.joaomeleski.peoplemanager.service.dto.out.person.CreatedPersonDto;
import com.joaomeleski.peoplemanager.service.dto.out.person.GetPersonDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static stub.Stubs.buildCreatePersonDtoWithOneAddressStub;
import static stub.Stubs.buildPersonStub;

class PersonMapperTest {
    @Test
    void givenCreatePersonDtoThenReturnPersonEntity() {
        var createPersonDtoStub = buildCreatePersonDtoWithOneAddressStub();
        var convertedPerson = PersonMapper.mapToPerson(createPersonDtoStub);

        Assertions.assertInstanceOf(Person.class, convertedPerson);
        Assertions.assertEquals(createPersonDtoStub.firstName(), convertedPerson.getFirstName());
        Assertions.assertEquals(createPersonDtoStub.lastName(), convertedPerson.getLastName());
        Assertions.assertEquals(createPersonDtoStub.cpf(), convertedPerson.getCpf());
        Assertions.assertEquals(createPersonDtoStub.birthDate(), convertedPerson.getBirthDate());
    }

    @Test
    void givenPersonEntityThenReturnCreatedPersonDto() {
        var createPersonDtoStub = buildPersonStub();
        var convertedPerson = PersonMapper.mapToCreatedPersonDto(createPersonDtoStub);

        Assertions.assertInstanceOf(CreatedPersonDto.class, convertedPerson);
        Assertions.assertEquals(createPersonDtoStub.getId(), convertedPerson.id());
        Assertions.assertEquals(createPersonDtoStub.getFirstName(), convertedPerson.firstName());
        Assertions.assertEquals(createPersonDtoStub.getLastName(), convertedPerson.lastName());
        Assertions.assertEquals(createPersonDtoStub.getCpf(), convertedPerson.cpf());
        Assertions.assertEquals(createPersonDtoStub.getBirthDate(), convertedPerson.birthDate());
    }

    @Test
    void givenPersonEntityThenReturnGetPersonDto() {
        var createPersonDtoStub = buildPersonStub();
        var convertedPerson = PersonMapper.mapToGetPersonDto(createPersonDtoStub);

        Assertions.assertInstanceOf(GetPersonDto.class, convertedPerson);
        Assertions.assertEquals(createPersonDtoStub.getId(), convertedPerson.id());
        Assertions.assertEquals(createPersonDtoStub.getFirstName(), convertedPerson.firstName());
        Assertions.assertEquals(createPersonDtoStub.getLastName(), convertedPerson.lastName());
        Assertions.assertEquals(createPersonDtoStub.getCpf(), convertedPerson.cpf());
        Assertions.assertEquals(createPersonDtoStub.getBirthDate(), convertedPerson.birthDate());
    }
}