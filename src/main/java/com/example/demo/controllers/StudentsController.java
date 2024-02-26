package com.example.demo.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.models.StudentRepository;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;
import java.lang.*;
import java.sql.ResultSet;

import com.example.demo.models.Student;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class StudentsController {
    
    @Autowired
    private StudentRepository studentRepo;

    @GetMapping("/students/view")
    public String getAllStudents(Model model) {
        System.out.println("Hello from all Student");
        List<Student> students = studentRepo.findAll(); // db
        model.addAttribute("us", students);
        return "students/showAll";
    }

    @PostMapping("/students/add")
    public String addStudent(@RequestParam Map<String, String> newstudent, HttpServletResponse response) {
        System.out.println("ADD student");
        String newName = newstudent.get("name");
        String newWeight = newstudent.get("weight");
        String newHeight = newstudent.get("height");
        String newhairColor = newstudent.get("hairColor");
        double newSize = Double.parseDouble(newstudent.get("size"));

        if (newName.length() == 0) {
            System.out.println("Error studentname contained no characters");
            throw new Error("Error: name is undefined");
        }
        System.out.println("Error studentname contained no characters");

        studentRepo.save(new Student("name", "weight", "height", "hairColor", 0));
        response.setStatus(201);
        
        return "students/addForm";
    }

    @PostMapping("/students/edit/{id}")
    public String editStudent(@PathVariable Double id, @ModelAttribute Student updatedStudent) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student id: " + id));

        // Update attributes
        student.setName(updatedStudent.getName());
        student.setWeight(updatedStudent.getWeight());
        student.setHeight(updatedStudent.getHeight());
        student.sethairColor(updatedStudent.gethairColor());
        student.setGpa(updatedStudent.getGpa());

        // Save the updated student to the database
        studentRepo.save(student);
            
        // Redirect to the showAll page after successful update
        return "redirect:/students/showAll";
    }

    @GetMapping("/students/delete")
    public String deleteStudent(@PathVariable Double id) {
        studentRepo.deleteById(id);
        return "redirect:/students/showAll";
    }

}