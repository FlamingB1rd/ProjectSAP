package com.ivelinnikolov.ProjectSAP.exceptions;

public class NoSuchProductException extends Exception
{
    @Override
    public String getMessage()
    {
        return "No such product found!";
    }
}
