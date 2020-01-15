package com.ivelinnikolov.ProjectSAP.models;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "orderdetails")
public class OrderDetails
{
    private int orderId;
    private int clientId;
    private int productId;
    private String dateOfOrder;
    private int amount;
    private double price;
    private double finalPrice;

    @Id
    @Column(name = "order_id", nullable = false)
    public int getOrderId()
    {
        return orderId;
    }

    public void setOrderId(int orderId)
    {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "client_id")
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Basic
    @Column(name = "product_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "date_of_order", nullable = false, length = 45)
    public String getDateOfOrder()
    {
        return dateOfOrder;
    }

    public void setDateOfOrder(String dateOfOrder)
    {
        this.dateOfOrder = dateOfOrder;
    }

    @Basic
    @Column(name = "amount", nullable = false)
    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    @Basic
    @Column(name = "final_price", nullable = false, precision = 0)
    public double getFinalPrice()
    {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice)
    {
        this.finalPrice = finalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetails that = (OrderDetails) o;
        return orderId == that.orderId &&
                amount == that.amount &&
                Double.compare(that.price, price) == 0 &&
                Double.compare(that.finalPrice, finalPrice) == 0 &&
                Objects.equals(dateOfOrder, that.dateOfOrder);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(orderId, dateOfOrder, amount, price, finalPrice);
    }

}
