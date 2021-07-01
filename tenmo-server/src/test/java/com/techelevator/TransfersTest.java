package com.techelevator;

import com.techelevator.tenmo.model.Transfers;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class TransfersTest {
    Transfers transfers = new Transfers();

    @Test
    public void transfer_id_is_3(){
        transfers.setTransferId(3);
        Assert.assertEquals(3, transfers.getTransferId());
    }

    @Test
    public void transferTypeId_is_3(){
        transfers.setTransferTypeId(3);
        Assert.assertEquals(3, transfers.getTransferTypeId());
    }

    @Test
    public void transferStatusId_is_4(){
        transfers.setTransferStatusId(4);
        Assert.assertEquals(4, transfers.getTransferStatusId());
    }

    @Test
    public void accountFrom_is_5(){
        transfers.setAccountFrom(5);
        Assert.assertEquals(5, transfers.getAccountFrom());
    }

    @Test
    public void accountTo_is_2(){
        transfers.setAccountTo(2);
        Assert.assertEquals(2, transfers.getAccountTo());
    }

    @Test
    public void getAmount_is_10(){
        transfers.setAmount(BigDecimal.valueOf(10));
        Assert.assertEquals(BigDecimal.valueOf(10), transfers.getAmount());
    }
}
