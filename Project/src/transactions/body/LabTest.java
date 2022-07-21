package transactions.body;

import java.util.HashMap;
import transactions.TransactionBody;

public class LabTest extends TransactionBody {

    private String testName;
    private HashMap<String, String> results;

    public LabTest(String testName, HashMap<String, String> results) {
        super();
        this.testName = testName;
        this.results = results;
    }

    public String getTestName() {
        return this.testName;
    }

    public HashMap<String, String> getResults() {
        return this.results;
    }

    @Override
    public String toString() {
        String result = "Test name: " + this.testName + "\n";
        result += "Results: " + this.results.toString();
        return result;
    }

}
