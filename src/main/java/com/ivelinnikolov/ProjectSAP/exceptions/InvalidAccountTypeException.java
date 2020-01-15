package com.ivelinnikolov.ProjectSAP.exceptions;

public class InvalidAccountTypeException extends Exception
{
    @Override
    public String getMessage()
    {
        return "Invalid account type set.";
    }
}
