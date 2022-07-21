package transactions;

import java.io.Serializable;
import java.util.Date;

public class TransactionHeader implements Serializable {

    private int transactionType, doctorID, patientID;
    private byte[] iv;
    private Date timestamp;

    public TransactionHeader(int transactionType, int doctorID, int patientID, byte[] iv) {
        this.transactionType = transactionType;
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.iv = iv;
        this.timestamp = new Date();
    }

    public int getTransactionType() {
        return this.transactionType;
    }

    public int getDoctorID() {
        return this.doctorID;
    }

    public int getPatientID() {
        return this.patientID;
    }

    public byte[] getIV() {
        return this.iv;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    @Override
    public String toString() {
        String transactionType;
        switch (this.transactionType) {
            case TransactionBody.PATIENT_INFO:
                transactionType = "Patient Information Transaction";
                break;
            case TransactionBody.VISIT:
                transactionType = "Visit Transaction";
                break;
            case TransactionBody.LAB_TEST:
                transactionType = "Lab Test Transaction";
                break;
            default:
                transactionType = "";
                break;
        }
        String result = "Transaction type: " + transactionType + "\n";
        result += "Doctor ID: " + this.doctorID + "\n";
        result += "Patient ID: " + this.patientID + "\n";
        result += "Timestamp: " + this.timestamp;
        return result;
    }

}
