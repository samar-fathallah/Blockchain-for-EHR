package client;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import crypto.AES;
import crypto.DSA;
import transactions.Transaction;
import transactions.TransactionBody;
import transactions.TransactionClient;
import transactions.TransactionHeader;
import transactions.body.LabTest;
import transactions.body.PatientInfo;
import transactions.body.Visit;

public class Doctor {

    private int doctorID;
    private KeyPair keyPair;
    private PublicKey serverPublicKey;
    private HashMap<Integer, Patient> patients;

    public Doctor(int doctorID, PublicKey serverPublicKey) {
        this.doctorID = doctorID;
        this.serverPublicKey = serverPublicKey;
        try {
            this.keyPair = DSA.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Could not generate key pair for the doctor.");
            e.printStackTrace();
        }
        this.patients = new HashMap<Integer, Patient>();
    }

    public int getDoctorID() {
        return this.doctorID;
    }

    public PublicKey getPublicKey() {
        return this.keyPair.getPublic();
    }

    public void addPatient(int patientID, SecretKey symmetricKey) {
        Patient newPatient = new Patient(patientID, symmetricKey);
        this.patients.put(patientID, newPatient);
        System.out.println("Patient assigned to doctor successfully.");
    }

    public TransactionClient createPatientInfoTransaction(int patientID, String name, String age,
            String weight, String height, String sex, HashMap<String, String> initialMeasurements) {

        Patient patient = this.patients.get(patientID);
        if (patient == null) {
            System.err.println("Invalid or unauthorized patient id.");
            return null;
        }
        byte[] iv = AES.generateIV();
        TransactionHeader transactionHeader = new TransactionHeader(TransactionBody.PATIENT_INFO, this.doctorID,
                patientID, iv);
        TransactionBody transactionBody = new PatientInfo(name, age, weight, height, sex, initialMeasurements);
        SealedObject encryptedTransactionBody = encryptTransaction(transactionBody,
                patient.getSymmetricKey(), iv);
        if (encryptedTransactionBody == null) {
            return null;
        }
        byte[] signature = signTransaction(transactionHeader, encryptedTransactionBody);
        if (signature == null) {
            return null;
        }
        TransactionClient transactionClient = new TransactionClient(transactionHeader, encryptedTransactionBody,
                signature);
        return transactionClient;
    }

    public TransactionClient createVisitTransaction(int patientID, String reason, String diagnosis,
            HashMap<String, String> measurements, HashMap<String, String> prescription) {

        Patient patient = this.patients.get(patientID);
        if (patient == null) {
            System.err.println("Invalid or unauthorized patient id.");
            return null;
        }
        byte[] iv = AES.generateIV();
        TransactionHeader transactionHeader = new TransactionHeader(TransactionBody.VISIT, this.doctorID, patientID,
                iv);
        TransactionBody transactionBody = new Visit(reason, diagnosis, measurements, prescription);
        SealedObject encryptedTransactionBody = encryptTransaction(transactionBody,
                patient.getSymmetricKey(), iv);
        if (encryptedTransactionBody == null) {
            return null;
        }
        byte[] signature = signTransaction(transactionHeader, encryptedTransactionBody);
        if (signature == null) {
            return null;
        }
        TransactionClient transactionClient = new TransactionClient(transactionHeader, encryptedTransactionBody,
                signature);
        return transactionClient;
    }

    public TransactionClient createLabTestTransaction(int patientID, String testName, HashMap<String, String> results) {
        Patient patient = this.patients.get(patientID);
        if (patient == null) {
            System.err.println("Invalid or unauthorized patient id.");
            return null;
        }
        byte[] iv = AES.generateIV();
        TransactionHeader transactionHeader = new TransactionHeader(TransactionBody.LAB_TEST, this.doctorID, patientID,
                iv);
        TransactionBody transactionBody = new LabTest(testName, results);
        SealedObject encryptedTransactionBody = encryptTransaction(transactionBody,
                patient.getSymmetricKey(), iv);
        if (encryptedTransactionBody == null) {
            return null;
        }
        byte[] signature = signTransaction(transactionHeader, encryptedTransactionBody);
        if (signature == null) {
            return null;
        }
        TransactionClient transactionClient = new TransactionClient(transactionHeader, encryptedTransactionBody,
                signature);
        return transactionClient;
    }

