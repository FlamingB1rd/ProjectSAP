package com.ivelinnikolov.ProjectSAP.services;

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
        product.setPrice(price);
        product.setMinimalPrice(minPrice);
        return productRepository.save(product);
    }

    public Product updateQuantityByID(int id, int newQuantity)
    {
        Product product = getProductById(id);
        product.setProductId(id);
        product.setQuantity(newQuantity);
        return productRepository.save(product);
    }

    public Product updateProductById(int id, Product updatedProduct)
    {
        Product product = getProductById(id);
        product.setProductId(id);
        product.setName(updatedProduct.getName());
        product.setQuantity(updatedProduct.getQuantity());
        product.setPrice(updatedProduct.getPrice());
        product.setDiscount(updatedProduct.getDiscount());
        product.setMinimalPrice(updatedProduct.getMinimalPrice());
        return productRepository.save(product);
    }

    public Product getProductById(int id)
    {
        return productRepository.findById(id).orElseGet(() -> null); //todo: throw exception instead of null
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
