package org.unibl.etf.virtualbank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.virtualbank.exceptions.TransactionFailedException;
import org.unibl.etf.virtualbank.models.requests.TransactionRequest;
import org.unibl.etf.virtualbank.services.TransactionService;

import javax.validation.Valid;

@RestController
@RequestMapping("/transactions")
@CrossOrigin(origins = "http://localhost:9000")
public class TransactionsController {

    private final TransactionService transactionService;

    public TransactionsController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public void processTransaction(@RequestBody @Valid TransactionRequest request) throws TransactionFailedException {
        transactionService.processTransaction(request);
    }
}
