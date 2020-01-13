package com.ivelinnikolov.ProjectSAP.services;

import com.ivelinnikolov.ProjectSAP.models.User;
import com.ivelinnikolov.ProjectSAP.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public User logIn(User user)
    {
        User validateUser = userRepository.findByUsername(user.getUsername());

        if (validateUser == null || !user.getPass().equals(validateUser.getPass())) {
            return null; //todo: throw exception here
        }

        return user;
    }


    public User createNewAccount(User user)
    {
        return userRepository.save(user);
    }

    public User getAccountById(int Id)
    {
        return userRepository.findById(Id).orElseGet(() -> null); //todo: throw exception instead of null
    }

}
