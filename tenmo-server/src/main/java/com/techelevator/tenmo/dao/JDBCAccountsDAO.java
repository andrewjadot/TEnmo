package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;

@Component
public class JDBCAccountsDAO implements AccountsDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //might want to add try catch blocks in the future to make sure information is correct when going through


    public JDBCAccountsDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //get the balance of an account where the user_id matches
    @Override
    public BigDecimal getBalance(int userId) {
        String sql = "SELECT balance FROM accounts WHERE user_id = ?;";
        SqlRowSet results = null;
        BigDecimal balance = null;
        results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()) {
            balance = results.getBigDecimal("balance");
        }
        return balance;
    }

    //update the balance of an account through adding money
    @Override
    public BigDecimal addToBalance(BigDecimal amountToAdd, int id) {
        Accounts accounts = findUserId(id);
        BigDecimal newBalance = accounts.getBalance().add(amountToAdd);
        String sql = "UPDATE accounts SET balance = ? WHERE user_id = ?";
        int rowCount = jdbcTemplate.update(sql, newBalance, id);
        return accounts.getBalance();
    }


    //update the balance of an account through subtracting money
    @Override
    public BigDecimal subtractFromBalance(BigDecimal amountToSubtract, int id) {
        Accounts accounts = findUserId(id);
        BigDecimal newBalance = accounts.getBalance().subtract(amountToSubtract);
        String sql = "UPDATE accounts SET balance = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, newBalance, id);
        return accounts.getBalance();
    }


    //find the accounts user
    @Override
    public Accounts findUserId(int userId) {
        Accounts accounts = null;
        SqlRowSet results = null;
        String sql = "SELECT * FROM accounts WHERE user_id = ?";
        results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()){
            accounts = mapRowToAccount(results);
        }
        return accounts;
    }

    //find a specific account
    @Override
    public Accounts findAccountById(int id) {
        Accounts accounts = null;

        String sql = "SELECT * FROM accounts WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()){
             accounts = mapRowToAccount(results);
        }
        return accounts;
    }

    //Helper method in producing a new account when finding by ids
    private Accounts mapRowToAccount(SqlRowSet results){
        Accounts accounts = new Accounts();
        accounts.setBalance(results.getBigDecimal("balance"));
        accounts.setAccountId(results.getInt("account_id"));
        accounts.setUserId(results.getInt("user_id"));
        return accounts;
    }
}


