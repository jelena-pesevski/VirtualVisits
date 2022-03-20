package org.unibl.etf.virtualbank.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.unibl.etf.virtualbank.exceptions.TransactionFailedException;
import org.unibl.etf.virtualbank.models.Account;
import org.unibl.etf.virtualbank.models.entities.AccountEntity;
import org.unibl.etf.virtualbank.models.enums.CreditCardType;
import org.unibl.etf.virtualbank.models.requests.TransactionRequest;
import org.unibl.etf.virtualbank.repositories.AccountEntityRepository;
import org.unibl.etf.virtualbank.repositories.TransactionEntityRepository;
import org.unibl.etf.virtualbank.services.TransactionService;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @Autowired
    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private AccountEntityRepository accountEntityRepository;

    @Mock
    private TransactionEntityRepository transactionEntityRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void testProcessTransactionWhenUserIsNotExisting() {
        Mockito.when(accountEntityRepository.findByCreditCardNumber("1234567")).thenReturn(Optional.empty());

        Exception e=assertThrows(TransactionFailedException.class, ()-> transactionService.processTransaction(new TransactionRequest("test", "test", "1234567", CreditCardType.VISA, "0000", LocalDate.parse("2024-01-01"), 50.00 )));
        assertThat(e, is(instanceOf(TransactionFailedException.class)));
    }

    @Test
    void testProcessTransactionWhenPayingIsNotEnabled(){
        Mockito.when(accountEntityRepository.findByCreditCardNumber("1234567")).thenReturn( Optional.of(new AccountEntity(1, "test", "test", "1234567", CreditCardType.VISA, 100.00, "0000", LocalDate.parse("2024-01-01"), false  )));
        Mockito.when(modelMapper.map(org.mockito.ArgumentMatchers.any(), org.mockito.ArgumentMatchers.any())).thenReturn(new Account("test", "test", "1234567", CreditCardType.VISA,"0000", LocalDate.parse("2024-01-01") ));

        Exception e=assertThrows(TransactionFailedException.class, ()-> transactionService.processTransaction(new TransactionRequest("test", "test", "1234567", CreditCardType.VISA, "0000", LocalDate.parse("2024-01-01"), 50.00 )));
        assertThat(e, is(instanceOf(TransactionFailedException.class)));
    }

    @Test
    void testProcessTransactionWhenCardIsExpired(){
        Mockito.when(accountEntityRepository.findByCreditCardNumber("1234567")).thenReturn( Optional.of(new AccountEntity(1, "test", "test", "1234567", CreditCardType.VISA, 100.00, "0000", LocalDate.parse("2022-01-01"), false  )));
        Mockito.when(modelMapper.map(org.mockito.ArgumentMatchers.any(), org.mockito.ArgumentMatchers.any())).thenReturn(new Account("test", "test", "1234567", CreditCardType.VISA,"0000", LocalDate.parse("2022-01-01") ));

        Exception e=assertThrows(TransactionFailedException.class, ()-> transactionService.processTransaction(new TransactionRequest("test", "test", "1234567", CreditCardType.VISA, "0000", LocalDate.parse("2022-01-01"), 50.00 )));
        assertThat(e, is(instanceOf(TransactionFailedException.class)));
    }

    @Test
    void testProcessTransactionWhenPriceIsGreaterThanCashAmount(){
        Mockito.when(accountEntityRepository.findByCreditCardNumber("1234567")).thenReturn( Optional.of(new AccountEntity(1, "test", "test", "1234567", CreditCardType.VISA, 20.00, "0000", LocalDate.parse("2024-01-01"), false  )));
        Mockito.when(modelMapper.map(org.mockito.ArgumentMatchers.any(), org.mockito.ArgumentMatchers.any())).thenReturn(new Account("test", "test", "1234567", CreditCardType.VISA,"0000", LocalDate.parse("2024-01-01") ));

        Exception e=assertThrows(TransactionFailedException.class, ()-> transactionService.processTransaction(new TransactionRequest("test", "test", "1234567", CreditCardType.VISA, "0000", LocalDate.parse("2024-01-01"), 50.00 )));
        assertThat(e, is(instanceOf(TransactionFailedException.class)));
    }

    @Test
    void testProcessTransactionWhenAccountDataIsNotValid(){
        Mockito.when(accountEntityRepository.findByCreditCardNumber("1234567")).thenReturn( Optional.of(new AccountEntity(1, "test", "test", "1234567", CreditCardType.VISA, 100.00, "0001", LocalDate.parse("2024-01-01"), false  )));
        Mockito.when(modelMapper.map(org.mockito.ArgumentMatchers.any(), org.mockito.ArgumentMatchers.any())).thenReturn(new Account("test", "test", "1234567", CreditCardType.VISA,"0001", LocalDate.parse("2024-01-01") ));

        Exception e=assertThrows(TransactionFailedException.class, ()-> transactionService.processTransaction(new TransactionRequest("test", "test", "1234567", CreditCardType.VISA, "0000", LocalDate.parse("2024-01-01"), 50.00 )));
        assertThat(e, is(instanceOf(TransactionFailedException.class)));
    }
}