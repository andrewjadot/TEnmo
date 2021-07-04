package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Component
public class JDBCTransfersDAO implements TransfersDAO{
    private JdbcTemplate jdbcTemplate;
    private AccountsDAO accountsDAO;

    @Override
    public List<Transfers> getTransfersByList(int userId) {
        List<Transfers> list = new ArrayList<>();
        String sql = "SELECT t.*, u.username AS userFrom, v.username AS userTo FROM transfers " +
                "JOIN accounts a ON transfers.account_from = a.account_id " +
                "JOIN accounts b ON transfers.account_to = b.account_id " +
                "JOIN users u ON a.user_id = u.user_id " +
                "JOIN users v ON b.user_id = v.user_id " +
                "WHERE a.user_id = ? OR b.user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, userId);
        while (results.next() ) {
            Transfers transfer = mapRowToTransfer(results);
            list.add(transfer);
        }
        return list;
    }

    @Override
    public Transfers getTransferById(int transactionId) {
        Transfers transfer = new Transfers();
        String sql = "SELECT t.*, u.username AS userFrom, v.username AS userTo, ts.transfer_status_desc, tt.transfer_type_desc FROM transfers " +
                "JOIN accounts a ON transfers.account_from = a.account_id " +
                "JOIN accounts b ON transfers.account_to = b.account_id " +
                "JOIN users u ON a.user_id = u.user_id " +
                "JOIN users v ON b.user_id = v.user_id " +
                "JOIN transfer_statuses ts ON t.transfer_status_id = ts.transfer_status_id " +
                "JOIN transfer_types tt ON t.transfer_type_id = tt.transfer_type_id " +
                "WHERE transfers.transfer_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transactionId);
        if (results.next()) {
            transfer = mapRowToTransfer(results);
        }
        return transfer;
    }

    @Override
    public String sendTransfer(int userFrom, int userTo, BigDecimal amount) {
        if (userFrom == userTo){
            return "Error: Can't send money to yourself";
        }
        if (amount.compareTo(accountsDAO.getBalance(userFrom)) == -1 && amount.compareTo(new BigDecimal(0)) == 1){
            String sql = "INSERT INTO transfers (transfer_type_id, account_from, account_to, amount " +
                    "VALUES (2,2,?,?,?);";
            jdbcTemplate.update(sql, userFrom, userTo, amount);
            accountsDAO.addToBalance(amount, userTo);
            accountsDAO.subtractFromBalance(amount, userFrom);

        }
        return "Successful Transfer of Funds!";
    }

  


    //helper method
    private Transfers mapRowToTransfer(SqlRowSet results){
        Transfers transfer = new Transfers();
        transfer.setTransferId(results.getInt("transfer_id"));
        transfer.setTransferTypeId(results.getInt("transfer_type_id"));
        transfer.setTransferStatusId(results.getInt("transfer_status_id"));
        transfer.setAccountFrom(results.getInt("account_From"));
        transfer.setAccountTo(results.getInt("account_to"));
        transfer.setAmount(results.getBigDecimal("amount"));
        try {
            transfer.setUserFrom(results.getInt("userFrom"));
            transfer.setUserTo(results.getInt("userTo"));
        } catch (Exception e) {}
        try {
            transfer.setTransferType(results.getString("transfer_type_desc"));
            transfer.setTransferStatus(results.getString("transfer_status_desc"));
        } catch (Exception e) {}
        return transfer;
    }
}