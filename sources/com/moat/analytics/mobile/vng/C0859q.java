package com.moat.analytics.mobile.vng;

import com.moat.analytics.mobile.vng.p013a.p015b.C0820a;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

/* renamed from: com.moat.analytics.mobile.vng.q */
class C0859q {
    C0859q() {
    }

    /* renamed from: a */
    static C0820a<String> m1581a(String str) {
        C0820a<String> a;
        InputStream inputStream = null;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() >= 400) {
                a = C0820a.m1432a();
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                    }
                }
            } else {
                inputStream = httpURLConnection.getInputStream();
                a = C0820a.m1433a(C0859q.m1582a(inputStream));
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e2) {
                    }
                }
            }
        } catch (IOException e3) {
            a = C0820a.m1432a();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e4) {
                }
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e5) {
                }
            }
        }
        return a;
    }

    /* renamed from: a */
    private static String m1582a(InputStream inputStream) {
        char[] cArr = new char[256];
        StringBuilder stringBuilder = new StringBuilder();
        Reader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        int i = 0;
        do {
            int read = inputStreamReader.read(cArr, 0, cArr.length);
            if (read <= 0) {
                break;
            }
            i += read;
            stringBuilder.append(cArr, 0, read);
        } while (i < 1024);
        return stringBuilder.toString();
    }
}
