package com.example.demo.controllers;

// import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.models.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;

import com.example.demo.models.User;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class UsersController {
    
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/users/view")
    public String getAllUsers(Model model) {
        System.out.println("Hello from all users");
        List<User> users = userRepo.findAll(); // db
        model.addAttribute("us", users);
        return "users/showAll";
    }
    
    @PostMapping("/users/add")
    public String addUser(@RequestParam Map<String, String> newuser, HttpServletResponse response) {
        System.out.println("ADD user");
        String newName = newuser.get("name");
        String newPwd = newuser.get("password");
        int newSize = Integer.parseInt(newuser.get("size"));

        if (newName.length() == 0) {
            System.out.println("Error username contained no characters");
            throw Error("Error: name is undefined");
        }

        userRepo.save(new User(newName,newPwd,newSize));
        response.setStatus(201);
        return "users/addedUser";
    }

    @GetMapping("/login")
    public String getLogin(Model model, HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("session_user");
        if (user = null){
            return "users/login";
        }
        else {
            model.addAttribute("user", user);
            return "users/protected";
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam Map<String,String> formdata, Model model, HttpServletRequest request, HttpSession session){ 
        // processing login
        String name = formData.get("name");
        String pwd = formdata.get("password");
        List<User> userList = userRepo.findByNameAndPassword(name, pwd);
        if (userlist.isEmpty()){
            return "users/login";
        }
        else {
            //success
            User user = userlist.get(0);
            request.getSession().setAttribute("session_user", user);
            model.addAttribute("user", user);
            return "users/protected";
        }
    }

    @PatchMapping("/users/update")
    public String updateUser(@RequestParam Map<String, String> newuser, HttpServletResponse response) {
        System.out.println("UPDATE user");
        String newName = newuser.get("name");
        String newPwd = newuser.get("password");
        int newSize = Integer.parseInt(newuser.get("size"));

        if (newName.length() == 0) {
            System.out.println("Error: username contained no characters");
            throw new RuntimeException("Error: name is undefined");
        }

        // Assuming you have logic to find and update a specific user
        User user = userRepo.findByUsername(newName); // Adjust this based on your repository method
        if (user != null) {
            // Update user information
            user.setPassword(newPwd);
            user.setSize(newSize);

            // Save the updated user
            userRepo.save(user);

            response.setStatus(200);
            return "users/updatedUser";
        } else {
            // Handle the case where the user is not found
            System.out.println("Error: User not found");
            response.setStatus(404);
            return "users/userNotFound";
        }
    }

    @DeleteMapping("/users/delete")
    public String deleteUser(@RequestParam String username, HttpServletResponse response) {
        System.out.println("DELETE user");

        // Assuming you have logic to find and delete a specific user
        User user = userRepo.findByUsername(username); // Adjust this based on your repository method
        if (user != null) {
            // Delete the user from the database
            userRepo.delete(user);

            response.setStatus(200);
            return "users/deletedUser";
        } else {
            // Handle the case where the user is not found
            System.out.println("Error: User not found");
            response.setStatus(404);
            return "users/userNotFound";
        }
    }

}