package com.nbl.SearchSpecification.controller;

import com.nbl.SearchSpecification.dto.StudentDto;
import com.nbl.SearchSpecification.model.Student;
import com.nbl.SearchSpecification.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/createStudent")
    public ResponseEntity<StudentDto> createStudent(@RequestBody Student student){
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    @GetMapping(value="/students")
    public List<StudentDto> getAllEmployee(){
        return studentService.getAllStudents();
    }

    @GetMapping(value="/student/{id}")
    public StudentDto getStudent(@PathVariable Long id ){
        return studentService.getById(id);
    }

    @DeleteMapping(value="/student/{id}")
    public String deleteStudent(@PathVariable Long id ){
        return studentService.deleteById(id);
    }

    @DeleteMapping(value="/student")
    public String deleteStudents(){
        return studentService.deleteAll();
    }
}
