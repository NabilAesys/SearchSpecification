package com.nbl.SearchSpecification.repository;


import com.nbl.SearchSpecification.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

    //select * from student where name = 'pratik'
    Student findByName(String name);


    List<Student> findByAddressCity(String city);

    List<Student> findBySubjectsName(String subName);
}
