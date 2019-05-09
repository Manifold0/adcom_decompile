// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.metrics;

import java.util.List;
import net.hockeyapp.android.metrics.model.IJsonSerializable;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.io.Writer;

public final class JsonHelper
{
    private static final String[] CONTROL_CHARACTERS;
    private static final int CONTROL_CHARACTER_RANGE = 128;
    
    static {
        CONTROL_CHARACTERS = new String[128];
        for (int i = 0; i <= 31; ++i) {
            JsonHelper.CONTROL_CHARACTERS[i] = String.format("\\u%04X", i);
        }
        JsonHelper.CONTROL_CHARACTERS[34] = "\\\"";
        JsonHelper.CONTROL_CHARACTERS[92] = "\\\\";
        JsonHelper.CONTROL_CHARACTERS[8] = "\\b";
        JsonHelper.CONTROL_CHARACTERS[12] = "\\f";
        JsonHelper.CONTROL_CHARACTERS[10] = "\\n";
        JsonHelper.CONTROL_CHARACTERS[13] = "\\r";
        JsonHelper.CONTROL_CHARACTERS[9] = "\\t";
    }
    
    private JsonHelper() {
    }
    
    public static String convert(final char c) {
        return Character.toString(c);
    }
    
    public static String convert(final Double n) {
        return Double.toString(n);
    }
    
    public static String convert(final Float n) {
        return Float.toString(n);
    }
    
    public static String convert(final Integer n) {
        return Integer.toString(n);
    }
    
    public static String convert(final Long n) {
        return Long.toString(n);
    }
    
    public static String convert(final String s) {
        if (s == null) {
            return "null";
        }
        if (s.length() == 0) {
            return "\"\"";
        }
        return escapeJSON(s);
    }
    
    public static String convert(final boolean b) {
        return Boolean.toString(b);
    }
    
    private static String escapeJSON(final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append("\"");
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 < '\u0080') {
                final String s2 = JsonHelper.CONTROL_CHARACTERS[char1];
                if (s2 == null) {
                    sb.append(char1);
                }
                else {
                    sb.append(s2);
                }
            }
            else if (char1 == '\u2028') {
                sb.append("\\u2028");
            }
            else if (char1 == '\u2029') {
                sb.append("\\u2029");
            }
            else {
                sb.append(char1);
            }
        }
        sb.append("\"");
        return sb.toString();
    }
    
    public static <T> void writeDictionary(final Writer writer, final Map<String, T> map) throws IOException {
        if (map == null || map.isEmpty()) {
            writer.write("null");
        }
        else {
            final Iterator<String> iterator = map.keySet().iterator();
            if (iterator.hasNext()) {
                writer.write("{");
                final String s = iterator.next();
                final T value = map.get(s);
                writer.write("\"" + s + "\"");
                writer.write(":");
                writeItem(writer, value);
                while (iterator.hasNext()) {
                    final String s2 = iterator.next();
                    writer.write(",");
                    writer.write("\"" + s2 + "\"");
                    writer.write(":");
                    writeItem(writer, map.get(s2));
                }
                writer.write("}");
            }
        }
    }
    
    private static <T> void writeItem(final Writer writer, final T t) throws IOException {
        if (t == null) {
            writer.write("null");
            return;
        }
        if (t instanceof String) {
            writer.write(convert((String)t));
            return;
        }
        if (t instanceof Double) {
            writer.write(convert((Double)t));
            return;
        }
        if (t instanceof Integer) {
            writer.write(convert((Integer)t));
            return;
        }
        if (t instanceof Long) {
            writer.write(convert((Long)t));
            return;
        }
        if (t instanceof IJsonSerializable) {
            ((IJsonSerializable)t).serialize(writer);
            return;
        }
        throw new IOException("Cannot serialize: " + t.toString());
    }
    
    public static void writeJsonSerializable(final Writer writer, final IJsonSerializable jsonSerializable) throws IOException {
        if (jsonSerializable != null) {
            jsonSerializable.serialize(writer);
        }
    }
    
    public static <T extends IJsonSerializable> void writeList(final Writer writer, final List<T> list) throws IOException {
        if (list == null || list.isEmpty()) {
            writer.write("null");
        }
        else {
            final Iterator<T> iterator = list.iterator();
            if (iterator.hasNext()) {
                writer.write("[");
                iterator.next().serialize(writer);
                while (iterator.hasNext()) {
                    final IJsonSerializable jsonSerializable = iterator.next();
                    writer.write(",");
                    jsonSerializable.serialize(writer);
                }
                writer.write("]");
            }
        }
    }
}
