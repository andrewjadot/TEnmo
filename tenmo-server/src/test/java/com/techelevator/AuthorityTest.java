package com.techelevator;

import com.techelevator.tenmo.model.Authority;
import org.junit.Assert;
import org.junit.Test;

public class AuthorityTest {
    Authority authority = new Authority("Tester");

    @Test
    public void authority_is_tester(){
        authority.setName("Tester");
        Assert.assertEquals("Tester", authority.getName());
    }

    @Test
    public void authority_to_a_string(){
        Assert.assertEquals("Authority{name=Tester}", authority.toString());
    }
}
