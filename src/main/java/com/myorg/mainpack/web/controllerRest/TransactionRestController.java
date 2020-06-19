package com.myorg.mainpack.web.controllerRest;

import com.myorg.mainpack.dto.BillDto;
import com.myorg.mainpack.dto.RestResponce;
import com.myorg.mainpack.dto.TransactionDto;
import com.myorg.mainpack.exception.types.BadValidateException;
import com.myorg.mainpack.model.User;
import com.myorg.mainpack.service.BillService;
import com.myorg.mainpack.service.TransactionService;
import com.myorg.mainpack.util.validator.TransactionValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transaction;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/rest/transactions")
public class TransactionRestController {

    @Value("${createNewTR}")
    private String createNewTR;

    @Value("${badValidateNewTR}")
    private String badValidateNewTR;

    private TransactionService transactionService;
    public TransactionService getTransactionRepository() { return transactionService; }
    @Autowired
    public void setTransactionRepository(TransactionService transactionService) { this.transactionService = transactionService; }


    TransactionValidator transactionValidator;
    public TransactionValidator getTransactionValidator() { return transactionValidator; }
    @Autowired
    public void setTransactionValidator(TransactionValidator transactionValidator) { this.transactionValidator = transactionValidator; }



    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(transactionValidator);
    }


    @PostMapping
    public ResponseEntity<RestResponce> addNewTransaction(@Valid @RequestBody TransactionDto transactionDto,
                                                          BindingResult bindingResult,
                                                          HttpServletRequest request){

        boolean canDoActions = (boolean) request.getSession().getAttribute("canDoActions");
        User currentUser = (User) request.getSession().getAttribute("currentUser");

        if (bindingResult.hasErrors()) {
            throw new BadValidateException( new RestResponce(badValidateNewTR) );
        }

        transactionService.addNewTransaction(transactionDto, canDoActions, currentUser);

        return ResponseEntity.status(201).body( new RestResponce(createNewTR) );

    }


}
