package com.example.demo.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.models.StudentRepository;

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
public class StudentController {
    
    @Autowired
    private StudentRepository studentRepo;

    @GetMapping("/students/view")
    public String getAllStudents(Model model) {
        System.out.println("Hello from all Student");
        List<Student> students = studentRepo.findAll(); // db
        model.addAttribute("us", students);
        return "students/showAll";
    }

    @GetMapping("/students/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/addForm";
    }

    @PostMapping("/students/add")
    public String addStudent(Map<String, String> newstudent, HttpServletResponse response) {
        System.out.println("ADD student");
        String newName = newstudent.get("name");
        String newPwd = newstudent.get("password");
        int newSize = Integer.parseInt(newstudent.get("size"));

        if (newName.length() == 0) {
            System.out.println("Error studentname contained no characters");
            throw new Error("Error: name is undefined");
        }

        studentRepo.save(new Student(newName,newPwd,newSize));
        response.setStatus(201);
        
        return "students/addForm";
    }

    @GetMapping("/students/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student id: " + id));

        model.addAttribute("student", student);
        return "students/editForm";
    }

    @PostMapping("/students/edit/{id}")
    public String editStudent(@PathVariable Long id, @ModelAttribute Student updatedStudent) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student id: " + id));

        // Update attributes
        student.setName(updatedStudent.getName());
        student.setWeight(updatedStudent.getWeight());
        student.setHeight(updatedStudent.getHeight());
        student.setHairColor(updatedStudent.getHairColor());
        student.setGpa(updatedStudent.getGpa());

        studentRepo.save(student);
        return "redirect:/students/showAll";
    }

    @GetMapping("/login")
    public String getLogin(Model model, HttpServletRequest request, HttpSession session) {
        Student student = (Student) session.getAttribute("session_student");
        if (student == null){
            return "students/login";
        }
        else {
            model.addAttribute("student", student);
            return "students/protected";
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam Map<String,String> formData, Model model, HttpServletRequest request, HttpSession session) {
        // processing login
        String name = formData.get("name");
        String pwd = formData.get("password");
        List<Student> studentList = studentRepo.findByNameAndPassword(name, pwd);
        if (studentList.isEmpty()){
            return "students/login";
        }
        else {
            //success
            Student student = studentList.get(0);
            request.getSession().setAttribute("session_student", student);
            model.addAttribute("student", student);
            return "students/protected";
        }
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentRepo.deleteById(id);
        return "redirect:/students/showAll";
    }

}