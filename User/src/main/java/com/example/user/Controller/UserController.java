package com.example.user.Controller;


import com.example.user.Model.Auser;
import com.example.user.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Operation(summary = "Get all users")
    @GetMapping
    public List<Auser> getAllUsers() {
        return userRepository.findAll();
    }
    @Operation(summary = "Get user by ID")
    @GetMapping("/{id}")
    public Auser getUserById(@PathVariable Long id){
        return userRepository.findById(id).orElse(null);
    }

    @Operation(summary = "Create a new user")
    @PostMapping
    public Auser createUser(@RequestBody Auser auser){
        return userRepository.save(auser);
    }

    @Operation(summary = "Update a user")
    @PutMapping("/{id}")
    public Auser updateUser(@PathVariable Long id, Auser userDetails){
        Auser user = userRepository.findById(id).orElseThrow(() -> new ErrorResponseException(HttpStatus.NOT_FOUND));
        user.setUserName(userDetails.getUserName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        return userRepository.save(user);
    }

    @Operation(summary = "Delete user by ID")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
