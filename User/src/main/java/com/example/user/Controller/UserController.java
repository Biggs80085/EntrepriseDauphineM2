package com.example.user.Controller;


import com.example.user.Model.Auser;
import org.springframework.http.ResponseEntity;
import com.example.user.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Operation(summary = "Get all users")
    @GetMapping
    public ResponseEntity<List<Auser>> getAllUsers() {
        List<Auser> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @Operation(summary = "Get user by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Auser> getUserById(@PathVariable Long id){
        Auser user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Create a new user")
    @PostMapping
    public ResponseEntity<Auser> createUser(@RequestBody Auser auser){
        Auser createdUser = userService.createUser(auser);
        return ResponseEntity.status(201).body(createdUser);
    }

    @Operation(summary = "Update a user")
    @PutMapping("/{id}")
    public ResponseEntity<Auser> updateUser(@PathVariable Long id, @RequestBody Auser userDetails){
        Auser updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary = "Delete user by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
