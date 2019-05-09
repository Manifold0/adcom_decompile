package com.facebook.ads.internal.p046v.p047a;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* renamed from: com.facebook.ads.internal.v.a.f */
public abstract class C2136f implements C2135q {
    /* renamed from: a */
    private final C2142r f4948a;

    public C2136f() {
        this(new C2143g());
    }

    public C2136f(C2142r c2142r) {
        this.f4948a = c2142r;
    }

    /* renamed from: a */
    public OutputStream mo5507a(HttpURLConnection httpURLConnection) {
        return httpURLConnection.getOutputStream();
    }

    /* renamed from: a */
    public HttpURLConnection mo5508a(String str) {
        return (HttpURLConnection) new URL(str).openConnection();
    }

    /* renamed from: a */
    public void mo5509a(OutputStream outputStream, byte[] bArr) {
        outputStream.write(bArr);
    }

    /* renamed from: a */
    public void mo5510a(HttpURLConnection httpURLConnection, C2147j c2147j, String str) {
        httpURLConnection.setRequestMethod(c2147j.m5499c());
        httpURLConnection.setDoOutput(c2147j.m5498b());
        httpURLConnection.setDoInput(c2147j.m5497a());
        if (str != null) {
            httpURLConnection.setRequestProperty("Content-Type", str);
        }
        httpURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
    }

    /* renamed from: a */
    public boolean mo5511a(C2149m c2149m) {
        C2150n a = c2149m.m5500a();
        if (this.f4948a.mo5518a()) {
            this.f4948a.mo5516a("BasicRequestHandler.onError got");
            c2149m.printStackTrace();
        }
        return a != null && a.m5501a() > 0;
    }

    /* renamed from: a */
    public byte[] mo5512a(InputStream inputStream) {
        byte[] bArr = new byte[16384];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    /* renamed from: b */
    public InputStream mo5513b(HttpURLConnection httpURLConnection) {
        return httpURLConnection.getInputStream();
    }
}
