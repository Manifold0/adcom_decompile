package com.moat.analytics.mobile.cha;

import com.moat.analytics.mobile.cha.base.functional.Optional;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

/* renamed from: com.moat.analytics.mobile.cha.m */
final class C1514m {

    /* renamed from: com.moat.analytics.mobile.cha.m$2 */
    static class C15132 extends Thread {
        /* renamed from: ˎ */
        private /* synthetic */ String f3543;

        C15132(String str) {
            this.f3543 = str;
        }

        public final void run() {
            try {
                C1514m.m3818(this.f3543);
            } catch (Exception e) {
            }
        }
    }

    C1514m() {
    }

    /* renamed from: ˋ */
    private static String m3817(InputStream inputStream) throws IOException {
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
    static Optional<String> m3818(String str) {
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
            of = Optional.of(C1514m.m3817(inputStream));
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
