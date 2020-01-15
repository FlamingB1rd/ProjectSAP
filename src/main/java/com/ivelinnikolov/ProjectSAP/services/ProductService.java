package com.ivelinnikolov.ProjectSAP.services;

import com.ivelinnikolov.ProjectSAP.exceptions.*;
import com.ivelinnikolov.ProjectSAP.models.Product;
import com.ivelinnikolov.ProjectSAP.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService
{

    @Autowired
    private ProductRepository productRepository;

    public List<Product> listAll()
    {
        //List<Product> valid = new ArrayList<>();
        //List<Product> all = productRepository.findAll();
        //for(Product single : all)
        //{
        //  if (single.getQuantity() != 0) valid.add(single)
        //}
        // return valid;  //This way we'll return all products that are still available.
        return productRepository.findAll();
    }

    public List<Product> listAllDiscountedProducts()
    {
        List<Product> valid = new ArrayList<>();
        List<Product> all = productRepository.findAll();

        for(Product single : all)
        {
          if (single.getDiscount() != 0)
              valid.add(single);
        }
        return valid;
    }

    public int listProductAmount(int id)
    {
        Product product = getProductById(id);
        return product.getQuantity();
    }

    public Product createNewProduct(Product product)
    {
        return productRepository.save(product);
    }

    public Product updatePricesByID(int id, double price, double minPrice)
    {
        Product product = getProductById(id);
        product.setProductId(id);
        try
        {
            product.setPrice(price);
        } catch (InvalidPriceException e)
        {
            e.getMessage();
        }
        try
        {
            product.setMinimalPrice(minPrice);
        } catch (InvalidPriceException e)
        {
            e.getMessage();
        }
        return productRepository.save(product);
    }

    public Product updateQuantityByID(int id, int newQuantity)
    {
        Product product = getProductById(id);
        product.setProductId(id);
        try
        {
            product.setQuantity(newQuantity);
        } catch (InvalidQuantityException e)
        {
            e.getMessage();
        }
        return productRepository.save(product);
    }

    public Product updateProductById(int id, Product updatedProduct)
    {
        Product product = getProductById(id);
        product.setProductId(id);
        product.setName(updatedProduct.getName());
        try
        {
            product.setQuantity(updatedProduct.getQuantity());
        } catch (InvalidQuantityException e)
        {
            e.getMessage();
        }
        try
        {
            product.setPrice(updatedProduct.getPrice());
        } catch (InvalidPriceException e)
        {
            e.getMessage();
        }
        try
        {
            product.setDiscount(updatedProduct.getDiscount());
        } catch (InvalidDiscountException e)
        {
            e.getMessage();
        }
        try
        {
            product.setMinimalPrice(updatedProduct.getMinimalPrice());
        } catch (InvalidPriceException e)
        {
            e.getMessage();
        }
        return productRepository.save(product);
    }

    public Product getProductById(int id)
    {
        return productRepository.findById(id).orElseGet(() -> {
            try
            {
                throw new NoSuchProductException();
            } catch (NoSuchProductException e)
            {
                e.getMessage();
                return null;
            }
        });
    }


    public void deleteByID(int id)
    {
        Product product = getProductById(id);
        if(product == null)
        {
            //todo: throw exception
            System.out.println("Account not found!");
            return;
        }
        productRepository.delete(product);
    }
}
