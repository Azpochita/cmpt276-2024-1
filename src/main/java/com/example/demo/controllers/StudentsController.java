package com.example.demo.controllers;

// import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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

    @GetMapping("/student/view")
    public String getAllStudents(Model model) {
        System.out.println("Hello from all users");
        List<Student> students = studentRepo.findAll(); // db
        model.addAttribute("us", students);
        return "students/showAll";
    }
    
    @PostMapping("/student/add")
    public String addStudent(@RequestParam Map<String, String> newstudent, HttpServletResponse response) {
        System.out.println("ADD user");
        String newName = newstudent.get("name");
        String newhairColor = newstudent.get("password");
        int newGpa = Integer.parseInteger(newstudent.get("size"));
        
        studentRepo.save(new Student(newName, newhairColor, newGpa));
        response.setStatus(201);
        return "students/addedUser";
    }
    // @GetMapping("/login")
    // public String getLogin(Model model, HttpServletRequest request, HttpSession session) {
    //     return new String();
    // }
    
}
