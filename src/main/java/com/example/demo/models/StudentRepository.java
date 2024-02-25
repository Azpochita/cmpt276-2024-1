package com.example.demo.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer>{
    List<Student> findByName(String name);
    List<Student> findByWeight(String weight);
    List<Student> findByHeight(String height);
    List<Student> findByhairColor(String hairColor);
    List<Student> findBygpa(double gpa);
}

