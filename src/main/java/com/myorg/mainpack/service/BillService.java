package com.myorg.mainpack.service;

import com.myorg.mainpack.dto.BillDto;
import com.myorg.mainpack.model.Bill;
import com.myorg.mainpack.model.User;
import com.myorg.mainpack.repository.BillRepository;
import com.myorg.mainpack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class BillService  {

    private BillRepository billRepository;
    public BillRepository getBillRepository() { return billRepository; }
    @Autowired
    public void setBillRepository(BillRepository billRepository) { this.billRepository = billRepository; }



    public List<BillDto> getAll(){

        List<BillDto> billDtoList = new ArrayList<>();

        List<Bill> billList =  billRepository.findAll();

        for( Bill bill : billList ){
            billDtoList.add( new BillDto(bill) );
        }

        return  billDtoList;

    }


    public List<BillDto> getAllYours(Long uid){

        List<BillDto> billDtoList = new ArrayList<>();

        List<Bill> billList =  billRepository.findAllYours(uid);

        for( Bill bill : billList ){
            billDtoList.add( new BillDto(bill) );
        }

        return  billDtoList;

    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Bill save(BillDto billDto) {
        Bill bill = new Bill(billDto);
        return billRepository.save(bill);
    }


}
