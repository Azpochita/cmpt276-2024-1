package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

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
    private StudentRepository userRepo;

    @GetMapping("/users/view")
    public String getAllUsers(Model model) {
        System.out.println("Hello from all users");
        List<Student> users = userRepo.findAll(); // db
        model.addAttribute("us", users);
        return "users/showAll";
    }
    
    @PostMapping("/student/add")
    public String addUser(@RequestParam Map<String, String> newuser, HttpServletResponse response) {
        System.out.println("ADD user");
        String newName = newuser.get("name");
        String newPwd = newuser.get("password");
        int newSize = Integer.parseInt(newuser.get("size"));
        Student s = new Student(newName, newPwd, newSize, 10.5);
        System.out.println("gpa: " + s.getGpa());
        userRepo.save(new Student(s));
        response.setStatus(201);
        return "users/addedUser";
    }
    // @GetMapping("/login")
    // public String getLogin(Model model, HttpServletRequest request, HttpSession session) {
    //     return new String();
    // }
    
}
