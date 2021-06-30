package com.techelevator;


import com.techelevator.tenmo.model.Accounts;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;
public class AccountsTest {

    Accounts accounts = new Accounts();

    @Test
    public void the_accountId_is_2(){
        accounts.setAccountId(2);
    }
}
