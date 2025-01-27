package com.example.demo.controllers;

// import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.models.Student;
import com.example.demo.models.StudentRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;

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
        System.out.println("Hello from all users");
        List<Student> students = studentRepo.findAll(); // db
        model.addAttribute("us", students);
        return "students/showAll";
    }
    
    @PostMapping("/students/add")
    public String addStudent(@RequestParam Map<String, String> newstudent, HttpServletResponse response) {
        System.out.println("ADD user");
        String newName = newstudent.get("name");
        int newWeight = Integer.parseInt(newstudent.get("Weight"));
        int newHeight = Integer.parseInt(newstudent.get("Height"));
        String newhairColor = newstudent.get("hairColor");
        double newGpa = Double.parseDouble(newstudent.get("Gpa"));
        studentRepo.save(new Student(newName, newWeight, newHeight, newhairColor, newGpa));
        response.setStatus(201);
        return "students/addedUser";
    }
    
}
