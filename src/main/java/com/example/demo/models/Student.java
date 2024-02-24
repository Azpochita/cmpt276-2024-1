package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int weight;
    private int height;
    private String hairColor;
    private double gpa;
    
    // Constructors, getters, setters
}