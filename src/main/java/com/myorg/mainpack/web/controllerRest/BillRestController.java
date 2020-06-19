package com.myorg.mainpack.web.controllerRest;

import com.fasterxml.jackson.databind.deser.BasicDeserializerFactory;
import com.myorg.mainpack.dto.BillDto;
import com.myorg.mainpack.dto.RestResponce;
import com.myorg.mainpack.exception.types.BadValidateException;
import com.myorg.mainpack.model.User;
import com.myorg.mainpack.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/rest/bills")
public class BillRestController {

    @Value("${createNewBill}")
    private String createNewBill;

    @Value("${badValidateNewBill}")
    private String badValidateNewBill;

    private BillService billService;
    public BillService getUserRepository() { return billService; }
    @Autowired
    public void setUserRepository(BillService billService) { this.billService = billService; }



    @GetMapping
    public ResponseEntity<List<BillDto>> getAllYours(HttpServletRequest request){
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        return ResponseEntity.status(200).body( billService.getAllYours(currentUser.getId()) );
    }


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<BillDto>> getAll(){
        return ResponseEntity.status(200).body( billService.getAll() );
    }


    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<RestResponce> save(@Valid @RequestBody BillDto billDto, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            throw new BadValidateException( new RestResponce(badValidateNewBill) );
        }

        billService.save(billDto);

        return ResponseEntity.status(201).body( new RestResponce(createNewBill) );

    }


}
