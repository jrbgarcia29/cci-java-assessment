package com.cci.assessment.controller;

import com.cci.assessment.exception.RecordNotFoundException;
import com.cci.assessment.model.User;
import com.cci.assessment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public  ResponseEntity<User> getUser(@PathVariable("id") Long id) throws RecordNotFoundException {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        user = userService.createUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) throws RecordNotFoundException {
        userService.updateUser(id, user);
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }
}
