package transactions;

import java.io.Serializable;
import javax.crypto.SealedObject;

public class TransactionClient implements Serializable {

    private TransactionHeader transactionHeader;
    private SealedObject encryptedTransactionBody;
    private byte[] transactionSignature;

    public TransactionClient(TransactionHeader transactionHeader, SealedObject encryptedTransactionBody,
            byte[] transactionSignature) {
        this.transactionHeader = transactionHeader;
        this.encryptedTransactionBody = encryptedTransactionBody;
        this.transactionSignature = transactionSignature;
    }

    public TransactionHeader getTransactionHeader() {
        return this.transactionHeader;
    }

    public SealedObject getEncryptedTransactionBody() {
        return this.encryptedTransactionBody;
    }

    public byte[] getTransactionSignature() {
        return this.transactionSignature;
    }

}
