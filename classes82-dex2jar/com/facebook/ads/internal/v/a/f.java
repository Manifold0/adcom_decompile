// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.v.a;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public abstract class f implements q
{
    private final r a;
    
    public f() {
        this(new g());
    }
    
    public f(final r a) {
        this.a = a;
    }
    
    @Override
    public OutputStream a(final HttpURLConnection httpURLConnection) {
        return httpURLConnection.getOutputStream();
    }
    
    @Override
    public HttpURLConnection a(final String s) {
        return (HttpURLConnection)new URL(s).openConnection();
    }
    
    @Override
    public void a(final OutputStream outputStream, final byte[] array) {
        outputStream.write(array);
    }
    
    @Override
    public void a(final HttpURLConnection httpURLConnection, final j j, final String s) {
        httpURLConnection.setRequestMethod(j.c());
        httpURLConnection.setDoOutput(j.b());
        httpURLConnection.setDoInput(j.a());
        if (s != null) {
            httpURLConnection.setRequestProperty("Content-Type", s);
        }
        httpURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
    }
    
    @Override
    public boolean a(final m m) {
        final n a = m.a();
        if (this.a.a()) {
            this.a.a("BasicRequestHandler.onError got");
            m.printStackTrace();
        }
        return a != null && a.a() > 0;
    }
    
    @Override
    public byte[] a(final InputStream inputStream) {
        final byte[] array = new byte[16384];
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            final int read = inputStream.read(array);
            if (read == -1) {
                break;
            }
            byteArrayOutputStream.write(array, 0, read);
        }
        byteArrayOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }
    
    @Override
    public InputStream b(final HttpURLConnection httpURLConnection) {
        return httpURLConnection.getInputStream();
    }
}
