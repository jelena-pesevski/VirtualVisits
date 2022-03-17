package org.unibl.etf.virtualbank.services;

import org.unibl.etf.virtualbank.exceptions.TransactionFailedException;
import org.unibl.etf.virtualbank.models.requests.TransactionRequest;

public interface TransactionService {

    void processTransaction(TransactionRequest request) throws TransactionFailedException;
}
