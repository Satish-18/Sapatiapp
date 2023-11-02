package com.citytech.global.usertransaction.services.usertransactionservices;

import com.citytech.global.common.enumeration.TransactionStatus;
import com.citytech.global.usertransaction.repository.Transactions;

public interface UserTransactionService {
    Transactions createTransaction(Transactions transactions);
    void approveTransaction(int id, TransactionStatus status);
}

