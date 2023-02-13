package stub;

import com.joaomeleski.peoplemanager.domain.entity.Address;
import com.joaomeleski.peoplemanager.domain.entity.Person;
import com.joaomeleski.peoplemanager.service.dto.in.address.CreateAddressDto;
import com.joaomeleski.peoplemanager.service.dto.in.person.CreatePersonDto;
import com.joaomeleski.peoplemanager.service.dto.in.person.UpdatePersonDto;

import java.time.LocalDate;
import java.util.Set;

public class Stubs {
    public static CreatePersonDto buildCreatePersonDtoWithOneAddressStub() {
        return CreatePersonDto.builder()
                .firstName("João")
                .lastName("Meleski")
                .cpf("12345678901")
                .birthDate(LocalDate.of(1990, 1, 1))
                .addresses(Set.of(buildCreatePersonAddressDtoStub()))
                .build();
    }

    public static CreatePersonDto buildCreatePersonStubWithTwoAddressesStub() {
        return CreatePersonDto.builder()
                .firstName("João")
                .lastName("Meleski")
                .cpf("12345678901")
                .birthDate(LocalDate.of(1990, 1, 1))
                .addresses(Set.of(buildCreatePersonAddressDtoStub(), buildCreatePersonAddressDtoWithDiffDataStub()))
                .build();
    }

    public static CreatePersonDto.CreatePersonAddressDto buildCreatePersonAddressDtoStub() {
        return CreatePersonDto.CreatePersonAddressDto.builder()
                .street("Rua dos Bobinhos")
                .number(10)
                .city("Porto Alegre")
                .state("RS")
                .mainAddress(true)
                .build();
    }

    public static CreatePersonDto.CreatePersonAddressDto buildCreatePersonAddressDtoWithDiffDataStub() {
        return CreatePersonDto.CreatePersonAddressDto.builder()
                .street("Rua dos Bobos")
                .number(5)
                .city("São Paulo")
                .state("SP")
                .mainAddress(true)
                .build();
    }

    public static Person buildPersonStub() {
        return Person.builder()
                .id(1L)
                .firstName("João")
                .lastName("Meleski")
                .cpf("12345678901")
                .birthDate(LocalDate.of(1990, 1, 1))
                .addresses(Set.of(buildAddressStub()))
                .build();
    }

    public static Address buildAddressStub() {
        return Address.builder()
                .id(1L)
                .street("Rua dos Bobinhos")
                .number(10)
                .city("Porto Alegre")
                .state("RS")
                .mainAddress(true)
                .build();
    }

    public static Address buildAddressWithEmbeddedPerson() {
        return Address.builder()
                .id(1L)
                .street("Rua dos Bobinhos")
                .number(10)
                .city("Porto Alegre")
                .state("RS")
                .mainAddress(true)
                .person(buildPersonStub())
                .build();
    }

    public static UpdatePersonDto buildUpdatePersonDto() {
        return UpdatePersonDto.builder()
                .firstName("João")
                .lastName("Meleski")
                .cpf("12345678901")
                .birthDate(LocalDate.of(1990, 1, 1))
                .build();
    }

    public static CreateAddressDto buildCreateAddressDtoStub() {
        return CreateAddressDto.builder()
                .street("Rua dos Bobinhos")
                .number(10)
                .city("Porto Alegre")
                .state("RS")
                .mainAddress(true)
                .build();
    }
}
