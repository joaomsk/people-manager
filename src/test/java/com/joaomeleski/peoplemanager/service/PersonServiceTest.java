package com.joaomeleski.peoplemanager.service;

import com.joaomeleski.peoplemanager.domain.entity.Person;
import com.joaomeleski.peoplemanager.domain.exception.MoreThanOneMainAddressException;
import com.joaomeleski.peoplemanager.domain.exception.ResourceNotFoundException;
import com.joaomeleski.peoplemanager.domain.exception.UpdatePersonModelNullException;
import com.joaomeleski.peoplemanager.infrastructure.repository.AddressRepository;
import com.joaomeleski.peoplemanager.infrastructure.repository.PersonRepository;
import com.joaomeleski.peoplemanager.service.dto.in.person.GetPageablePersons;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static stub.Stubs.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private AddressRepository addressRepository;

    @Test
    void mustThrowException_whenTryingToAddPerson_withMoreThen1MainAddress() {
        var personStub = buildCreatePersonStubWithTwoAddressesStub();

        Assertions.assertThrows(MoreThanOneMainAddressException.class,
                () -> personService.createPerson(personStub));
    }

    @Test
    void mustNotThrowException_whenTryingToAddAValidPerson() {
        var personEntityStub = buildPersonStub();
        when(personRepository.saveAndFlush(Mockito.any(Person.class)))
                .thenReturn(personEntityStub);

        var personStub = buildCreatePersonDtoWithOneAddressStub();
        var person = personService.createPerson(personStub);

        Assertions.assertDoesNotThrow(() -> personService.createPerson(personStub));
        Assertions.assertNotNull(person);
        Assertions.assertEquals(personStub.firstName(), person.firstName());
        Assertions.assertEquals(personStub.lastName(), person.lastName());
        Assertions.assertEquals(personStub.cpf(), person.cpf());
        Assertions.assertEquals(personStub.addresses().size(), person.addresses().size());
    }

    @Test
    void mustReturnPerson_whenTryingToFindPersonById() {
        var personEntityStub = buildPersonStub();
        when(personRepository.findById(Mockito.anyLong()))
                .thenReturn(java.util.Optional.of(personEntityStub));

        var person = personService.findPersonById(1L);

        Assertions.assertNotNull(person);
        Assertions.assertEquals(personEntityStub.getFirstName(), person.firstName());
        Assertions.assertEquals(personEntityStub.getLastName(), person.lastName());
        Assertions.assertEquals(personEntityStub.getCpf(), person.cpf());
        Assertions.assertEquals(personEntityStub.getAddresses().size(), person.addresses().size());
    }

    @Test
    void mustReturnPersonsSuccessfully_whenTryingToFindAllPersons() {
        var personEntityStub = buildPersonStub();
        var pageResult = new PageImpl<>(List.of(personEntityStub));

        when(personRepository.getPageablePersons(Mockito.any()))
                .thenReturn(pageResult);

        var persons = personService.findAllPersons(new GetPageablePersons(0, 10));

        Assertions.assertNotNull(persons);
        Assertions.assertEquals(1, persons.getData().size());
        Assertions.assertEquals(personEntityStub.getFirstName(), persons.getData().get(0).firstName());
        Assertions.assertEquals(personEntityStub.getLastName(), persons.getData().get(0).lastName());
        Assertions.assertEquals(personEntityStub.getCpf(), persons.getData().get(0).cpf());
        Assertions.assertEquals(personEntityStub.getAddresses().size(), persons.getData().get(0).addresses().size());
    }

    @Test
    void mustThrowException_whenTryingToUpdateAPerson_withModelNull() {
        var personStub = buildPersonStub();
        when(personRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(personStub));

        Assertions.assertThrows(UpdatePersonModelNullException.class,
                () -> personService.updatePersonById(1L, null));
    }

    @Test
    void mustThrowException_whenTryingToUpdateAPerson_notInSystem() {
        when(personRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> personService.updatePersonById(1L, buildUpdatePersonDto()));
    }

    @Test
    void mustVerifyIfPerson_isBeingUpdatedSuccessfully() {
        var personStub = buildPersonStub();
        when(personRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(personStub));

        var personUpdateStub = buildUpdatePersonDto();
        personService.updatePersonById(1L, personUpdateStub);

        verify(personRepository).updatePersonById(personUpdateStub.firstName(),
                personUpdateStub.lastName(),
                personUpdateStub.cpf(),
                personUpdateStub.birthDate(),
                personStub.getId());
    }

    @Test
    void mustVerifyIfPerson_isBeingExcludedSuccessfully() {
        var personStub = buildPersonStub();
        when(personRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(personStub));

        personService.deletePersonById(1L);

        verify(personRepository).delete(personStub);
    }
}