package com.joaomeleski.peoplemanager.service;

import com.joaomeleski.peoplemanager.domain.exception.ResourceNotFoundException;
import com.joaomeleski.peoplemanager.infrastructure.repository.AddressRepository;
import com.joaomeleski.peoplemanager.infrastructure.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static stub.Stubs.buildAddressWithEmbeddedPerson;
import static stub.Stubs.buildCreateAddressDtoStub;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private AddressRepository addressRepository;

    @Test
    void mustThrowException_ifPersonIsNotFound() {
        when(personRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> addressService.createAddressForExistingPerson(1L, buildCreateAddressDtoStub()));
    }

    @Test
    void mustCreateAddressForExistingPersonSuccessfully() {
        var stub = buildAddressWithEmbeddedPerson();

        when(personRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(stub.getPerson()));

        when(addressRepository.countMainAddressesByPersonId(Mockito.anyLong()))
                .thenReturn(0);

        var addAddress = buildCreateAddressDtoStub();
        var result = addressService.createAddressForExistingPerson(1L, addAddress);

        Mockito.verify(addressRepository, Mockito.times(1)).saveAndFlush(Mockito.any());
        Assertions.assertEquals(result.street(), addAddress.street());
        Assertions.assertEquals(result.number(), addAddress.number());
        Assertions.assertEquals(result.city(), addAddress.city());
        Assertions.assertEquals(result.state(), addAddress.state());
        Assertions.assertEquals(result.neighborhood(), addAddress.neighborhood());
        Assertions.assertEquals(result.mainAddress(), addAddress.mainAddress());
    }
}