package com.myorg.mainpack.dto;

import com.myorg.mainpack.model.AbstractBaseEntity;
import com.myorg.mainpack.model.Bill;
import com.myorg.mainpack.model.User;


public class BillDto extends AbstractBaseEntity {

    private UserDto userDto;

    private int money;



    public BillDto(){}

    public BillDto(Bill bill){
        this.id = bill.getId();
        this.userDto = new UserDto( bill.getUser() );
        this.money = bill.getMoney();
    }




    public UserDto getUser() {
        return userDto;
    }

    public void setUser(UserDto userDto) {
        this.userDto = userDto;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
