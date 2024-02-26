package com.example.demo.models;

import java.util.List;
import com.example.demo.models.Student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByAge(int age);
    List<Student> findByName(String name);
    List<Student> findByNameAndPassword(String name, String password);
    List<Student> findByGpa(int gpa);
}

