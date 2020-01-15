package com.ivelinnikolov.ProjectSAP.services;

import com.ivelinnikolov.ProjectSAP.exceptions.NoSuchUserException;
import com.ivelinnikolov.ProjectSAP.models.User;
import com.ivelinnikolov.ProjectSAP.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    public List<User> listAll()
    {
        return userRepository.findAll();
    }

    public ResponseEntity<?> logIn(User user, HttpSession session)
    {
        User validateUser = userRepository.findByUsername(user.getUsername());

        if (validateUser == null || !user.getPass().equals(validateUser.getPass()))
        {
            return ResponseEntity.status(401).body("The username or password is not correct");
        }

        session.setAttribute("userId", validateUser.getUserId());
        session.setAttribute("username", validateUser.getUsername());
        session.setAttribute("userRole", validateUser.getAccountType());

        return ResponseEntity.ok("User " + validateUser.getUsername() + " successfully logged in.");
    }


    public User createNewAccount(User user)
    {
        return userRepository.save(user);
    }

    public User getAccountById(int Id)
    {
        return userRepository.findById(Id).orElseGet(() -> {
            try
            {
                throw new NoSuchUserException();
            } catch (NoSuchUserException e)
            {
                e.getMessage();
                return null;
            }
        });
    }

}
