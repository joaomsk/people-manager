package com.joaomeleski.peoplemanager.api.controller;

import com.joaomeleski.peoplemanager.service.PersonService;
import com.joaomeleski.peoplemanager.service.dto.in.person.GetPageablePersons;
import com.joaomeleski.peoplemanager.service.dto.in.person.CreatePersonDto;
import com.joaomeleski.peoplemanager.service.dto.in.person.UpdatePersonDto;
import com.joaomeleski.peoplemanager.service.dto.out.person.CreatedPersonDto;
import com.joaomeleski.peoplemanager.service.dto.out.person.GetPersonDto;
import com.joaomeleski.peoplemanager.util.PartialResult;
import com.joaomeleski.peoplemanager.util.UriBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @Operation(summary = "Create a new person",
            description = "Use this endpoint do create a new person in the system.")
    @PostMapping
    public ResponseEntity<CreatedPersonDto> createPerson(@RequestBody CreatePersonDto model) {
        CreatedPersonDto createdPerson = personService.createPerson(model);
        return ResponseEntity.created(UriBuilder.buildLocationUri(createdPerson.id())).body(createdPerson);
    }

    @Operation(summary = "Get all persons",
            description = "Use this endpoint to get all persons in the system. You can also use pagination.")
    @GetMapping
    public ResponseEntity<PartialResult<List<GetPersonDto>>> getAllPersons(GetPageablePersons pageable) {
        var partialPersonsResult = personService.findAllPersons(pageable);
        return new ResponseEntity<>(partialPersonsResult, partialPersonsResult.getHeaders(), HttpStatus.PARTIAL_CONTENT);
    }

    @Operation(summary = "Get a person by id",
            description = "Use this endpoint to get a person by id in the system.")
    @GetMapping("/{id}")
    public ResponseEntity<GetPersonDto> getPersonById(@PathVariable("id") @Schema(example = "1") long personId) {
        return ResponseEntity.ok(personService.findPersonById(personId));
    }

    @Operation(summary = "Update a person by id",
            description = "Use this endpoint to update a person by id in the system.")
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePersonById(@PathVariable("id") long personId, @RequestBody UpdatePersonDto model) {
        personService.updatePersonById(personId, model);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a person by id",
            description = "Use this endpoint to delete a person by id in the system.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonById(@PathVariable("id") long personId) {
        personService.deletePersonById(personId);
        return ResponseEntity.noContent().build();
    }
}
