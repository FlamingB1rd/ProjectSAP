package com.ivelinnikolov.ProjectSAP.exceptions;

public class NoSuchUserException extends Exception
{
    @Override
    public String getMessage()
    {
        return "No such user found!";
    }
}
