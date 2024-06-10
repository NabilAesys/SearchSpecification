package com.nbl.SearchSpecification.controller;

import com.nbl.SearchSpecification.dto.AddressDto;
import com.nbl.SearchSpecification.dto.StudentDto;
import com.nbl.SearchSpecification.model.Address;
import com.nbl.SearchSpecification.model.Student;
import com.nbl.SearchSpecification.model.Subject;
import com.nbl.SearchSpecification.repository.AddressRepository;
import com.nbl.SearchSpecification.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdressController {
    @Autowired
    private AddressService addressService;

    @PostMapping("/createAddress")
    public ResponseEntity<AddressDto> createAddress(@RequestBody Address address){
        return ResponseEntity.ok(addressService.createAddress(address));
    }

    @GetMapping(value="/address/{id}")
    public AddressDto getAddress(@PathVariable Long id ){
        return addressService.getById(id);
    }

    @DeleteMapping(value="/address/{id}")
    public String deleteAddress(@PathVariable Long id ){
        return addressService.deleteById(id);
    }

    @DeleteMapping(value="/address")
    public String deleteAddress(){
        return addressService.deleteAll();
    }
}
