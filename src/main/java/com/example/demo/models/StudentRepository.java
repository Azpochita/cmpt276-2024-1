package com.example.demo.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Students, Integer>{
    List<Students> findByName(String name);
    List<Students> findByWeight(String weight);
    List<Students> findByHeight(String height);
    List<Students> findByhairColor(String hairColor);
    List<Students> findBygpa(double gpa);
}