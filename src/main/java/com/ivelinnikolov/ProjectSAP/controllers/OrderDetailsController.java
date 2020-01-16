package com.ivelinnikolov.ProjectSAP.controllers;

import com.ivelinnikolov.ProjectSAP.POJOModels.BuyProduct;
import com.ivelinnikolov.ProjectSAP.POJOModels.TurnoverDateForm;
import com.ivelinnikolov.ProjectSAP.services.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/orders")
public class OrderDetailsController
{
    @Autowired
    private OrderDetailsService orderDetailsService;



    //-------------------------------------------- GET REQUESTS --------------------------------------------
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllOrders(HttpSession session)
    {
        if (session.getAttribute("userId") == null)
        {
            return ResponseEntity.status(401).body("Unauthorized!");
        }
        else if (!session.getAttribute("userRole").equals(2)) // 2 - employee
        {
            return ResponseEntity.status(403).body("Request unavailable for this account.");
        }

        return ResponseEntity.ok(orderDetailsService.listAll());
    }

    @GetMapping("/list/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getOrder(@PathVariable(name = "id") int id, HttpSession session)
    {
        if (session.getAttribute("userId") == null)
        {
            return ResponseEntity.status(401).body("Unauthorized!");
        }
        else if (!session.getAttribute("userRole").equals(2)) // 2 - employee
        {
            return ResponseEntity.status(403).body("Request unavailable for this account.");
        }

        return  ResponseEntity.ok(orderDetailsService.getOrderById(id));
    }

    //-------------------------------------------- POST REQUESTS --------------------------------------------

    @PostMapping("/order")
    public ResponseEntity<?> buyProduct(@RequestBody BuyProduct order, HttpSession session)
    {
        String orderValid = orderDetailsService.buyProduct(order.getClientId(), order.getProductId(), order.getQuantity(), order.getDate());

        return ResponseEntity.ok(orderValid);
    }

    @PostMapping("/turnover")
    public ResponseEntity<?> calculateTurnover(@RequestBody TurnoverDateForm dateForm, HttpSession session)
    {
        Date validStartDate = new Date();
        Date validEndDate = new Date();
        try
        {
            validStartDate = new SimpleDateFormat("dd.MM.yyyy").parse(dateForm.getStartDate());
            validEndDate = new SimpleDateFormat("dd.MM.yyyy").parse(dateForm.getEndDate());
        }
        catch (ParseException e)
        {
            e.getMessage();
        }

        return ResponseEntity.ok("The turnover for the specified time period is: " + orderDetailsService.turnover(validStartDate, validEndDate));
    }
}
