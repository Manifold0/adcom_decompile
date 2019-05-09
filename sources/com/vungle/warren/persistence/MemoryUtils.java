package com.vungle.warren.persistence;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MemoryUtils {
    @Deprecated
    public static void writeString(@Nullable String s, @NonNull ByteArrayOutputStream out) throws IOException {
        if (s == null) {
            out.write(toBytes(0));
            return;
        }
        byte[] bytes = s.getBytes();
        out.write(toBytes(bytes.length));
        out.write(bytes);
    }

    public static <T> void write(@Nullable T value, @NonNull ByteArrayOutputStream out) throws IOException {
        int i = 1;
        if (value == null) {
            out.write(toBytes(0));
            return;
        }
        byte[] bytes;
        if (value instanceof String) {
            bytes = ((String) value).getBytes();
        } else if (value instanceof Number) {
            if (value instanceof Integer) {
                bytes = toBytes(((Integer) value).intValue());
            } else if (value instanceof Long) {
                bytes = toBytes(((Long) value).longValue());
            } else {
                throw new IllegalArgumentException("Value type not supported!");
            }
        } else if (value instanceof Boolean) {
            bytes = new byte[1];
            if (!((Boolean) value).booleanValue()) {
                i = 0;
            }
            bytes[0] = (byte) i;
        } else {
            throw new IllegalArgumentException("Value type not supported!");
        }
        out.write(toBytes(bytes.length));
        out.write(bytes);
    }

    @NonNull
    public static String extractString(ByteBuffer buffy) {
        int byteCount = buffy.getInt();
        if (byteCount == 0) {
            return "";
        }
        byte[] stringBytes = new byte[byteCount];
        buffy.get(stringBytes);
        return new String(stringBytes);
    }

    @Nullable
    public static <T> T extract(ByteBuffer buffy, Class<T> clazz) {
        boolean z = true;
        int byteCount = buffy.getInt();
        if (byteCount == 0) {
            if (clazz == String.class) {
                return "";
            }
            return null;
        } else if (clazz == String.class) {
            byte[] valueBytes = new byte[byteCount];
            buffy.get(valueBytes);
            return new String(valueBytes);
        } else if (clazz == Integer.class) {
            return Integer.valueOf(buffy.getInt());
        } else {
            if (clazz == Long.class) {
                return Long.valueOf(buffy.getLong());
            }
            if (clazz == Boolean.class) {
                if (buffy.get() != (byte) 1) {
                    z = false;
                }
                return Boolean.valueOf(z);
            }
            throw new IllegalArgumentException("Class type " + clazz.getCanonicalName() + " not supported!");
        }
    }

    public static void writeStringArray(@NonNull String[] strings, @NonNull ByteArrayOutputStream out) throws IOException {
        out.write(toBytes(strings.length));
        for (String s : strings) {
            writeString(s, out);
        }
    }

    public static <T> void writeArray(@NonNull T[] values, @NonNull ByteArrayOutputStream out) throws IOException {
        out.write(toBytes(values.length));
        for (T t : values) {
            write(t, out);
        }
    }

    public static String[] extractStringArray(ByteBuffer buffer) {
        int stringCount = buffer.getInt();
        String[] result = new String[stringCount];
        for (int x = 0; x < stringCount; x++) {
            result[x] = extractString(buffer);
        }
        return result;
    }

    public static <T> T[] extractArray(ByteBuffer buffy, Class<T> clazz) {
        int count = buffy.getInt();
        Object[] result = (Object[]) ((Object[]) Array.newInstance(clazz, count));
        for (int x = 0; x < count; x++) {
            result[x] = extract(buffy, clazz);
        }
        return result;
    }

    public static void writeStringMap(@NonNull Map<String, String> map, @NonNull ByteArrayOutputStream out) throws IOException {
        int entryCount = map.size();
        String[] keys = new String[entryCount];
        String[] values = new String[entryCount];
        int x = 0;
        for (Entry<String, String> entry : map.entrySet()) {
            keys[x] = (String) entry.getKey();
            values[x] = (String) entry.getValue();
            x++;
        }
        writeStringArray(keys, out);
        writeStringArray(values, out);
    }

    public static <T> void writeMap(@NonNull Map<String, T> map, @NonNull ByteArrayOutputStream out) throws IOException {
        int entryCount = map.size();
        String[] keys = new String[entryCount];
        T[] values = null;
        int x = 0;
        for (Entry<String, T> entry : map.entrySet()) {
            keys[x] = (String) entry.getKey();
            if (values == null) {
                values = (Object[]) ((Object[]) Array.newInstance(entry.getValue().getClass(), entryCount));
            }
            values[x] = entry.getValue();
            x++;
        }
        writeArray(keys, out);
        if (values == null) {
            values = new Object[0];
        }
        writeArray(values, out);
    }

    public static Map<String, String> extractStringMap(ByteBuffer buffy) {
        String[] keys = extractStringArray(buffy);
        String[] values = extractStringArray(buffy);
        Map<String, String> ret = new HashMap(keys.length);
        for (int x = 0; x < keys.length; x++) {
            ret.put(keys[x], values[x]);
        }
        return ret;
    }

    public static <T> Map<String, T> extractMap(ByteBuffer buffy, Class<T> clazz) {
        String[] keys = (String[]) extractArray(buffy, String.class);
        T[] values = extractArray(buffy, clazz);
        Map<String, T> ret = new HashMap(keys.length);
        for (int x = 0; x < keys.length; x++) {
            ret.put(keys[x], values[x]);
        }
        return ret;
    }

    public static <T extends Memorable> void writeMemorable(@Nullable T memorable, ByteArrayOutputStream out) throws IOException {
        if (memorable == null) {
            out.write(toBytes(0));
            return;
        }
        byte[] bytes = memorable.toByteArray();
        out.write(toBytes(bytes.length));
        out.write(bytes);
    }

    @Nullable
    public static <T extends Memorable> T extractMemorable(ByteBuffer buffy, Class<T> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        int byteCount = buffy.getInt();
        if (byteCount == 0) {
            return null;
        }
        buffy.get(new byte[byteCount]);
        return (Memorable) clazz.getDeclaredConstructor(new Class[]{byte[].class}).newInstance(new Object[]{bytes});
    }

    public static byte[] toBytes(int value) {
        return new byte[]{(byte) (value >>> 24), (byte) (value >>> 16), (byte) (value >>> 8), (byte) value};
    }

    public static byte[] toBytes(long value) {
        byte[] result = new byte[8];
        for (int i = 7; i >= 0; i--) {
            result[i] = (byte) ((int) (255 & value));
            value >>= 8;
        }
        return result;
    }
}
