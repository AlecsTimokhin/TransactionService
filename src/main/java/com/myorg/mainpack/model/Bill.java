package com.myorg.mainpack.model;

import com.myorg.mainpack.dto.BillDto;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "BILLS")
public class Bill extends AbstractBaseEntity {

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name="USER_ID")
    private User user;

    @Column(name = "MONEY")
    private int money;



    public Bill(){}

    public Bill(BillDto billDto){
        this.user = new User( billDto.getUser().getId() );
        this.money = billDto.getMoney();
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

}