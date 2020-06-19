package com.myorg.mainpack.model;

import com.myorg.mainpack.dto.TransactionDto;

import javax.persistence.*;


@Entity
@Table(name = "TRANSACTIONS")
public class Transaction extends AbstractBaseEntity {

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name="BILL_FROM")
    private Bill billFrom;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name="BILL_TO")
    private Bill billTo;

    @Column(name = "MONEY")
    private int money;



    public Transaction(){}

    public Transaction(TransactionDto transactionDto){
        this.billFrom = transactionDto.getBillFrom();
        this.billTo = transactionDto.getBillTo();
        this.money = transactionDto.getMoney();
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