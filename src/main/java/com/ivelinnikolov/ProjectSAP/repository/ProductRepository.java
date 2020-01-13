package com.ivelinnikolov.ProjectSAP.repository;

import com.ivelinnikolov.ProjectSAP.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>
{
}
