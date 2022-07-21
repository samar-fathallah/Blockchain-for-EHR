package transactions.body;

import java.util.HashMap;
import transactions.TransactionBody;

public class PatientInfo extends TransactionBody {

    private String name, age, weight, height, sex;
    private HashMap<String, String> initialMeasurements;

    public PatientInfo(String name, String age, String weight, String height, String sex,
            HashMap<String, String> initialMeasurements) {
        super();
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.sex = sex;
        this.initialMeasurements = initialMeasurements;
    }

    public String getName() {
        return this.name;
    }

    public String getAge() {
        return this.age;
    }

    public String getWeight() {
        return this.weight;
    }

    public String getHeight() {
        return this.height;
    }

    public String getSex() {
        return this.sex;
    }

    public HashMap<String, String> getInitialMeasurements() {
        return this.initialMeasurements;
    }

    @Override
    public String toString() {
        String result = "Name: " + this.name + "\n";
        result += "Age: " + this.age + "\n";
        result += "Weight: " + this.weight + "\n";
        result += "Height: " + this.height + "\n";
        result += "Sex: " + this.sex + "\n";
        result += "Initial measurements: " + this.initialMeasurements.toString();
        return result;
    }

}
