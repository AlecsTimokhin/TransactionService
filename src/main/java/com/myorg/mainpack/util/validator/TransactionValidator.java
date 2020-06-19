package com.myorg.mainpack.util.validator;

import com.myorg.mainpack.model.Bill;
import com.myorg.mainpack.model.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;


@Component
public class TransactionValidator implements org.springframework.validation.Validator {

    @Value("${badMoney}")
    private String badMoney;

    @Value("${badBillFrom}")
    private String badBillFrom;

    @Value("${badBillTo}")
    private String badBillTo;



    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == Transaction.class;
    }


    @Override
    public void validate(Object obj, Errors errors) {

        Transaction t = ((Transaction) obj);

        int money = t.getMoney();
        if( money < 0 ){
            errors.rejectValue("money", "error.transaction", badMoney);
        }

        Bill billFrom = t.getBillFrom();
        if( billFrom == null || billFrom.getId() == null  ){
            errors.rejectValue("billFrom", "error.transaction", badBillFrom);
        }

        Bill billTo = t.getBillFrom();
        if( billTo == null || billTo.getId() == null  ){
            errors.rejectValue("billTo", "error.transaction", badBillTo);
        }

    }
}