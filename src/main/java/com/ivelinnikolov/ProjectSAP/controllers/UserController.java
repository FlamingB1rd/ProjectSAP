package com.ivelinnikolov.ProjectSAP.controllers;

import com.ivelinnikolov.ProjectSAP.POJOModels.LoginForm;
import com.ivelinnikolov.ProjectSAP.models.User;
import com.ivelinnikolov.ProjectSAP.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    public ResponseEntity<?> getAllAccounts(HttpSession session)
    {
        if (session.getAttribute("userId") == null)
        {
            return ResponseEntity.status(401).body("Unauthorized!");
        }
        else if (!session.getAttribute("userRole").equals(2))
        {
            return ResponseEntity.status(403).body("Request unavailable for this account.");
        }

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
    public ResponseEntity<?> createAccount(@Valid @RequestBody User user)
    {
        return ResponseEntity.ok(userService.createNewAccount(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody LoginForm loginForm, HttpSession session)
    {


        User userValidate = new User();
        userValidate.setUsername(loginForm.getUsername());
        userValidate.setPass(loginForm.getPass());
        return userService.logIn(userValidate, session);
    }
}
