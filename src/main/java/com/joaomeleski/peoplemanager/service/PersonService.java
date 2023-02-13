package com.joaomeleski.peoplemanager.service;

import com.joaomeleski.peoplemanager.domain.entity.Address;
import com.joaomeleski.peoplemanager.domain.entity.Person;
import com.joaomeleski.peoplemanager.domain.exception.MoreThanOneMainAddressException;
import com.joaomeleski.peoplemanager.domain.exception.UpdatePersonModelNullException;
import com.joaomeleski.peoplemanager.domain.exception.ResourceNotFoundException;
import com.joaomeleski.peoplemanager.infrastructure.mapper.PersonMapper;
import com.joaomeleski.peoplemanager.infrastructure.repository.AddressRepository;
import com.joaomeleski.peoplemanager.infrastructure.repository.PersonRepository;
import com.joaomeleski.peoplemanager.service.dto.in.person.CreatePersonDto;
import com.joaomeleski.peoplemanager.service.dto.in.person.GetPageablePersons;
import com.joaomeleski.peoplemanager.service.dto.in.person.UpdatePersonDto;
import com.joaomeleski.peoplemanager.service.dto.out.person.CreatedPersonDto;
import com.joaomeleski.peoplemanager.service.dto.out.person.GetPersonDto;
import com.joaomeleski.peoplemanager.util.PartialResult;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.joaomeleski.peoplemanager.util.ValueOrDefault.getValueOrDefault;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;

    public PersonService(PersonRepository personRepository, AddressRepository addressRepository) {
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
    }

    public CreatedPersonDto createPerson(CreatePersonDto model) {
        validateIfThereIsMoreThan1AddressAsMain(model.addresses());
        var createdPerson = personRepository.saveAndFlush(PersonMapper.mapToPerson(model));

        attachAddressToPerson(createdPerson, createdPerson.getAddresses().stream()
                .map(Address::getId)
                .collect(Collectors.toSet()));

        return PersonMapper.mapToCreatedPersonDto(createdPerson);
    }

    public GetPersonDto findPersonById(long personId) {
        var person = findPersonByIdOrThrowException(personId);

        return PersonMapper.mapToGetPersonDto(person);
    }

    public PartialResult<List<GetPersonDto>> findAllPersons(GetPageablePersons model) {
        var pageablePersons = personRepository.getPageablePersons(
                PageRequest.of(model.page(), model.size()));

        PageImpl<GetPersonDto> convertedReviews = new PageImpl<>(pageablePersons.getContent().stream()
                .map(PersonMapper::mapToGetPersonDto)
                .toList(), pageablePersons.getPageable(), pageablePersons.getTotalElements());

        return new PartialResult<>(convertedReviews.getContent(), model.page(), convertedReviews.getNumberOfElements(), convertedReviews.getTotalElements());
    }

    public void updatePersonById(Long personId, UpdatePersonDto model) {
        var person = compareAndBuildPersonWithNewData(model, findPersonByIdOrThrowException(personId));
        personRepository.updatePersonById(person.getFirstName(), person.getLastName(), person.getCpf(), person.getBirthDate(), person.getId());
    }

    public void deletePersonById(long personId) {
        var person = findPersonByIdOrThrowException(personId);
        personRepository.delete(person);
    }

    private Person compareAndBuildPersonWithNewData(UpdatePersonDto model, Person person) {
        if (model == null) throw new UpdatePersonModelNullException("Model can't be null");

        return Person.builder()
                .id(person.getId())
                .firstName(getValueOrDefault(model.firstName(), person.getFirstName()))
                .cpf(getValueOrDefault(model.cpf(), person.getCpf()))
                .lastName(getValueOrDefault(model.lastName(), person.getLastName()))
                .birthDate(getValueOrDefault(model.birthDate(), person.getBirthDate()))
                .addresses(person.getAddresses())
                .build();
    }

    private Person findPersonByIdOrThrowException(long personId) {
        return personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found"));
    }

    private void validateIfThereIsMoreThan1AddressAsMain(Set<CreatePersonDto.CreatePersonAddressDto> addresses) {
        var moreThanOneMain = addresses.stream()
                .map(CreatePersonDto.CreatePersonAddressDto::mainAddress)
                .count() > 1;

        if (moreThanOneMain) {
            throw new MoreThanOneMainAddressException("There is more than one main addresses");
        }
    }

    private void attachAddressToPerson(Person person, Set<Long> addressesIds) {
        addressRepository.updatePersonAddressById(person, addressesIds);
    }
}
