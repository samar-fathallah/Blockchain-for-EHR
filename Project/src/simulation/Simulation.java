package simulation;

import java.util.HashMap;

public class Simulation {

    private Network network;
    private boolean isRunning;

    public Simulation() {
        this.network = new Network();
    }

    public void startSimulation() {
        this.isRunning = true;
        System.out.println("Simulation running...");
        while (this.isRunning) {
            this.displayOptions();
        }
    }

    private void displayOptions() {
        System.out.println();
        System.out.println("Enter a number from the below options:");
        System.out.println("1- Create a new patient");
        System.out.println("2- Create a new doctor");
        System.out.println("3- Assign patient to a doctor");
        System.out.println("4- Add a new record for a patient");
        System.out.println("5- Get patient(s) record(s)");
        System.out.println("6- View blocks");
        System.out.println("0- End simulation");
        System.out.println();
        String input = System.console().readLine();
        System.out.println();
        switch (input) {
            case "1":
                this.network.createPatient();
                break;
            case "2":
                this.network.createDoctor();
                break;
            case "3":
                this.assignPatientToDoctor();
                break;
            case "4":
                this.addRecord();
                break;
            case "5":
                this.getRecords();
                break;
            case "6":
                this.network.viewBlocks();
                break;
            case "0":
                this.isRunning = false;
                System.out.println("Simulation ended.");
                break;
            case "7aram 3aliko":
                System.out.println("W 3alikom el salam.");
                break;
            case "#":
                System.out.println("Ta7eyat HashTech team.");
                break;
            default:
                System.out.println("Invalid option.");
                break;
        }
        System.out.println();
        System.out.println("---------------------------------------------------------");
    }

    private void assignPatientToDoctor() {
        System.out.println("Assign patient to doctor");
        System.out.println("------------------------" + "\n");
        try {
            System.out.print("Enter a patient id: ");
            int patientID = Integer.parseInt(System.console().readLine());
            System.out.print("Enter a doctor id: ");
            int doctorID = Integer.parseInt(System.console().readLine());
            System.out.println();
            this.network.assignPatientToDoctor(patientID, doctorID);
        } catch (NumberFormatException e) {
            System.out.println("\n" + "Invalid input" + "\n");
            this.assignPatientToDoctor();
        }
    }

    private void addRecord() {
        System.out.println("Add a record");
        System.out.println("------------" + "\n");
        System.out.println("Enter a number from the below options:");
        System.out.println("1- Add a new patient info record");
        System.out.println("2- Add a new patient visit record");
        System.out.println("3- Add a new patient lab test record");
        System.out.println();
        String input = System.console().readLine();
        System.out.println();
        switch (input) {
            case "1":
                this.addPatientInfoRecord();
                break;
            case "2":
                this.addPatientVisitRecord();
                break;
            case "3":
                this.addPatientLabTestRecord();
                break;
            default:
                System.out.println("Invalid input" + "\n");
                this.addRecord();
                break;
        }
    }

    private void addPatientInfoRecord() {
        System.out.println("Add a patient info record");
        System.out.println("-------------------------" + "\n");
        try {
            System.out.print("Enter a doctor id: ");
            int doctorID = Integer.parseInt(System.console().readLine());
            System.out.print("Enter a patient id: ");
            int patientID = Integer.parseInt(System.console().readLine());
            System.out.print("Enter the patient's name: ");
            String name = System.console().readLine();
            System.out.print("Enter the patient's age: ");
            String age = System.console().readLine();
            System.out.print("Enter the patient's weight: ");
            String weight = System.console().readLine();
            System.out.print("Enter the patient's height: ");
            String height = System.console().readLine();
            System.out.print("Enter the patient's sex: ");
            String sex = System.console().readLine();
            System.out.println("Enter the patient's initial measurements: ");
            HashMap<String, String> initialMeasurements = getHashMapEntries();
            System.out.println();
            this.network.createPatientInfoTransaction(doctorID, patientID, name, age, weight, height, sex,
                    initialMeasurements);
        } catch (Exception e) {
            System.out.println("\n" + "Invalid input" + "\n");
            this.addPatientInfoRecord();
        }
    }

