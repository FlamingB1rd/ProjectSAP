package com.ivelinnikolov.ProjectSAP.exceptions;

public class InvalidEmailException extends Exception
{
    @Override
    public String getMessage()
    {
        return "Invalid email set.";
    }
}
