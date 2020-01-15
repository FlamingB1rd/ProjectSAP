package com.ivelinnikolov.ProjectSAP.services;

import com.ivelinnikolov.ProjectSAP.exceptions.InvalidQuantityException;
import com.ivelinnikolov.ProjectSAP.exceptions.NoSuchOrderException;
import com.ivelinnikolov.ProjectSAP.models.OrderDetails;
import com.ivelinnikolov.ProjectSAP.models.Product;
import com.ivelinnikolov.ProjectSAP.models.User;
import com.ivelinnikolov.ProjectSAP.repository.OrderDetailsRepository;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;

@Service
public class OrderDetailsService
{
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    public List<OrderDetails> listAll()
    {
        return orderDetailsRepository.findAll();
    }

    public double turnover (Date startDate, Date endDate) {
        List<OrderDetails> orderDetails = listAll();
        List<OrderDetails> validOrders = new ArrayList<>();
        double turnoverPrice = 0.0;

        for (OrderDetails order : orderDetails) {
            try {
                Date orderDate = new SimpleDateFormat("dd.MM.yyyy").parse(order.getDateOfOrder());

                if (((orderDate.compareTo(startDate) > 0) || (orderDate.compareTo(startDate) == 0)) || ((orderDate.compareTo(endDate) < 0) || (orderDate.compareTo(endDate) == 0))) {
                    validOrders.add(order);
                }

            } catch (ParseException e) {
                e.getMessage();
            }
        }

        for (OrderDetails order : validOrders)
        {
            turnoverPrice += order.getFinalPrice();
        }

        return turnoverPrice;
    }

    public OrderDetails getOrderById(int id)
    {
        return orderDetailsRepository.findById(id).orElseGet(() -> {
            try
            {
                throw new NoSuchOrderException();
            } catch (NoSuchOrderException e)
            {
                return null;
            }
        });
    }

    public OrderDetails orderAdd(OrderDetails order)
    {
        return orderDetailsRepository.save(order);
    }

    public String buyProduct(int clientId, int productId, int quantity, String date)
    {
        OrderDetails orderDetails = new OrderDetails();
        User user = null;

        user = userService.getAccountById(clientId);

        Product product = productService.getProductById(productId);

        double orderPrice = quantity * product.getPrice();
        double discountedOrderPrice = quantity * (product.getPrice() - (product.getPrice() * product.getDiscount() / 100));

        if (quantity > product.getQuantity() || product.getQuantity() == 0)
        {
            return "There is not enough of this product.";
        }

        orderDetails.setClientId(clientId);
        orderDetails.setProductId(productId);
        orderDetails.setAmount(quantity);
        orderDetails.setPrice(orderPrice);
        orderDetails.setFinalPrice(discountedOrderPrice);
        orderDetails.setDateOfOrder(date);
        OrderDetails newOrder = orderAdd(orderDetails);

        try
        {
            product.setQuantity(product.getQuantity() - quantity);
        } catch (InvalidQuantityException e)
        {
            e.getMessage();
        }
        Product newProduct = productService.updateProductById(productId, product);

        return "Your order has been successful. It's on it's way to " + user.getAddress() + "!";
    }


}
