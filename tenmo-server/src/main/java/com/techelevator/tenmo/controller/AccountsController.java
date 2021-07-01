package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountsDAO;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class AccountsController {
    private AccountsDAO accountsDAO;
    private UserDao userDao;


    public AccountsController(AccountsDAO accountsDAO, UserDao userDao){
        this.accountsDAO = accountsDAO;
        this.userDao = userDao;
    }

    @RequestMapping(path = "balance/{id}", method = RequestMethod.GET)
    public BigDecimal getBalance(@PathVariable int id){
        BigDecimal balance = accountsDAO.getBalance(id);
        return balance;
    }

    @RequestMapping(path = "listusers", method = RequestMethod.GET)
    public List<User> userList() {
        List<User> users = userDao.findAll();
        return users;
    }
}
