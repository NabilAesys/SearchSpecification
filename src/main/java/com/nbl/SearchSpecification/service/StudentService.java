package com.nbl.SearchSpecification.service;

import com.nbl.SearchSpecification.dto.PageRequestDto;
import com.nbl.SearchSpecification.repository.StudentRepository;
import com.nbl.SearchSpecification.converter.StudentConverter;
import com.nbl.SearchSpecification.model.Student;
import com.nbl.SearchSpecification.dto.RequestDto;
import com.nbl.SearchSpecification.dto.StudentDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    StudentConverter converter;

    @Autowired
    private FiltersSpecification<Student> studentFiltersSpecification;

    private ModelMapper modelMapper;

    public Student getStdByName(String name) {

        return studentRepository.findByName(name);
    }

    public List<Student> getStdByCity(String city) {

        return studentRepository.findByAddressCity(city);
    }

    public List<Student> getStdBySubject(String subject) {

        return studentRepository.findBySubjectsName(subject);
    }

    public StudentDto createStudent(Student student){
        if (student != null){
             studentRepository.save(student);
        }
        return converter.entityToDtos(student);
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



    private StudentDto convertEntityToDto(Student student){
        modelMapper.getConfiguration()//per matchare alche 'latitude' e 'longitude'
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        StudentDto studentDto = new StudentDto();
        studentDto = modelMapper.map(student, StudentDto.class);
        return studentDto;
    }

    private Student convertDtoToEntity(StudentDto studentDto){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Student student = new Student();
        student = modelMapper.map(studentDto, Student.class);
        return student;
    }

    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        if (!students.isEmpty())
            return  converter.entityToDtos(students);
        return null;
    }

    public String deleteById(Long id) {
        if (studentRepository.existsById(id)){
            studentRepository.deleteAllById(Collections.singleton(id));
            return "Student Deleted Successfully.";
        }
        return  "id Student non esistente";
    }

    public String deleteAll() {
        if (studentRepository.findAll().isEmpty()){
            return "Lista è vuota!";
        }  else
            studentRepository.deleteAll();
        return "lista svuotata";
    }

    public StudentDto getById(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null){
            return converter.entityToDtos(student);
        }
        return null;
    }

    public List<StudentDto> findBy(Specification<Student> searchSpecification) {
        List<Student> students = studentRepository.findAll();
        if (!students.isEmpty())
            return  converter.entityToDtos(students);
        return null;
    }

    public List<Student> getStudents(RequestDto requestDto) {
        Specification<Student> searchSpecification = studentFiltersSpecification
                .getSearchSpecification(requestDto.getSearchRequestDto(), requestDto.getGlobalOperator());
        return studentRepository.findAll(searchSpecification);
    }

    public Page<Student> getStudentPage(RequestDto requestDto) {
        Specification<Student> searchSpecification = studentFiltersSpecification
                .getSearchSpecification(requestDto.getSearchRequestDto(), requestDto.getGlobalOperator());

        Pageable pageable = new PageRequestDto().getPageable(requestDto.getPageDto());
        return studentRepository.findAll(searchSpecification, pageable);
    }
}
