package com.ivelinnikolov.ProjectSAP.models;

import com.ivelinnikolov.ProjectSAP.exceptions.InvalidAccountTypeException;
import com.ivelinnikolov.ProjectSAP.exceptions.InvalidEmailException;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "acc")
public class User
{
    private int userId;
    private String fname;
    private String lname;
    private String email;
    private String address;
    private String username;
    private String pass;
    private int accountType;

    private static final String PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

    @Id
    @Column(name = "user_id", nullable = false)
    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    @Basic
    @Column(name = "fname", nullable = false, length = 45)
    public String getFname()
    {
        return fname;
    }

    public void setFname(String fname)
    {
        this.fname = fname;
    }

    @Basic
    @Column(name = "lname", nullable = false, length = 45)
    public String getLname()
    {
        return lname;
    }

    public void setLname(String lname)
    {
        this.lname = lname;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 45)
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email) throws InvalidEmailException
    {
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches())
        {
            throw new InvalidEmailException();
        }
        this.email = email;
    }

    @Basic
    @Column(name = "address", nullable = false, length = 45)
    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 45)
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    @Basic
    @Column(name = "pass", nullable = false, length = 45)
    public String getPass()
    {
        return pass;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
    }

    @Basic
    @Column(name = "account_type", nullable = false)
    public int getAccountType()
    {
        return accountType;
    }

    public void setAccountType(int accountType) throws InvalidAccountTypeException
    {
        if (accountType == 1 || accountType == 2)
        {
            this.accountType = accountType;
        }
        else throw new InvalidAccountTypeException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                accountType == user.accountType &&
                Objects.equals(fname, user.fname) &&
                Objects.equals(lname, user.lname) &&
                Objects.equals(email, user.email) &&
                Objects.equals(address, user.address) &&
                Objects.equals(username, user.username) &&
                Objects.equals(pass, user.pass);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(userId, fname, lname, email, address, username, pass, accountType);
    }
}
