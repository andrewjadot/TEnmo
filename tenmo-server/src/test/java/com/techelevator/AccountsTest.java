package com.techelevator;


import com.techelevator.tenmo.model.Accounts;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;


public class AccountsTest {

    Accounts accounts = new Accounts();


    @Test
    public void the_accountId_is_2(){
        accounts.setAccountId(2);
        Assert.assertEquals(2, accounts.getAccountId());
    }

    @Test
    public void userId_is_1(){
        accounts.setUserId(1);
        Assert.assertEquals(1, accounts.getUserId());
    }

    @Test
    public void balance_is_100(){
        accounts.setBalance(BigDecimal.valueOf(100));
        Assert.assertEquals( BigDecimal.valueOf(100), accounts.getBalance());
    }
}
