package com.techelevator;

import com.techelevator.tenmo.model.TransferStatuses;
import org.junit.Assert;
import org.junit.Test;

public class TransferStatusesTest {
    TransferStatuses transferStatuses = new TransferStatuses();

    @Test
    public void getTransferStatusId_is_1() {
        transferStatuses.setTransferStatusId(1);
        Assert.assertEquals(1, transferStatuses.getTransferStatusId());
    }

    @Test
    public void transferStatusDesc_is_new_transfer(){
        transferStatuses.setTransferStatusDesc("New Transfer");
        Assert.assertEquals("New Transfer", transferStatuses.getTransferStatusDesc());
    }
}
