package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    private String name;
    private String hairColor;
    private double gpa;

    public Student(String name, String hairColor, double gpa) {
        this.name = name;
        this.hairColor = hairColor;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String gethairColor() {
        return hairColor;
    }

    public void sethairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public int getGpa() {
        return gpa;
    }

    public void setGpa(int gpa) {
        this.gpa = gpa;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
    
}
