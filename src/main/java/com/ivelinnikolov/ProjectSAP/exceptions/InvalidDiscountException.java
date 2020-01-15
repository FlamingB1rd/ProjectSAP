package com.ivelinnikolov.ProjectSAP.exceptions;

public class InvalidDiscountException extends Exception
{
    @Override
    public String getMessage()
    {
        return "Invalid discount set.";
    }
}
