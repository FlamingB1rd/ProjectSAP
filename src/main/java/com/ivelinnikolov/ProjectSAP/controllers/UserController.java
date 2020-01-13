package com.ivelinnikolov.ProjectSAP.controllers;

import com.ivelinnikolov.ProjectSAP.models.User;
import com.ivelinnikolov.ProjectSAP.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    private UserService userService;

    //-------------------------------------------- GET REQUESTS --------------------------------------------

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<User>> getAllAccounts()
    {
        return ResponseEntity.ok(userService.listAll());
    }

    @GetMapping("/individual/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getAccount(@PathVariable(name = "id") int id)
    {
        return  userService.getAccountById(id);
    }

    //-------------------------------------------- POST REQUESTS --------------------------------------------

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public User createAccount(@Valid @RequestBody User user)
    {
        return userService.createNewAccount(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> logIn(User user)
    {
        User userFromDb = userService.logIn(user);

        if (userFromDb == null)
        {
            return ResponseEntity.status(401).body("The username or password is not correct");
        }

        return ResponseEntity.ok(userFromDb);
    }
}
