package com.ivelinnikolov.ProjectSAP.exceptions;

public class InvalidQuantityException extends Exception
{
    @Override
    public String getMessage()
    {
        return "Invalid quantity set.";
    }
}
