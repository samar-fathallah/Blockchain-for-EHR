package crypto;

import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SHA {

    private static final String ALGORITHM = "SHA-512";

    public static byte[] generateHash(Serializable object)
            throws NoSuchAlgorithmException, IOException {

        MessageDigest md = MessageDigest.getInstance(ALGORITHM);
        byte[] bytes = Helpers.objectToByteArray(object);
        md.update(bytes);
        byte[] hash = md.digest();
        return hash;
    }

    public static boolean verifyHash(Serializable object, byte[] hash)
            throws NoSuchAlgorithmException, IOException {

        byte[] newHash = generateHash(object);
        return Arrays.equals(hash, newHash);
    }

}
