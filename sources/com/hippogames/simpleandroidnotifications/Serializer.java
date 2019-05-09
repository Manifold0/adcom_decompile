package com.hippogames.simpleandroidnotifications;

import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Serializer {
    public static String ToString(Serializable serializable) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        ObjectOutputStream stream = new ObjectOutputStream(bytes);
        stream.writeObject(serializable);
        stream.close();
        return Base64.encodeToString(bytes.toByteArray(), 0);
    }

    public static Object FromString(String serialized) throws IOException, ClassNotFoundException {
        ObjectInputStream stream = new ObjectInputStream(new ByteArrayInputStream(Base64.decode(serialized, 0)));
        Object object = stream.readObject();
        stream.close();
        return object;
    }
}
