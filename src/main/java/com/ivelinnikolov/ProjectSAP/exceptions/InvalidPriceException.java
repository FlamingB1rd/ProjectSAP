package com.ivelinnikolov.ProjectSAP.exceptions;

public class InvalidPriceException extends Exception
{
    @Override
    public String getMessage()
    {
        return "Invalid sum entered!";
    }
}
