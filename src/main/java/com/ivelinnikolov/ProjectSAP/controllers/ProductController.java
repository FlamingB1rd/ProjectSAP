package com.ivelinnikolov.ProjectSAP.controllers;

import com.ivelinnikolov.ProjectSAP.models.Product;
import com.ivelinnikolov.ProjectSAP.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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

    @GetMapping("/amount/{id}")
    @ResponseStatus(HttpStatus.OK)
    public int showProductAmount(@PathVariable(name = "id") int id)
    {
        return productService.listProductAmount(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable(name = "id") int id)
    {
        return  productService.getProductById(id);
    }

    @GetMapping("/discount")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllDiscountedProducts()
    {
        return productService.listAllDiscountedProducts();
    }

    //-------------------------------------------- PUT REQUESTS --------------------------------------------

    @PutMapping("/update-{id}/{newPrice}/{newMinPrice}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updatePricesById(@PathVariable(name = "id") int id, @PathVariable(name = "newPrice") double newPrice,
    @PathVariable(name = "newMinPrice") double newMinPrice, HttpSession session)
    {
        if (session.getAttribute("userId") == null)
        {
            return ResponseEntity.status(401).body("Unauthorized!");
        }
        else if (!session.getAttribute("userRole").equals(2)) // 2 - employee
        {
            return ResponseEntity.status(403).body("Request unavailable for this account.");
        }

        return ResponseEntity.ok(productService.updatePricesByID(id, newPrice, newMinPrice));
    }

    @PutMapping("/update-{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateProductById(@PathVariable(name = "id") int id, @Valid @RequestBody Product updatedProduct, HttpSession session)
    {
        if (session.getAttribute("userId") == null)
        {
            return ResponseEntity.status(401).body("Unauthorized!");
        }
        else if (!session.getAttribute("userRole").equals(2)) // 2 - employee
        {
            return ResponseEntity.status(403).body("Request unavailable for this account.");
        }

        return ResponseEntity.ok(productService.updateProductById(id, updatedProduct));
    }

    //-------------------------------------------- POST REQUESTS --------------------------------------------

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product, HttpSession session)
    {
        if (session.getAttribute("userId") == null)
        {
            return ResponseEntity.status(401).body("Unauthorized!");
        }
        else if (!session.getAttribute("userRole").equals(2)) // 2 - employee
        {
            return ResponseEntity.status(403).body("Request unavailable for this account.");
        }

        return ResponseEntity.ok(productService.createNewProduct(product));
    }

    //-------------------------------------------- DELETE REQUESTS --------------------------------------------

    @DeleteMapping("/remove-{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteProductByID(@PathVariable(value = "id") Integer accID, HttpSession session)
    {
        if (session.getAttribute("userId") == null)
        {
            return ResponseEntity.status(401).body("Unauthorized!");
        }
        else if (!session.getAttribute("userRole").equals(2)) // 2 - employee
        {
            return ResponseEntity.status(403).body("Request unavailable for this account.");
        }

        productService.deleteByID(accID);
        return ResponseEntity.noContent().build();
    }
}
