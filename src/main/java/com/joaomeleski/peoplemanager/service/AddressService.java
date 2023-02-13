package com.joaomeleski.peoplemanager.service;

import com.joaomeleski.peoplemanager.domain.entity.Address;
import com.joaomeleski.peoplemanager.domain.entity.Person;
import com.joaomeleski.peoplemanager.domain.exception.ResourceNotFoundException;
import com.joaomeleski.peoplemanager.infrastructure.repository.AddressRepository;
import com.joaomeleski.peoplemanager.infrastructure.repository.PersonRepository;
import com.joaomeleski.peoplemanager.service.dto.in.address.CreateAddressDto;
import com.joaomeleski.peoplemanager.service.dto.out.address.GetCreatedAddressDto;
import org.springframework.stereotype.Service;

import static com.joaomeleski.peoplemanager.infrastructure.mapper.AddressMapper.mapToGetCreatedAddressDto;

@Service
public class AddressService {

    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;

    public AddressService(PersonRepository personRepository, AddressRepository addressRepository) {
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
    }

    public GetCreatedAddressDto createAddressForExistingPerson(Long personId, CreateAddressDto model) {
        var address = validateAndBuildAddressToBeInserted(findPersonByIdOrThrowException(personId), model);
        addressRepository.saveAndFlush(address);
        return mapToGetCreatedAddressDto(address);
    }

    private boolean personHasAtLeastOneMainAddress(long personId) {
        return addressRepository.countMainAddressesByPersonId(personId) > 0;
    }

    private Address validateAndBuildAddressToBeInserted(Person person, CreateAddressDto model) {
        return Address.builder()
                .state(model.state())
                .city(model.city())
                .street(model.street())
                .number(model.number())
                .neighborhood(model.neighborhood())
                .mainAddress(!personHasAtLeastOneMainAddress(person.getId()))
                .person(person)
                .build();
    }

    private Person findPersonByIdOrThrowException(long personId) {
        return personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found"));
    }
}
