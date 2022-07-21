package blockchain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import transactions.Transaction;

public class BlockBody implements Serializable {

    private int blockID;
    private ArrayList<Transaction> transactions;
    private Date timestamp;

    public BlockBody(int blockID, ArrayList<Transaction> transactions) {
        this.blockID = blockID;
        this.transactions = transactions;
        this.timestamp = new Date();
    }

    public int getBlockID() {
        return this.blockID;
    }

    public ArrayList<Transaction> getTransactions() {
        return this.transactions;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    @Override
    public String toString() {
        String result = "Block ID: " + this.blockID + "\n";
        for (Transaction transaction : this.transactions) {
            result += "Transaction " + transaction.getTransaction().getTransactionID() + "\n";
        }
        result += "Timestamp: " + this.timestamp;

        return result;
    }
}
