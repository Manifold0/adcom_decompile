// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.b;

import org.json.JSONArray;
import java.util.Iterator;
import java.util.List;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;

public class s
{
    public static final String a(final Throwable t) {
        if (t == null) {
            return null;
        }
        final StringWriter stringWriter = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(stringWriter);
        t.printStackTrace(printWriter);
        printWriter.close();
        return stringWriter.toString();
    }
    
    public static String a(final List<String> list, final String s) {
        final StringBuilder sb = new StringBuilder("");
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            sb.append(s);
        }
        return sb.toString();
    }
    
    public static final String a(final StackTraceElement[] array) {
        final JSONArray jsonArray = new JSONArray();
        for (int length = array.length, i = 0; i < length; ++i) {
            jsonArray.put((Object)array[i].toString());
        }
        return jsonArray.toString();
    }
}
