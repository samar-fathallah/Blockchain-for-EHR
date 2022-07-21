package crypto;

import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

public class DSA {

    private static final String ALGORITHM = "SHA512withDSA";
    public static final int KEY_SIZE = 2048;

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");
        keyPairGen.initialize(KEY_SIZE);
        KeyPair pair = keyPairGen.generateKeyPair();
        return pair;
    }

    public static byte[] signObject(Serializable object, PrivateKey privateKey)
            throws NoSuchAlgorithmException, InvalidKeyException,
            SignatureException, IOException {

        Signature sign = Signature.getInstance(ALGORITHM);
        sign.initSign(privateKey);
        byte[] bytes = Helpers.objectToByteArray(object);
        sign.update(bytes);
        byte[] signature = sign.sign();
        return signature;
    }

    public static boolean verifyObject(Serializable object, byte[] signature, PublicKey publicKey)
            throws NoSuchAlgorithmException, InvalidKeyException,
            SignatureException, IOException {

        Signature sign = Signature.getInstance(ALGORITHM);
        sign.initVerify(publicKey);
        byte[] bytes = Helpers.objectToByteArray(object);
        sign.update(bytes);
        return sign.verify(signature);
    }

}
