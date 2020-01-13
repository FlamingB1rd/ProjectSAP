package com.ivelinnikolov.ProjectSAP.repository;

import com.ivelinnikolov.ProjectSAP.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
    User findByUsername(String username);
}
