package com.example.demo.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer>{
    List<Student> findByName(String name);
    List<Student> findByWeight(int weight);
    List<Student> findByHeight(int height);
    List<Student> findByHairColor(String hairColor);
    List<Student> findByGpa(double gpa);
}