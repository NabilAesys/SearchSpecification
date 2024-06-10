package com.nbl.SearchSpecification.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectDto {

    private Long id;
    private String name;
    private StudentDto studentDto;
}
