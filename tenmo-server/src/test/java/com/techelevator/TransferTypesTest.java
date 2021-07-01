package com.techelevator;

import com.techelevator.tenmo.model.TransferTypes;
import org.junit.Assert;
import org.junit.Test;

public class TransferTypesTest {
    TransferTypes types = new TransferTypes();

    @Test
    public void transferTypeId_is_2(){
        types.setTransferTypeId(2);
        Assert.assertEquals(2, types.getTransferTypeId());
    }

    @Test
    public void transferTypeDesc_is_request(){
        types.setTransferTypeDesc("request");
        Assert.assertEquals("request", types.getTransferTypeDesc());
    }

}
