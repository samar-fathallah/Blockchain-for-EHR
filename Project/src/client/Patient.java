package client;

import javax.crypto.SecretKey;

public class Patient {

    private int patientID;
    private SecretKey symmetricKey;

    public Patient(int patientID, SecretKey symmetricKey) {
        this.patientID = patientID;
        this.symmetricKey = symmetricKey;
    }

    public int getPatientID() {
        return this.patientID;
    }

    public SecretKey getSymmetricKey() {
        return this.symmetricKey;
    }

}
