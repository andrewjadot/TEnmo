package com.techelevator;

import com.techelevator.tenmo.model.Authority;
import com.techelevator.tenmo.model.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class UserTest {
    User user = new User();

    @Test
    public void userId_is_7(){
        Long newId = 7L;
        user.setId(newId);
        Assert.assertEquals(newId, user.getId());
    }

    @Test
    public void username_is_username(){
        user.setUsername("username");
        Assert.assertEquals("username", user.getUsername());
    }

    @Test
    public void password_is_topsecret(){
        user.setPassword("topsecret");
        Assert.assertEquals("topsecret", user.getPassword());
    }

    @Test
    public void isActivated_is_true(){
        user.setActivated(true);
        Assert.assertTrue(user.isActivated());
    }

    @Test
    public void setAuthorities_is_authority(){
        Set<Authority> authority = new HashSet<>();
        Set<Authority> authorityTwo = new HashSet<>();

        user.setAuthorities(authority);
        Assert.assertEquals(authorityTwo, user.getAuthorities());
    }
}
