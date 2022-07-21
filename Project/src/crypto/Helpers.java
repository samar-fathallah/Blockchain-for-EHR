package crypto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Helpers {

    public static byte[] objectToByteArray(Serializable object) throws IOException {
        byte[] bytes;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(object);
            oos.flush();
            bytes = bos.toByteArray();
        }
        return bytes;
    }

}
