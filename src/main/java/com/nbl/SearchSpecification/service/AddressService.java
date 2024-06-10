package com.nbl.SearchSpecification.service;

import com.nbl.SearchSpecification.dto.StudentDto;
import com.nbl.SearchSpecification.repository.AddressRepository;
import com.nbl.SearchSpecification.converter.AddressConverter;
import com.nbl.SearchSpecification.model.Address;
import com.nbl.SearchSpecification.model.Student;
import com.nbl.SearchSpecification.dto.AddressDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    AddressConverter converter;

    @Autowired
    private FiltersSpecification<Student> studentFiltersSpecification;


    private ModelMapper modelMapper;

    public Address getAddressById(Long id) {

        return addressRepository.findById(id).orElse(null);
    }

    public List<Address> getAddress() {
        return addressRepository.findAll();
    }

    public AddressDto createAddress(Address address){
        if (address != null){
            addressRepository.save(address);
        }
        return converter.entityToDto(address);
    }


    private AddressDto convertEntityToDto(Address address){
        modelMapper.getConfiguration()//per matchare alche 'latitude' e 'longitude'
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        AddressDto addressDto = new AddressDto();
        addressDto = modelMapper.map(address, AddressDto.class);
        return addressDto;
    }

    private Address convertDtoToEntity(AddressDto addressDto){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Address address = new Address();
        address = modelMapper.map(addressDto, Address.class);
        return address;
    }

    public String deleteById(Long id) {
        if (addressRepository.existsById(id)){
            addressRepository.deleteAllById(Collections.singleton(id));
            return "Student Deleted Successfully.";
        }
        return  "id Student non esistente";
    }

    public String deleteAll() {
        if (addressRepository.findAll().isEmpty()){
            return "Lista è vuota!";
        }  else
            addressRepository.deleteAll();
        return "lista svuotata";
    }

    public AddressDto getById(Long id) {
        Address address = addressRepository.findById(id).get();
        if (address != null){
            return converter.entityToDto(address);
        }
        return null;
    }
}
