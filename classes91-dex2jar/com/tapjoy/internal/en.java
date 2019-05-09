// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.io.OutputStream;
import java.nio.charset.Charset;
import java.net.HttpURLConnection;
import java.util.Iterator;
import android.net.Uri;
import java.util.Map;

public final class en
{
    public static String a(final Map map) {
        final StringBuilder sb = new StringBuilder();
        final Iterator<Map.Entry<Object, V>> iterator = map.entrySet().iterator();
        int n = 1;
        while (iterator.hasNext()) {
            final Map.Entry<Object, V> entry = iterator.next();
            int n2;
            if (n != 0) {
                n2 = 0;
            }
            else {
                sb.append("&");
                n2 = n;
            }
            final Object key = entry.getKey();
            if (key != null) {
                sb.append(Uri.encode(key.toString()));
            }
            sb.append("=");
            final V value = entry.getValue();
            n = n2;
            if (value != null) {
                sb.append(Uri.encode(value.toString()));
                n = n2;
            }
        }
        return sb.toString();
    }
    
    public static void a(HttpURLConnection outputStream, final String s, final String s2, final Charset charset) {
        outputStream.setDoOutput(true);
        outputStream.setRequestProperty("Content-Type", s);
        final byte[] bytes = s2.getBytes(charset);
        outputStream.setFixedLengthStreamingMode(bytes.length);
        outputStream = (HttpURLConnection)outputStream.getOutputStream();
        try {
            ((OutputStream)outputStream).write(bytes);
        }
        finally {
            ((OutputStream)outputStream).close();
        }
    }
}
