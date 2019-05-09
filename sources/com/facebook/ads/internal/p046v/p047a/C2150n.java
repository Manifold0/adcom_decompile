package com.facebook.ads.internal.p046v.p047a;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.v.a.n */
public class C2150n {
    /* renamed from: a */
    private int f4977a;
    /* renamed from: b */
    private String f4978b;
    /* renamed from: c */
    private Map<String, List<String>> f4979c;
    /* renamed from: d */
    private byte[] f4980d;

    public C2150n(HttpURLConnection httpURLConnection, byte[] bArr) {
        try {
            this.f4977a = httpURLConnection.getResponseCode();
            this.f4978b = httpURLConnection.getURL().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.f4979c = httpURLConnection.getHeaderFields();
        this.f4980d = bArr;
    }

    /* renamed from: a */
    public int m5501a() {
        return this.f4977a;
    }

    /* renamed from: b */
    public String m5502b() {
        return this.f4978b;
    }

    /* renamed from: c */
    public Map<String, List<String>> m5503c() {
        return this.f4979c;
    }

    /* renamed from: d */
    public byte[] m5504d() {
        return this.f4980d;
    }

    /* renamed from: e */
    public String m5505e() {
        return this.f4980d != null ? new String(this.f4980d) : null;
    }
}
