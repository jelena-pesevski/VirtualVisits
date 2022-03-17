package org.unibl.etf.virtualbank.services.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.unibl.etf.virtualbank.exceptions.TransactionFailedException;
import org.unibl.etf.virtualbank.models.Account;
import org.unibl.etf.virtualbank.models.entities.AccountEntity;
import org.unibl.etf.virtualbank.models.entities.TransactionEntity;
import org.unibl.etf.virtualbank.models.requests.TransactionRequest;
import org.unibl.etf.virtualbank.repositories.AccountEntityRepository;
import org.unibl.etf.virtualbank.repositories.TransactionEntityRepository;
import org.unibl.etf.virtualbank.services.TransactionService;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final ModelMapper modelMapper;

    private final TransactionEntityRepository transactionEntityRepository;

    private final AccountEntityRepository accountEntityRepository;

    public TransactionServiceImpl(ModelMapper modelMapper, TransactionEntityRepository transactionEntityRepository, AccountEntityRepository accountEntityRepository) {
        this.modelMapper = modelMapper;
        this.transactionEntityRepository = transactionEntityRepository;
        this.accountEntityRepository = accountEntityRepository;
    }

    @Override
    public void processTransaction(TransactionRequest request) throws TransactionFailedException {
        AccountEntity accountEntity=accountEntityRepository.findByCreditCardNumber(request.getCreditCardNumber()).orElseThrow(TransactionFailedException::new);

        Account accountFromRequest=modelMapper.map(request, Account.class);
        Account accountFromDataBase=modelMapper.map(accountEntity, Account.class);

        //if data from request is not valid, or paying is forbidden or there is no enough money on bank account,
        // or card expired
        long millis=System.currentTimeMillis();
        Date currDate=new Date(millis);
        LocalDate now=LocalDate.now();
        if(!accountFromRequest.equals(accountFromDataBase) || !accountEntity.getCanPay() || accountEntity.getAmount()<request.getCashAmount() || now.isAfter(request.getExpirationDate())){
            throw new TransactionFailedException();
        }

        //else process the transaction
        accountEntity.setAmount(accountEntity.getAmount()-request.getCashAmount());
        //lower the amount left
        accountEntityRepository.save(accountEntity);

        //write transaction to db
        TransactionEntity transactionEntity=new TransactionEntity();
        transactionEntity.setTransactionId(null);
        transactionEntity.setAccountId(accountEntity.getAccoutId());
        transactionEntity.setDateTime(new Timestamp(millis));
        transactionEntity.setCashAmount(request.getCashAmount());
        transactionEntityRepository.save(transactionEntity);
    }
}
