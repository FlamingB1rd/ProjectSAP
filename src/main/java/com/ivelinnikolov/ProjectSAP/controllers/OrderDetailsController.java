package com.ivelinnikolov.ProjectSAP.controllers;

import com.ivelinnikolov.ProjectSAP.POJOModels.BuyProduct;
import com.ivelinnikolov.ProjectSAP.models.OrderDetails;
import com.ivelinnikolov.ProjectSAP.services.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderDetailsController
{
    @Autowired
    private OrderDetailsService orderDetailsService;

    //-------------------------------------------- GET REQUESTS --------------------------------------------
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDetails> getAllProducts()
    {
        return orderDetailsService.listAll();
    }

    @GetMapping("/list/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDetails getProduct(@PathVariable(name = "id") int id)
    {
        return  orderDetailsService.getOrderById(id);
    }

    //-------------------------------------------- POST REQUESTS --------------------------------------------

    @PostMapping("/order")
    public ResponseEntity<?> buyProduct(@RequestBody BuyProduct order)
    {
        String orderValid = orderDetailsService.buyProduct(order.getClientId(), order.getProductId(), order.getQuantity(), order.getDate());
        //return ResponseEntity.status(201).body(orderValid);
        return ResponseEntity.ok(orderValid);
    }
}
