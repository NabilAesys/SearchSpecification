package com.nbl.SearchSpecification.service;

import com.nbl.SearchSpecification.converter.SubjectConverter;
import com.nbl.SearchSpecification.dto.AddressDto;
import com.nbl.SearchSpecification.dto.SubjectDto;
import com.nbl.SearchSpecification.model.Address;
import com.nbl.SearchSpecification.model.Student;
import com.nbl.SearchSpecification.model.Subject;
import com.nbl.SearchSpecification.repository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    SubjectConverter converter;

    @Autowired
    private FiltersSpecification<Student> studentFiltersSpecification;


    private ModelMapper modelMapper;

    public Subject getSubjectById(Long id) {

        return subjectRepository.findById(id).orElse(null);
    }



    public List<Subject> getAddress() {
        return subjectRepository.findAll();
    }

    public SubjectDto createSubject(Subject subject){
        if (subject != null){
            subjectRepository.save(subject);
        }
        return converter.entityToDto(subject);
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
        if (subjectRepository.existsById(id)){
            subjectRepository.deleteAllById(Collections.singleton(id));
            return "Student Deleted Successfully.";
        }
        return  "id Student non esistente";
    }

    public String deleteAll() {
        if (subjectRepository.findAll().isEmpty()){
            return "Lista è vuota!";
        }  else
            subjectRepository.deleteAll();
        return "lista svuotata";
    }
}
