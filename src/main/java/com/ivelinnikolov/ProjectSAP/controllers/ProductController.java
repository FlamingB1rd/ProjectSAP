package com.ivelinnikolov.ProjectSAP.controllers;

import com.ivelinnikolov.ProjectSAP.models.Product;
import com.ivelinnikolov.ProjectSAP.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController
{
    @Autowired
    private ProductService productService;

    //-------------------------------------------- GET REQUESTS --------------------------------------------

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts()
    {
        return productService.listAll();
    }

    @GetMapping("/product-amount/{id}")
    @ResponseStatus(HttpStatus.OK)
    public int showProductAmount(@PathVariable(name = "id") int id)
    {
        return productService.listProductAmount(id);
    }

    @GetMapping("/list-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable(name = "id") int id)
    {
        return  productService.getProductById(id);
    }

    @GetMapping("/list-all-discounted-items")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllDiscountedProducts()
    {
        return productService.listAllDiscountedProducts();
    }

    //-------------------------------------------- PUT REQUESTS --------------------------------------------

    @PutMapping("/update-product-price-{id}/{newPrice}/{newMinPrice}")
    @ResponseStatus(HttpStatus.OK)
    public Product updatePricesById(@PathVariable(name = "id") int id, @PathVariable(name = "newPrice") double newPrice,
    @PathVariable(name = "newMinPrice") double newMinPrice)
    {
        return productService.updatePricesByID(id, newPrice, newMinPrice);
    }

    @PutMapping("/update-product-{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product updateProductById(@PathVariable(name = "id") int id, @Valid @RequestBody Product updatedProduct)
    {
        return productService.updateProductById(id, updatedProduct);
    }

    //-------------------------------------------- POST REQUESTS --------------------------------------------

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@Valid @RequestBody Product product)
    {
        return productService.createNewProduct(product);
    }

    //-------------------------------------------- DELETE REQUESTS --------------------------------------------

    @DeleteMapping("/remove-product-by-id-{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductByID(@PathVariable(value = "id") Integer accID)
    {
        productService.deleteByID(accID);
    }
}
