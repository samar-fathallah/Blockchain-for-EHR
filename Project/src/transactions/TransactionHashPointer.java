package transactions;

import java.io.Serializable;

public class TransactionHashPointer implements Serializable {

    private Transaction pointer;
    private byte[] hash;

    public TransactionHashPointer(Transaction pointer, byte[] hash) {
        this.pointer = pointer;
        this.hash = hash;
    }

    public Transaction getPointer() {
        return this.pointer;
    }

    public byte[] getHash() {
        return this.hash;
    }

}
