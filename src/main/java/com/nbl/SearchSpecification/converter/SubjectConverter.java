package com.nbl.SearchSpecification.converter;

import com.nbl.SearchSpecification.dto.SubjectDto;
import com.nbl.SearchSpecification.model.Subject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class SubjectConverter {
    public SubjectDto entityToDto(Subject subject) {

//		StudentDto dto = new StudentDto();
//		dto.setId(student.getId());
//		dto.setName(student.getName());
//		dto.setUsername(student.getUsername());
//		dto.setPassword(student.getPassword());
//
//		return dto;

        ModelMapper mapper =new ModelMapper();
        SubjectDto map = mapper.map(subject, SubjectDto.class);
        return map;

    }
    public List<SubjectDto> entityToDto(List<Subject> subjects) {

        return	subjects.stream().map(this::entityToDto).collect(Collectors.toList());

    }


    public Subject dtoToEntity(SubjectDto dto)
    {
//		Student st = new Student();
//		st.setId(dto.getId());
//		st.setName(dto.getName());
//		st.setPassword(dto.getPassword());
//		st.setUsername(dto.getUsername());
//
//		return st;

        ModelMapper mapper = new ModelMapper();
        Subject map = mapper.map(dto, Subject.class);
        return map;
    }

    public List<Subject> dtoToEntity(List<SubjectDto> dto)
    {

        return dto.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