    public boolean getLastPatientTransaction(int patientID) {
        if (this.patients.get(patientID) == null) {
            System.err.println("Invalid or unauthorized patient id.");
            return false;
        }
        return true;
    }

    public boolean getAllPatientTransactions(int patientID) {
        if (this.patients.get(patientID) == null) {
            System.err.println("Invalid or unauthorized patient id.");
            return false;
        }
        return true;
    }

    public void receiveTransactions(ArrayList<Transaction> transactions) {
        if (transactions == null) {
            System.err.println("Could not retrieve transactions from server.");
            return;
        }
        ArrayList<TransactionHeader> transactionHeaders = new ArrayList<TransactionHeader>(transactions.size());
        ArrayList<TransactionBody> transactionBodies = new ArrayList<TransactionBody>(transactions.size());
        for (Transaction transaction : transactions) {
            boolean verified = verifyTransaction(transaction);
            if (!verified) {
                return;
            }
            TransactionHeader transactionHeader = transaction.getTransaction().getTransactionHeader();
            SealedObject encryptedTransactionBody = transaction.getTransaction().getEncryptedTransactionBody();
            TransactionBody transactionBody = decryptTransaction(encryptedTransactionBody,
                    this.patients.get(transactionHeader.getPatientID()).getSymmetricKey(), transactionHeader.getIV());
            if (transactionBody != null) {
                transactionHeaders.add(transactionHeader);
                transactionBodies.add(transactionBody);
            }
        }
        viewTransactions(transactionHeaders, transactionBodies);
        System.out.println(transactions.size() + " transaction(s) received successfully.");
    }

    private SealedObject encryptTransaction(TransactionBody transactionBody, SecretKey key, byte[] iv) {
        SealedObject encryptedTransactionBody;
        try {
            encryptedTransactionBody = AES.encryptObject(transactionBody, key, iv);
        } catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidAlgorithmParameterException | IllegalBlockSizeException | IOException e) {
            System.err.println("Could not encrypt transaction.");
            e.printStackTrace();
            return null;
        }
        return encryptedTransactionBody;
    }

    private TransactionBody decryptTransaction(SealedObject encryptedTransactionBody, SecretKey key, byte[] iv) {
        TransactionBody transactionBody;
        try {
            transactionBody = (TransactionBody) AES.decryptObject(encryptedTransactionBody, key, iv);
        } catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidAlgorithmParameterException | ClassNotFoundException | BadPaddingException
                | IllegalBlockSizeException | IOException e) {
            System.err.println("Could not decrypt transaction.");
            e.printStackTrace();
            return null;
        }
        return transactionBody;
    }

    private byte[] signTransaction(TransactionHeader transactionHeader, SealedObject encryptedTransactionBody) {
        byte[] signature;
        try {
            HashSet<Object> set = new HashSet<Object>();
            set.add(transactionHeader);
            set.add(encryptedTransactionBody);
            signature = DSA.signObject(set, this.keyPair.getPrivate());
        } catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException | IOException e) {
            System.err.println("Could not sign transaction.");
            e.printStackTrace();
            return null;
        }
        return signature;
    }

    private boolean verifyTransaction(Transaction transaction) {
        boolean verified;
        try {
            HashSet<Object> set = new HashSet<Object>();
            set.add(transaction.getPreviousTransaction());
            set.add(transaction.getTransaction());
            verified = DSA.verifyObject(set, transaction.getTransactionSignature(), this.serverPublicKey);
        } catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException | IOException e) {
            System.err.println("Could not verify transaction.");
            e.printStackTrace();
            return false;
        }
        if (!verified) {
            System.err.println("Invalid transaction signature.");
        }
        return true;
    }

    private void viewTransactions(ArrayList<TransactionHeader> transactionHeaders,
            ArrayList<TransactionBody> transactionBodies) {
        System.out.println();
        for (int i = 0; i < transactionHeaders.size(); i++) {
            System.out.println(transactionHeaders.get(i).toString());
            System.out.println(transactionBodies.get(i).toString());
            System.out.println();
        }
    }

}
