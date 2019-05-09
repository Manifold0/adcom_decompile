package com.tapjoy.internal;

import android.net.Uri;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;

public final class en {
    /* renamed from: a */
    public static String m7653a(Map map) {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (Entry entry : map.entrySet()) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append(RequestParameters.AMPERSAND);
            }
            Object key = entry.getKey();
            if (key != null) {
                stringBuilder.append(Uri.encode(key.toString()));
            }
            stringBuilder.append(RequestParameters.EQUAL);
            Object value = entry.getValue();
            if (value != null) {
                stringBuilder.append(Uri.encode(value.toString()));
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public static void m7654a(HttpURLConnection httpURLConnection, String str, String str2, Charset charset) {
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Content-Type", str);
        byte[] bytes = str2.getBytes(charset);
        httpURLConnection.setFixedLengthStreamingMode(bytes.length);
        OutputStream outputStream = httpURLConnection.getOutputStream();
        try {
            outputStream.write(bytes);
        } finally {
            outputStream.close();
        }
    }
}