    private void addPatientVisitRecord() {
        System.out.println("Add a patient visit record");
        System.out.println("--------------------------" + "\n");
        try {
            System.out.print("Enter a doctor id: ");
            int doctorID = Integer.parseInt(System.console().readLine());
            System.out.print("Enter a patient id: ");
            int patientID = Integer.parseInt(System.console().readLine());
            System.out.print("Enter the patient's visit reason: ");
            String reason = System.console().readLine();
            System.out.print("Enter the patient's diagnosis: ");
            String diagnosis = System.console().readLine();
            System.out.println("Enter the patient's measurements: ");
            HashMap<String, String> measurements = getHashMapEntries();
            System.out.println("Enter the patient's prescriptions: ");
            HashMap<String, String> prescription = getHashMapEntries();
            System.out.println();
            this.network.createVisitTransaction(doctorID, patientID, reason, diagnosis, measurements, prescription);
        } catch (Exception e) {
            System.out.println("\n" + "Invalid input" + "\n");
            this.addPatientVisitRecord();
        }
    }

    private void addPatientLabTestRecord() {
        System.out.println("Add a patient lab test record");
        System.out.println("-----------------------------" + "\n");
        try {
            System.out.print("Enter a doctor id: ");
            int doctorID = Integer.parseInt(System.console().readLine());
            System.out.print("Enter a patient id: ");
            int patientID = Integer.parseInt(System.console().readLine());
            System.out.print("Enter the test name: ");
            String testName = System.console().readLine();
            System.out.println("Enter the test results: ");
            HashMap<String, String> results = getHashMapEntries();
            System.out.println();
            this.network.createLabTestTransaction(doctorID, patientID, testName, results);
        } catch (Exception e) {
            System.out.println("\n" + "Invalid input" + "\n");
            this.addPatientLabTestRecord();
        }
    }

    private HashMap<String, String> getHashMapEntries() {
        System.out.println(
                "(Enter the name and value of the item, then type 'yes' or 'y' when done, 'no' or 'n' otherwise)");
        HashMap<String, String> map = new HashMap<String, String>();
        boolean done = false;
        while (!done) {
            System.out.print("Enter the name of the item: ");
            String key = System.console().readLine();
            System.out.print("Enter the value of the item: ");
            String value = System.console().readLine();
            map.put(key, value);
            boolean invalid = true;
            while (invalid) {
                System.out.print("Done? ");
                String answer = System.console().readLine();
                switch (answer) {
                    case "yes":
                    case "y":
                        invalid = false;
                        done = true;
                        break;
                    case "no":
                    case "n":
                        invalid = false;
                    default:
                        break;
                }
            }
        }
        return map;
    }

    private void getRecords() {
        System.out.println("Get records");
        System.out.println("-----------" + "\n");
        System.out.println("Enter a number from the below options:");
        System.out.println("1- Get the last record of a patient");
        System.out.println("2- Get all the records of a patient");
        System.out.println();
        String input = System.console().readLine();
        System.out.println();
        switch (input) {
            case "1":
                this.getLastPatientRecord();
                break;
            case "2":
                this.getAllPatientRecords();
                break;
            default:
                System.out.println("Invalid input" + "\n");
                this.getRecords();
                break;
        }
    }

    private void getLastPatientRecord() {
        System.out.println("Get patient's last record");
        System.out.println("-------------------------" + "\n");
        try {
            System.out.print("Enter a doctor id: ");
            int doctorID = Integer.parseInt(System.console().readLine());
            System.out.print("Enter a patient id: ");
            int patientID = Integer.parseInt(System.console().readLine());
            System.out.println();
            this.network.getLastPatientTransaction(doctorID, patientID);
        } catch (NumberFormatException e) {
            System.out.println("\n" + "Invalid input" + "\n");
            this.getLastPatientRecord();
        }
    }

    private void getAllPatientRecords() {
        System.out.println("Get patient's all records");
        System.out.println("-------------------------" + "\n");
        try {
            System.out.print("Enter a doctor id: ");
            int doctorID = Integer.parseInt(System.console().readLine());
            System.out.print("Enter a patient id: ");
            int patientID = Integer.parseInt(System.console().readLine());
            System.out.println();
            this.network.getAllPatientTransactions(doctorID, patientID);
        } catch (NumberFormatException e) {
            System.out.println("\n" + "Invalid input" + "\n");
            this.getAllPatientRecords();
        }
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.startSimulation();
    }

}
