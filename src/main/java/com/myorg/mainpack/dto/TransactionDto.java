package com.myorg.mainpack.dto;

import com.myorg.mainpack.model.AbstractBaseEntity;
import com.myorg.mainpack.model.Bill;
import com.myorg.mainpack.model.Transaction;

import javax.persistence.*;


public class TransactionDto extends AbstractBaseEntity {

    private Bill billFrom;

    private Bill billTo;

    private int money;



    public TransactionDto(){}

    public TransactionDto(Transaction transaction){
        this.id = transaction.getId();
        this.billFrom = transaction.getBillFrom();
        this.billTo = transaction.getBillTo();
        this.money = transaction.getMoney();
    }



    public Bill getBillFrom() {
        return billFrom;
    }

    public void setBillFrom(Bill billFrom) {
        this.billFrom = billFrom;
    }

    public Bill getBillTo() {
        return billTo;
    }

    public void setBillTo(Bill billTo) {
        this.billTo = billTo;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}