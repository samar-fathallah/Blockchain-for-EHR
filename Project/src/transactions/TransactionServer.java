package transactions;

import java.io.Serializable;
import javax.crypto.SealedObject;

public class TransactionServer implements Serializable {

    private int transactionID;
    private TransactionHeader transactionHeader;
    private SealedObject encryptedTransactionBody;
    private byte[] transactionClientSignature;

    public TransactionServer(int transactionID, TransactionHeader transactionHeader,
            SealedObject encryptedTransactionBody, byte[] transactionClientSignature) {
        this.transactionID = transactionID;
        this.transactionHeader = transactionHeader;
        this.encryptedTransactionBody = encryptedTransactionBody;
        this.transactionClientSignature = transactionClientSignature;
    }

    public int getTransactionID() {
        return this.transactionID;
    }

    public TransactionHeader getTransactionHeader() {
        return this.transactionHeader;
    }

    public SealedObject getEncryptedTransactionBody() {
        return this.encryptedTransactionBody;
    }

    public byte[] getTransactionClientSignature() {
        return this.transactionClientSignature;
    }

}
