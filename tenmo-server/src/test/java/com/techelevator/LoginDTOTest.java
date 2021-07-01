package com.techelevator;

import com.techelevator.tenmo.model.LoginDTO;
import org.junit.Assert;
import org.junit.Test;

public class LoginDTOTest {
    LoginDTO login = new LoginDTO();

    @Test
    public void username_is_username(){
        login.setUsername("username");
        Assert.assertEquals("username", login.getUsername());
    }

    @Test
    public void password_is_topsecret(){
        login.setPassword("topsecret");
        Assert.assertEquals("topsecret", login.getPassword());
    }
}
