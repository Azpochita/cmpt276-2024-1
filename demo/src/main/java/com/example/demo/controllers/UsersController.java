package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.models.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import scala.collection.concurrent.Map;

import com.example.demo.models.User;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class UsersController {
    
    @Autowired
    private UserRepository usersRepository;

    @GetMapping("/users/view")
    public String getAllUsers(Model model) {
        System.out.println("Hello from all users");
        List<User> users = usersRepository.findAll(); // db
        model.addAttribute("us", users);
        return "users/showAll";
    }
    
    // @PostMapping("/users/add")
    // public String addUser(@RequestParam Map<String, String> newuser, HttpServletResponse response) {
    //     System.out.println("ADD user");
    //     String newName = newuser.get("name");
    //     String newPwd = newuser.get("password");
    //     int newSize = Integer.parseInt(newuser.get("size"));
    //     userRepo.save(new User(newName,newPwd,newSize));
    //     response.setStatus(sc:201);
    //     return "users/addedUser";
    // }
    @GetMapping("/login")
    public String getLogin(Model model, HttpServletRequest request, HttpSession session) {
        return new String();
    }
    
}
