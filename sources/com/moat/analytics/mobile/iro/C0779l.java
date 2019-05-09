package com.moat.analytics.mobile.iro;

import com.moat.analytics.mobile.iro.base.functional.Optional;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

/* renamed from: com.moat.analytics.mobile.iro.l */
final class C0779l {

    /* renamed from: com.moat.analytics.mobile.iro.l$1 */
    static class C07781 extends Thread {
        /* renamed from: ˎ */
        private /* synthetic */ String f1236;

        C07781(String str) {
            this.f1236 = str;
        }

        public final void run() {
            try {
                C0779l.m1343(this.f1236);
            } catch (Exception e) {
            }
        }
    }

    C0779l() {
    }

    /* renamed from: ˋ */
    private static String m1342(InputStream inputStream) throws IOException {
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

    /* renamed from: ॱ */
    static Optional<String> m1343(String str) {
        Optional<String> of;
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
                return Optional.empty();
            }
            inputStream = httpURLConnection.getInputStream();
            of = Optional.of(C0779l.m1342(inputStream));
            if (inputStream == null) {
                return of;
            }
            try {
                inputStream.close();
                return of;
            } catch (IOException e) {
                return of;
            }
        } catch (IOException e2) {
            of = Optional.empty();
            if (inputStream == null) {
                return of;
            }
            try {
                inputStream.close();
                return of;
            } catch (IOException e3) {
                return of;
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
