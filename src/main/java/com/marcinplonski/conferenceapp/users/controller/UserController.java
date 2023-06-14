package com.marcinplonski.conferenceapp.users.controller;

import com.marcinplonski.conferenceapp.users.model.User;
import com.marcinplonski.conferenceapp.users.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }



    @PostMapping
    public User addUser(@RequestBody @Valid User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id );
    }

    @PatchMapping("/{id}")
    public User patchUser(@PathVariable Long id, @RequestBody User user) {
        return userService.patchUser(id, user);
    }
}
