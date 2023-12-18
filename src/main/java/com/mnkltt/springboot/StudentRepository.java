package com.mnkltt.springboot;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

    List<Student> findByName(String name);
    List<Student> findByNameContaining(String name);

}
