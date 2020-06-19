package com.myorg.mainpack.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myorg.mainpack.model.AbstractBaseEntity;
import com.myorg.mainpack.model.Bill;
import com.myorg.mainpack.model.Role;
import com.myorg.mainpack.model.User;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


public class UserDto extends AbstractBaseEntity {

        private boolean active;

        @Size(min = 3, max = 255)
        private String username;

        @JsonIgnore
        private String password;

        private Set<Role> roles;

        private Set<BillDto> billsDto;



        public UserDto(){}

        public UserDto(User user){
            this.id = user.getId();
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.active = user.isActive();
            this.roles = user.getRoles();
        }




    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<BillDto> getBillsDto() {
        return billsDto;
    }

    public void setBillsDto(Set<BillDto> billsDto) {
        this.billsDto = billsDto;
    }
}