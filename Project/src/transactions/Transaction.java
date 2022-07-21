package transactions;

import java.io.Serializable;

public class Transaction implements Serializable {

    private TransactionHashPointer previousTransaction;
    private TransactionServer transaction;
    private byte[] transactionSignature;

    public Transaction(TransactionHashPointer previousTransaction, TransactionServer transaction,
            byte[] transactionSignature) {
        this.previousTransaction = previousTransaction;
        this.transaction = transaction;
        this.transactionSignature = transactionSignature;
    }

    public TransactionHashPointer getPreviousTransaction() {
        return this.previousTransaction;
    }

    public TransactionServer getTransaction() {
        return this.transaction;
    }

    public byte[] getTransactionSignature() {
        return this.transactionSignature;
    }

}
