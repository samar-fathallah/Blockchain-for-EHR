package transactions;

import java.io.Serializable;

public abstract class TransactionBody implements Serializable {

    public static final int PATIENT_INFO = 0;
    public static final int VISIT = 1;
    public static final int LAB_TEST = 2;

}
