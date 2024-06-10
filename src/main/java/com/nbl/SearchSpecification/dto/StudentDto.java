package com.nbl.SearchSpecification.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class StudentDto {

    private Long id;
    private String name;
    private AddressDto addressDto;


    private Set<SubjectDto> subjectDtos;
}
