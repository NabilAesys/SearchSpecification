package com.nbl.SearchSpecification.controller;


import com.nbl.SearchSpecification.model.Student;
import com.nbl.SearchSpecification.dto.RequestDto;
import com.nbl.SearchSpecification.dto.StudentDto;
import com.nbl.SearchSpecification.repository.StudentRepository;
import com.nbl.SearchSpecification.service.AddressService;
import com.nbl.SearchSpecification.service.FiltersSpecification;
import com.nbl.SearchSpecification.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/filter")
public class FilterController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    private AddressService addressService;

    @Autowired
    private FiltersSpecification<Student> studentFiltersSpecification;

    @GetMapping("/{name}")
    public Student getStdByName(@PathVariable(name = "name") String name) {

        return studentService.getStdByName(name);
    }

    @GetMapping("/city/{CITY}")
    public List<Student> getStdByCity(@PathVariable(name = "CITY") String city) {

        return studentService.getStdByCity(city);
    }

    @GetMapping("/subject/{SUB}")
    public List<Student> getStdBySubject(@PathVariable(name = "SUB") String subject) {

        return studentService.getStdBySubject(subject);
    }

//    @PostMapping("/specification")
//    public List<Student> getStudents() {
//        Specification<Student> specification = new Specification<Student>() {
//
//            @Override
//            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                return criteriaBuilder.equal(root.get("id"), "3");
//            }
//        };
//        List<Student> all = studentRepository.findAll(specification);
//        return all;
//    }


//    @PostMapping("/specification")
//    public List<StudentDto> getStudents(@RequestBody RequestDto requestDto) {
//        Specification<Student> searchSpecification = studentFiltersSpecification
//                .getSearchSpecification(requestDto.getSearchRequestDto(), requestDto.getGlobalOperator());
//        return studentService.findBy(searchSpecification);}

    @PostMapping("/specification")
    public List<Student> getStudents(@RequestBody RequestDto requestDto) {
        return studentService.getStudents(requestDto);
    }

    @PostMapping("/specification/pagination")
    public Page<Student> getStudentsPage(@RequestBody RequestDto requestDto) {
        return studentService.getStudentPage(requestDto);
    }


}
