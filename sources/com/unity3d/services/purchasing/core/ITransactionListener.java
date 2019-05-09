package com.unity3d.services.purchasing.core;

public interface ITransactionListener {
    void onTransactionComplete(TransactionDetails transactionDetails);

    void onTransactionError(TransactionErrorDetails transactionErrorDetails);
}
