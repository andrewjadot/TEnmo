package com.techelevator.DAOTests;

import com.techelevator.tenmo.dao.JDBCAccountsDAO;
import com.techelevator.tenmo.model.Accounts;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class JDBCAccountsDAOTest extends TEnmoDAOTests {
    private JDBCAccountsDAO sut;
    private Accounts testAccounts;

    @Before
    public void setup(){
        sut = new JDBCAccountsDAO(dataSource);
        testAccounts = new Accounts();
        testAccounts.setAccountId(1);
        testAccounts.setUserId(1);
        testAccounts.setBalance(BigDecimal.valueOf(1000));
    }
    @Test
    public void getBalance_by_user_id_correct_balance(){
        BigDecimal balance = sut.getBalance(1);
        Assert.assertEquals(1000, balance);
    }

}
