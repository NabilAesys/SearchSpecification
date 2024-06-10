package com.nbl.SearchSpecification.controller;

import com.nbl.SearchSpecification.dto.SubjectDto;
import com.nbl.SearchSpecification.model.Subject;
import com.nbl.SearchSpecification.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubjectController {
    @Autowired
   private SubjectService subjectService;

    @PostMapping("/createSubject")
    public ResponseEntity<SubjectDto> createSubject(@RequestBody Subject subject){
        return ResponseEntity.ok(subjectService.createSubject(subject));
    }

    @DeleteMapping(value="/subject/{id}")
    public String deleteSubject(@PathVariable Long id ){
        return subjectService.deleteById(id);
    }

    @DeleteMapping(value="/subjects")
    public String deleteSubjects(){
        return subjectService.deleteAll();
    }
}
