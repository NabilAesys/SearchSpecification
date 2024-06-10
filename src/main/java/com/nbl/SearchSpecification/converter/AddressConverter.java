package com.nbl.SearchSpecification.converter;

import com.nbl.SearchSpecification.model.Address;
import com.nbl.SearchSpecification.dto.AddressDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class AddressConverter {
    public AddressDto entityToDto(Address address) {

//		StudentDto dto = new StudentDto();
//		dto.setId(student.getId());
//		dto.setName(student.getName());
//		dto.setUsername(student.getUsername());
//		dto.setPassword(student.getPassword());
//
//		return dto;

        ModelMapper mapper =new ModelMapper();
        AddressDto map = mapper.map(address, AddressDto.class);
        return map;

    }
    public List<AddressDto> entityToDto(List<Address> address) {

        return	address.stream().map(x -> entityToDto(x)).collect(Collectors.toList());

    }


    public Address dtoToEntity(AddressDto dto)
    {
//		Student st = new Student();
//		st.setId(dto.getId());
//		st.setName(dto.getName());
//		st.setPassword(dto.getPassword());
//		st.setUsername(dto.getUsername());
//
//		return st;

        ModelMapper mapper = new ModelMapper();
        Address map = mapper.map(dto, Address.class);
        return map;
    }

    public List<Address> dtoToEntity(List<AddressDto> dto)
    {

        return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }
}
