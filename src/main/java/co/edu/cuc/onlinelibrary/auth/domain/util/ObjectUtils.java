package co.edu.cuc.onlinelibrary.auth.domain.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class ObjectUtils {

    private ObjectUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> T clone(T original) {
        try {
            byte[] bytes = objectToByte(original);
            Object clone = objectToByte(bytes);
            return (T) clone;
        } catch (IOException | ClassNotFoundException ex) {
            log.error("Error clonado objeto : {}", ex.getMessage(), ex);
            return null;
        }
    }

    private static byte[] objectToByte(Object object) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(object);
            return bos.toByteArray();
        }
    }

    private static Object objectToByte(byte[] byteArray) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
             ObjectInputStream in = new ObjectInputStream(bis)) {
            return in.readObject();
        }
    }
}
