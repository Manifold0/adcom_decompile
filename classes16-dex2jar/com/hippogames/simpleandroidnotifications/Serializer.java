// 
// Decompiled by Procyon v0.5.34
// 

package com.hippogames.simpleandroidnotifications;

import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import android.util.Base64;

public class Serializer
{
    public static Object FromString(final String s) throws IOException, ClassNotFoundException {
        final ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(Base64.decode(s, 0)));
        final Object object = objectInputStream.readObject();
        objectInputStream.close();
        return object;
    }
    
    public static String ToString(final Serializable s) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(s);
        objectOutputStream.close();
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
    }
}
