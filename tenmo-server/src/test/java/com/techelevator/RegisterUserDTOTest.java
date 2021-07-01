package com.techelevator;

import com.techelevator.tenmo.model.RegisterUserDTO;
import org.junit.Assert;
import org.junit.Test;

public class RegisterUserDTOTest {
    RegisterUserDTO user = new RegisterUserDTO();


    @Test
    public void username_is_username(){
        user.setUsername("username");
        Assert.assertEquals("username", user.getUsername());
    }

    @Test
    public void password_is_password(){
        user.setPassword("password");
        Assert.assertEquals("password", user.getPassword());
    }
}
