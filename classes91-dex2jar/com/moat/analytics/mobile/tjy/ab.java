// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;
import com.moat.analytics.mobile.tjy.base.functional.a;
import java.io.InputStreamReader;
import java.io.InputStream;

enum ab implements aa
{
    a("instance", 0);
    
    private ab(final String s, final int n) {
    }
    
    private String a(final InputStream inputStream) {
        final char[] array = new char[256];
        final StringBuilder sb = new StringBuilder();
        final InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        int n = 0;
        int n2;
        do {
            final int read = inputStreamReader.read(array, 0, 256);
            if (read <= 0) {
                break;
            }
            n2 = n + read;
            sb.append(array, 0, read);
        } while ((n = n2) < 1024);
        return sb.toString();
    }
    
    @Override
    public final a a(String s) {
        final InputStream inputStream = null;
        InputStream inputStream3;
        final InputStream inputStream2 = inputStream3 = null;
        Object o = inputStream;
        try {
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(s).openConnection();
            inputStream3 = inputStream2;
            o = inputStream;
            httpURLConnection.setUseCaches(false);
            inputStream3 = inputStream2;
            o = inputStream;
            httpURLConnection.setReadTimeout(10000);
            inputStream3 = inputStream2;
            o = inputStream;
            httpURLConnection.setConnectTimeout(15000);
            inputStream3 = inputStream2;
            o = inputStream;
            httpURLConnection.setRequestMethod("GET");
            inputStream3 = inputStream2;
            o = inputStream;
            httpURLConnection.setDoInput(true);
            inputStream3 = inputStream2;
            o = inputStream;
            httpURLConnection.connect();
            inputStream3 = inputStream2;
            o = inputStream;
            if (httpURLConnection.getResponseCode() >= 400) {
                inputStream3 = inputStream2;
                o = inputStream;
                return com.moat.analytics.mobile.tjy.base.functional.a.a();
            }
            inputStream3 = inputStream2;
            o = inputStream;
            final InputStream inputStream4 = (InputStream)(o = (inputStream3 = httpURLConnection.getInputStream()));
            inputStream3 = (InputStream)(o = com.moat.analytics.mobile.tjy.base.functional.a.a(this.a(inputStream4)));
            if (inputStream4 != null) {
                try {
                    inputStream4.close();
                    return (a)inputStream3;
                }
                catch (IOException ex) {
                    return (a)inputStream3;
                }
            }
        }
        catch (IOException ex2) {
            o = inputStream3;
            s = (String)(o = com.moat.analytics.mobile.tjy.base.functional.a.a());
            if (inputStream3 != null) {
                try {
                    inputStream3.close();
                    return (a)s;
                }
                catch (IOException ex3) {
                    return (a)s;
                }
            }
        }
        finally {
            Label_0197: {
                if (o == null) {
                    break Label_0197;
                }
                try {
                    ((InputStream)o).close();
                }
                catch (IOException ex4) {}
            }
        }
        return (a)o;
    }
}
