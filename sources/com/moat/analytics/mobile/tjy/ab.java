package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.functional.C2749a;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

enum ab implements aa {
    instance;

    /* renamed from: a */
    private String m6794a(InputStream inputStream) {
        char[] cArr = new char[256];
        StringBuilder stringBuilder = new StringBuilder();
        Reader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        int i = 0;
        do {
            int read = inputStreamReader.read(cArr, 0, 256);
            if (read <= 0) {
                break;
            }
            i += read;
            stringBuilder.append(cArr, 0, read);
        } while (i < 1024);
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public final C2749a mo6080a(String str) {
        C2749a a;
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
                return C2749a.m6883a();
            }
            inputStream = httpURLConnection.getInputStream();
            a = C2749a.m6884a(m6794a(inputStream));
            if (inputStream == null) {
                return a;
            }
            try {
                inputStream.close();
                return a;
            } catch (IOException e) {
                return a;
            }
        } catch (IOException e2) {
            a = C2749a.m6883a();
            if (inputStream == null) {
                return a;
            }
            try {
                inputStream.close();
                return a;
            } catch (IOException e3) {
                return a;
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e4) {
                }
            }
        }
    }
}
