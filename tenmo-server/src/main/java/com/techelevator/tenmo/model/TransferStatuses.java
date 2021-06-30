package com.techelevator.tenmo.model;

public class TransferStatuses {
    //Properties of Transfer Statuses aka row names
    private int transferStatusId;
    private String transferStatusDesc;

    //Getters and Setters
    public int getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(int transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public String getTransferStatusDesc() {
        return transferStatusDesc;
    }

    public void setTransferStatusDesc(String transferStatusDesc) {
        this.transferStatusDesc = transferStatusDesc;
    }
}
