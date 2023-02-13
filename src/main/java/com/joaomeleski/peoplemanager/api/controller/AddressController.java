package com.joaomeleski.peoplemanager.api.controller;

import com.joaomeleski.peoplemanager.service.AddressService;
import com.joaomeleski.peoplemanager.service.dto.in.address.CreateAddressDto;
import com.joaomeleski.peoplemanager.service.dto.out.address.GetCreatedAddressDto;
import com.joaomeleski.peoplemanager.util.UriBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Operation(summary = "Create a new address for an existing person",
            description = "Use this endpoint do create and attach an address to an existing person in the system.")
    @PostMapping({"/{id}"})
    public ResponseEntity<GetCreatedAddressDto> createAddressForExistingPerson(@PathVariable("id")
                                                                                   @Schema(example = "1") Long personId, @RequestBody CreateAddressDto model) {
        var createdAddress = addressService.createAddressForExistingPerson(personId, model);
        return ResponseEntity.created(UriBuilder.buildLocationUri(createdAddress.id())).body(createdAddress);
    }
}
