package com.ivelinnikolov.ProjectSAP.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product
{
    private int productId;
    private String name;
    private int quantity;
    private double price;
    private int discount;
    private double minimalPrice;

    @Id
    @Column(name = "product_id", nullable = false)
    public int getProductId()
    {
        return productId;
    }

    public void setProductId(int productId)
    {
        this.productId = productId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Basic
    @Column(name = "quantity", nullable = false)
    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
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
    @Column(name = "discount", nullable = false)
    public int getDiscount()
    {
        return discount;
    }

    public void setDiscount(int discount)
    {
        this.discount = discount;
    }

    @Basic
    @Column(name = "minimal_price", nullable = false, precision = 0)
    public double getMinimalPrice()
    {
        return minimalPrice;
    }

    public void setMinimalPrice(double minimalPrice)
    {
        this.minimalPrice = minimalPrice;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product that = (Product) o;
        return productId == that.productId &&
                quantity == that.quantity &&
                Double.compare(that.price, price) == 0 &&
                discount == that.discount &&
                Double.compare(that.minimalPrice, minimalPrice) == 0 &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(productId, name, quantity, price, discount, minimalPrice);
    }
}
