package server;

import javax.crypto.SecretKey;
import transactions.TransactionHashPointer;

public class Patient {

    private int patientID;
    private SecretKey symmetricKey;
    private TransactionHashPointer lastPublishedTransaction;
    private TransactionHashPointer lastTransaction;

    public Patient(int patientID, SecretKey symmetricKey) {
        this.patientID = patientID;
        this.symmetricKey = symmetricKey;
        this.lastPublishedTransaction = null;
        this.lastTransaction = null;
    }

    public int getPatientID() {
        return this.patientID;
    }

    public SecretKey getSymmetricKey() {
        return this.symmetricKey;
    }

    public TransactionHashPointer getLastPublishedTransaction() {
        return this.lastPublishedTransaction;
    }

    public void setLastPublishedTransaction(TransactionHashPointer lastPublishedTransaction) {
        this.lastPublishedTransaction = lastPublishedTransaction;
    }

    public TransactionHashPointer getLastTransaction() {
        return this.lastTransaction;
    }

    public void setLastTransaction(TransactionHashPointer lastTransaction) {
        this.lastTransaction = lastTransaction;
    }

}
