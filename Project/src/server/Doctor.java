package server;

import java.security.PublicKey;
import java.util.HashSet;

public class Doctor {

    private int doctorID;
    private PublicKey doctorPublicKey;
    private HashSet<Integer> patients;

    public Doctor(int doctorID, PublicKey doctorPublicKey) {
        this.doctorID = doctorID;
        this.doctorPublicKey = doctorPublicKey;
        this.patients = new HashSet<Integer>();
    }

    public int getDoctorID() {
        return this.doctorID;
    }

    public PublicKey getPublicKey() {
        return this.doctorPublicKey;
    }

    public HashSet<Integer> getPatients() {
        return this.patients;
    }

    public void addPatient(int patientID) {
        this.patients.add(patientID);
    }

}
