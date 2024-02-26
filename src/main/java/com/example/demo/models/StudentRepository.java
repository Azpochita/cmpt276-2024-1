package com.example.demo.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<User, Integer>{
    List<User> findByAge(int age);
    List<User> findByName(String name);
    List<User> findByNameAndPassword(String name, String password);
}

