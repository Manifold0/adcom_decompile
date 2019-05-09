// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.persistence;

import java.util.Iterator;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import android.support.annotation.NonNull;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Array;
import android.support.annotation.Nullable;
import java.nio.ByteBuffer;

public class MemoryUtils
{
    @Nullable
    public static <T> T extract(final ByteBuffer byteBuffer, final Class<T> clazz) {
        boolean b = true;
        final int int1 = byteBuffer.getInt();
        if (int1 == 0) {
            if (clazz == String.class) {
                return (T)"";
            }
            return null;
        }
        else {
            if (clazz == String.class) {
                final byte[] array = new byte[int1];
                byteBuffer.get(array);
                return (T)new String(array);
            }
            if (clazz == Integer.class) {
                return (T)Integer.valueOf(byteBuffer.getInt());
            }
            if (clazz == Long.class) {
                return (T)Long.valueOf(byteBuffer.getLong());
            }
            if (clazz == Boolean.class) {
                if (byteBuffer.get() != 1) {
                    b = false;
                }
                return (T)Boolean.valueOf(b);
            }
            throw new IllegalArgumentException("Class type " + clazz.getCanonicalName() + " not supported!");
        }
    }
    
    public static <T> T[] extractArray(final ByteBuffer byteBuffer, final Class<T> clazz) {
        final int int1 = byteBuffer.getInt();
        final Object[] array = (Object[])Array.newInstance(clazz, int1);
        for (int i = 0; i < int1; ++i) {
            array[i] = extract(byteBuffer, clazz);
        }
        return (T[])array;
    }
    
    public static <T> Map<String, T> extractMap(final ByteBuffer byteBuffer, final Class<T> clazz) {
        final String[] array = extractArray(byteBuffer, String.class);
        final T[] array2 = extractArray(byteBuffer, clazz);
        final HashMap hashMap = new HashMap<String, T>(array.length);
        for (int i = 0; i < array.length; ++i) {
            hashMap.put(array[i], array2[i]);
        }
        return (Map<String, T>)hashMap;
    }
    
    @Nullable
    public static <T extends Memorable> T extractMemorable(final ByteBuffer byteBuffer, final Class<T> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        final int int1 = byteBuffer.getInt();
        if (int1 == 0) {
            return null;
        }
        final byte[] array = new byte[int1];
        byteBuffer.get(array);
        return clazz.getDeclaredConstructor(byte[].class).newInstance(array);
    }
    
    @NonNull
    public static String extractString(final ByteBuffer byteBuffer) {
        final int int1 = byteBuffer.getInt();
        if (int1 == 0) {
            return "";
        }
        final byte[] array = new byte[int1];
        byteBuffer.get(array);
        return new String(array);
    }
    
    public static String[] extractStringArray(final ByteBuffer byteBuffer) {
        final int int1 = byteBuffer.getInt();
        final String[] array = new String[int1];
        for (int i = 0; i < int1; ++i) {
            array[i] = extractString(byteBuffer);
        }
        return array;
    }
    
    public static Map<String, String> extractStringMap(final ByteBuffer byteBuffer) {
        final String[] stringArray = extractStringArray(byteBuffer);
        final String[] stringArray2 = extractStringArray(byteBuffer);
        final HashMap hashMap = new HashMap<String, String>(stringArray.length);
        for (int i = 0; i < stringArray.length; ++i) {
            hashMap.put(stringArray[i], stringArray2[i]);
        }
        return (Map<String, String>)hashMap;
    }
    
    public static byte[] toBytes(final int n) {
        return new byte[] { (byte)(n >>> 24), (byte)(n >>> 16), (byte)(n >>> 8), (byte)n };
    }
    
    public static byte[] toBytes(long n) {
        final byte[] array = new byte[8];
        for (int i = 7; i >= 0; --i) {
            array[i] = (byte)(0xFFL & n);
            n >>= 8;
        }
        return array;
    }
    
    public static <T> void write(@Nullable final T t, @NonNull final ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        boolean b = true;
        if (t == null) {
            byteArrayOutputStream.write(toBytes(0));
            return;
        }
        byte[] array;
        if (t instanceof String) {
            array = ((String)t).getBytes();
        }
        else if (t instanceof Number) {
            if (t instanceof Integer) {
                array = toBytes((int)t);
            }
            else {
                if (!(t instanceof Long)) {
                    throw new IllegalArgumentException("Value type not supported!");
                }
                array = toBytes((long)t);
            }
        }
        else {
            if (!(t instanceof Boolean)) {
                throw new IllegalArgumentException("Value type not supported!");
            }
            final byte[] array2 = { 0 };
            if (!(boolean)t) {
                b = false;
            }
            array2[0] = (byte)(b ? 1 : 0);
            array = array2;
        }
        byteArrayOutputStream.write(toBytes(array.length));
        byteArrayOutputStream.write(array);
    }
    
    public static <T> void writeArray(@NonNull final T[] array, @NonNull final ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        byteArrayOutputStream.write(toBytes(array.length));
        for (int length = array.length, i = 0; i < length; ++i) {
            write(array[i], byteArrayOutputStream);
        }
    }
    
    public static <T> void writeMap(@NonNull final Map<String, T> map, @NonNull final ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        final int size = map.size();
        final String[] array = new String[size];
        final Object[] array2 = null;
        int n = 0;
        final Iterator<Map.Entry<String, T>> iterator = map.entrySet().iterator();
        Object[] array3 = array2;
        while (iterator.hasNext()) {
            final Map.Entry<String, T> entry = iterator.next();
            array[n] = entry.getKey();
            Object[] array4;
            if ((array4 = array3) == null) {
                array4 = (Object[])Array.newInstance(entry.getValue().getClass(), size);
            }
            array4[n] = entry.getValue();
            ++n;
            array3 = array4;
        }
        writeArray(array, byteArrayOutputStream);
        if (array3 == null) {
            array3 = new Object[0];
        }
        writeArray(array3, byteArrayOutputStream);
    }
    
    public static <T extends Memorable> void writeMemorable(@Nullable final T t, final ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        if (t == null) {
            byteArrayOutputStream.write(toBytes(0));
            return;
        }
        final byte[] byteArray = t.toByteArray();
        byteArrayOutputStream.write(toBytes(byteArray.length));
        byteArrayOutputStream.write(byteArray);
    }
    
    @Deprecated
    public static void writeString(@Nullable final String s, @NonNull final ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        if (s == null) {
            byteArrayOutputStream.write(toBytes(0));
            return;
        }
        final byte[] bytes = s.getBytes();
        byteArrayOutputStream.write(toBytes(bytes.length));
        byteArrayOutputStream.write(bytes);
    }
    
    public static void writeStringArray(@NonNull final String[] array, @NonNull final ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        byteArrayOutputStream.write(toBytes(array.length));
        for (int length = array.length, i = 0; i < length; ++i) {
            writeString(array[i], byteArrayOutputStream);
        }
    }
    
    public static void writeStringMap(@NonNull final Map<String, String> map, @NonNull final ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        final int size = map.size();
        final String[] array = new String[size];
        final String[] array2 = new String[size];
        int n = 0;
        for (final Map.Entry<String, String> entry : map.entrySet()) {
            array[n] = entry.getKey();
            array2[n] = entry.getValue();
            ++n;
        }
        writeStringArray(array, byteArrayOutputStream);
        writeStringArray(array2, byteArrayOutputStream);
    }
}
