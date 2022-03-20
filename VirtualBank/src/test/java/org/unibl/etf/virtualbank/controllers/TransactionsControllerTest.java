package org.unibl.etf.virtualbank.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.unibl.etf.virtualbank.exceptions.TransactionFailedException;
import org.unibl.etf.virtualbank.models.enums.CreditCardType;
import org.unibl.etf.virtualbank.models.requests.TransactionRequest;
import org.unibl.etf.virtualbank.services.TransactionService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TransactionsControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionsController transactionsController;
    private TransactionRequest transactionRequest;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        transactionRequest = new TransactionRequest("test", "test", "1234567", CreditCardType.VISA, "0000", LocalDate.parse("2024-01-01"), 50.00 );
        mockMvc = MockMvcBuilders.standaloneSetup(transactionsController).build();
    }

    @AfterEach
    void tearDown() {
        transactionRequest = null;
    }

    @Test
    void postMappingTransactionRequest() throws Exception {
    //    when(transactionService.processTransaction(any())).thenCallRealMethod();
        mockMvc.perform(post("/transactions").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(transactionRequest))).
                andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}