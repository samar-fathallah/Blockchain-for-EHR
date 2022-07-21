package simulation;

import java.util.ArrayList;
import java.util.HashMap;
import javax.crypto.SecretKey;
import client.Doctor;
import server.Server;
import transactions.Transaction;
import transactions.TransactionClient;

public class Network {

    private Server server;
    private HashMap<Integer, Doctor> doctors;
    private int currentDoctorID;

    public Network() {
        this.server = new Server();
        this.currentDoctorID = 1;
        this.doctors = new HashMap<Integer, Doctor>();
    }

    public void createPatient() {
        this.server.createPatient();
    }

    public void createDoctor() {
        Doctor newDoctor = new Doctor(this.currentDoctorID++, this.server.getPublicKey());
        doctors.put(newDoctor.getDoctorID(), newDoctor);
        server.addDoctor(newDoctor.getDoctorID(), newDoctor.getPublicKey());
        System.out.println("Doctor (" + newDoctor.getDoctorID() + ") created successfully.");
    }

    public void assignPatientToDoctor(int patientID, int doctorID) {
        Doctor doctor = this.doctors.get(doctorID);
        if (doctor == null) {
            System.err.println("Invalid doctor id.");
            return;
        }
        SecretKey symmetricKey = this.server.assignPatientToDoctor(patientID, doctorID);
        if (symmetricKey == null) {
            return;
        }
        doctor.addPatient(patientID, symmetricKey);
    }

    public void createPatientInfoTransaction(int doctorID, int patientID, String name, String age,
            String weight, String height, String sex, HashMap<String, String> initialMeasurements) {
        Doctor doctor = this.doctors.get(doctorID);
        if (doctor == null) {
            System.err.println("Invalid doctor id.");
            return;
        }
        TransactionClient transactionClient = doctor.createPatientInfoTransaction(patientID, name, age, weight, height,
                sex, initialMeasurements);
        if (transactionClient == null) {
            return;
        }
        this.server.receiveTransaction(transactionClient);
    }

    public void createVisitTransaction(int doctorID, int patientID, String reason, String diagnosis,
            HashMap<String, String> measurements, HashMap<String, String> prescription) {
        Doctor doctor = this.doctors.get(doctorID);
        if (doctor == null) {
            System.err.println("Invalid doctor id.");
            return;
        }
        TransactionClient transactionClient = doctor.createVisitTransaction(patientID, reason, diagnosis, measurements,
                prescription);
        if (transactionClient == null) {
            System.err.println("Could not create transaction at client.");
            return;
        }
        this.server.receiveTransaction(transactionClient);
    }

    public void createLabTestTransaction(int doctorID, int patientID, String testName,
            HashMap<String, String> results) {
        Doctor doctor = this.doctors.get(doctorID);
        if (doctor == null) {
            System.err.println("Invalid doctor id.");
            return;
        }
        TransactionClient transactionClient = doctor.createLabTestTransaction(patientID, testName, results);
        if (transactionClient == null) {
            System.err.println("Could not create transaction at client.");
            return;
        }
        this.server.receiveTransaction(transactionClient);
    }

    public void getLastPatientTransaction(int doctorID, int patientID) {
        Doctor doctor = this.doctors.get(doctorID);
        if (doctor == null) {
            System.err.println("Invalid doctor id.");
            return;
        }
        if (!doctor.getLastPatientTransaction(patientID)) {
            return;
        }
        ArrayList<Transaction> transactions = this.server.getLastPatientTransaction(doctorID, patientID);
        doctor.receiveTransactions(transactions);
    }

    public void getAllPatientTransactions(int doctorID, int patientID) {
        Doctor doctor = this.doctors.get(doctorID);
        if (doctor == null) {
            System.err.println("Invalid doctor id.");
            return;
        }
        if (!doctor.getLastPatientTransaction(patientID)) {
            return;
        }
        ArrayList<Transaction> transactions = this.server.getAllPatientTransactions(doctorID, patientID);
        doctor.receiveTransactions(transactions);
    }

    public void viewBlocks() {
        this.server.viewBlocks();
    }

}
