package com.myorg.mainpack.service;

import com.myorg.mainpack.dto.RestResponce;
import com.myorg.mainpack.dto.TransactionDto;
import com.myorg.mainpack.exception.types.BadValidateException;
import com.myorg.mainpack.exception.types.ConflictException;
import com.myorg.mainpack.exception.types.NoAccessException;
import com.myorg.mainpack.exception.types.NotFoundException;
import com.myorg.mainpack.model.Bill;
import com.myorg.mainpack.model.Role;
import com.myorg.mainpack.model.Transaction;
import com.myorg.mainpack.model.User;
import com.myorg.mainpack.repository.BillRepository;
import com.myorg.mainpack.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class TransactionService {

    @Value("${badValidateNewTR}")
    private String badValidateNewTR;

    @Value("${badSumm}")
    private String badSumm;

    @Value("${notFoundBillFrom}")
    private String notFoundBillFrom;

    @Value("${notFoundBillTo}")
    private String notFoundBillTo;

    @Value("${noAccsses}")
    private String noAccsses;


    private TransactionRepository transactionRepository;
    public TransactionRepository getTransactionRepository() { return transactionRepository; }
    @Autowired
    public void setTransactionRepository(TransactionRepository transactionRepository) { this.transactionRepository = transactionRepository; }

    private BillRepository billRepository;
    public BillRepository getBillRepository() { return billRepository; }
    @Autowired
    public void setBillRepository(BillRepository billRepository) { this.billRepository = billRepository; }




    // Метод анализа и добавления новой транзакции
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Transaction addNewTransaction(TransactionDto transactionDto, boolean canDoActions, User currentUser) {

        if( transactionDto.getBillFrom() != null ){

            if( transactionDto.getBillFrom().getId() != null ){

                Long id = transactionDto.getBillFrom().getId();
                Bill billFrom = billRepository.getById(id);

                if( billFrom == null ){
                    throw new NotFoundException( new RestResponce(notFoundBillFrom) );
                }

                // Только админ может добавлять транзакциии не со своих счетов
                if( !canDoActions && !currentUser.getId().equals( billFrom.getUser().getId() ) ){
                    throw new NoAccessException( new RestResponce(noAccsses) );
                }

                // Если кол-во денег отрицательное
                if( billFrom.getMoney() - transactionDto.getMoney() < 0 ){
                    throw new ConflictException( new RestResponce(badSumm) );
                }

                // Списываем средства с счета billFrom
                billFrom.setMoney( billFrom.getMoney() - transactionDto.getMoney() );

            }
            else{
                throw new BadValidateException( new RestResponce(badValidateNewTR) );
            }

        }


        if( transactionDto.getBillTo() != null ){

            if( transactionDto.getBillFrom().getId() != null ){

                Long id = transactionDto.getBillFrom().getId();
                Bill billTo = billRepository.getById(id);

                if( billTo == null ){
                    throw new NotFoundException( new RestResponce(notFoundBillTo) );
                }

                // Зачисляем средства на счет billTo
                billTo.setMoney( billTo.getMoney() + transactionDto.getMoney() );

            }
            else{
                throw new BadValidateException( new RestResponce(badValidateNewTR) );
            }

        }

        Transaction transaction = new Transaction(transactionDto);

        return transactionRepository.save(transaction);

    }


}
