package com.example.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

// UserController.java
@RestController
@RequestMapping("/users")
@Tag(name = "users", description = "Manage users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessagePublisher messagePublisher;
    
    
	/*
	 * @GetMapping("/get") public String getHello() { return "Hello"; }
	 */
    @Operation(summary = "Create or update a user", description = "Creates a new user or updates an existing user in the system.")
    @PostMapping("/save")
    public User save(@RequestBody User user) {
       messagePublisher.publish("User created: " + user.getId());
        return userService.updateUser(user);
    }

    @Operation(summary = "Get a user by ID", description = "Retrieves a user from the system based on the provided ID.")
    @GetMapping("/{id}")
    public User findById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @Operation(summary = "Delete a user by ID", description = "Deletes a user from the system based on the provided ID.")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        messagePublisher.publish("User deleted: "+ id);
        userService.deleteUser(id);
    }
}
            