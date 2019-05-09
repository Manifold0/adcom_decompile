package com.facebook.ads.internal.p046v.p047a;

import android.os.Build.VERSION;
import android.support.annotation.AnyThread;
import android.support.annotation.WorkerThread;
import android.util.Log;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.net.ssl.HttpsURLConnection;

@WorkerThread
/* renamed from: com.facebook.ads.internal.v.a.a */
public class C2138a {
    /* renamed from: f */
    private static int[] f4950f = new int[20];
    /* renamed from: g */
    private static final String f4951g = C2138a.class.getSimpleName();
    /* renamed from: j */
    private static C2055a f4952j;
    /* renamed from: a */
    protected final C2135q f4953a = new C21371(this);
    /* renamed from: b */
    protected final C2140d f4954b = new C2141e();
    /* renamed from: c */
    protected C2142r f4955c = new C2143g();
    /* renamed from: d */
    protected int f4956d = 2000;
    /* renamed from: e */
    protected int f4957e = 8000;
    /* renamed from: h */
    private int f4958h = 3;
    /* renamed from: i */
    private Map<String, String> f4959i = new TreeMap();
    /* renamed from: k */
    private boolean f4960k;
    /* renamed from: l */
    private Set<String> f4961l;
    /* renamed from: m */
    private Set<String> f4962m;

    /* renamed from: com.facebook.ads.internal.v.a.a$a */
    public interface C2055a {
        @WorkerThread
        /* renamed from: a */
        Map<String, String> mo5466a();
    }

    /* renamed from: com.facebook.ads.internal.v.a.a$1 */
    class C21371 extends C2136f {
        /* renamed from: a */
        final /* synthetic */ C2138a f4949a;

        C21371(C2138a c2138a) {
            this.f4949a = c2138a;
        }
    }

    static {
        if (VERSION.SDK_INT < 8) {
            System.setProperty("http.keepAlive", "false");
        }
        if (VERSION.SDK_INT > 8 && CookieHandler.getDefault() == null) {
            CookieHandler.setDefault(new CookieManager());
        }
    }

    /* renamed from: a */
    public static synchronized void m5453a(C2055a c2055a) {
        synchronized (C2138a.class) {
            f4952j = c2055a;
        }
    }

    /* renamed from: a */
    protected int m5454a(int i) {
        return f4950f[i + 2] * 1000;
    }

    /* renamed from: a */
    protected int m5455a(HttpURLConnection httpURLConnection, byte[] bArr) {
        OutputStream outputStream = null;
        try {
            outputStream = this.f4953a.mo5507a(httpURLConnection);
            if (outputStream != null) {
                this.f4953a.mo5509a(outputStream, bArr);
            }
            int responseCode = httpURLConnection.getResponseCode();
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e) {
                }
            }
            return responseCode;
        } catch (Throwable th) {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e2) {
                }
            }
        }
    }

    @AnyThread
    /* renamed from: a */
    public C2138a m5456a(String str, String str2) {
        this.f4959i.put(str, str2);
        return this;
    }

    /* renamed from: a */
    public C2150n m5457a(C2145l c2145l) {
        int i = 0;
        long currentTimeMillis = System.currentTimeMillis();
        while (i < this.f4958h) {
            try {
                m5476c(m5454a(i));
                if (this.f4955c.mo5518a()) {
                    this.f4955c.mo5516a((i + 1) + "of" + this.f4958h + ", trying " + c2145l.m5493a());
                }
                currentTimeMillis = System.currentTimeMillis();
                C2150n a = m5458a(c2145l.m5493a(), c2145l.m5494b(), c2145l.m5495c(), c2145l.m5496d());
                if (a != null) {
                    return a;
                }
                i++;
            } catch (C2149m e) {
                if (m5468a((Throwable) e, currentTimeMillis) && i < this.f4958h - 1) {
                    continue;
                } else if (!this.f4953a.mo5511a(e) || i >= this.f4958h - 1) {
                    throw e;
                } else {
                    try {
                        Thread.sleep((long) this.f4956d);
                    } catch (InterruptedException e2) {
                        throw e;
                    }
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    protected C2150n m5458a(String str, C2147j c2147j, String str2, byte[] bArr) {
        HttpURLConnection a;
        Throwable e;
        Exception exception;
        C2150n c2150n = null;
        C2150n c2150n2 = null;
        C2150n a2;
        try {
            this.f4960k = false;
            a = m5463a(str);
            try {
                m5466a(a, c2147j, str2);
                for (String str3 : this.f4959i.keySet()) {
                    a.setRequestProperty(str3, (String) this.f4959i.get(str3));
                }
                synchronized (C2138a.class) {
                    if (f4952j != null) {
                        Map a3 = f4952j.mo5466a();
                        for (String str32 : a3.keySet()) {
                            a.setRequestProperty(str32, (String) a3.get(str32));
                        }
                    }
                }
                if (this.f4955c.mo5518a()) {
                    this.f4955c.mo5517a(a, bArr);
                }
                a.connect();
                this.f4960k = true;
                Object obj = (this.f4962m == null || this.f4962m.isEmpty()) ? null : 1;
                Object obj2 = (this.f4961l == null || this.f4961l.isEmpty()) ? null : 1;
                if ((a instanceof HttpsURLConnection) && !(obj == null && obj2 == null)) {
                    try {
                        C2151o.m5507a((HttpsURLConnection) a, this.f4962m, this.f4961l);
                    } catch (Throwable e2) {
                        Log.e(f4951g, "Unable to validate SSL certificates.", e2);
                    }
                }
                if (a.getDoOutput() && bArr != null) {
                    m5455a(a, bArr);
                }
                a2 = a.getDoInput() ? m5461a(a) : new C2150n(a, null);
                if (this.f4955c.mo5518a()) {
                    this.f4955c.mo5515a(a2);
                }
                if (a == null) {
                    return a2;
                }
                a.disconnect();
                return a2;
            } catch (Exception e3) {
                exception = e3;
            }
        } catch (Exception e32) {
            exception = e32;
            a = null;
            try {
                a2 = m5471b(a);
                if (a2 != null) {
                    try {
                        if (a2.m5501a() > 0) {
                            if (this.f4955c.mo5518a()) {
                                this.f4955c.mo5515a(a2);
                            }
                            if (a == null) {
                                return a2;
                            }
                            a.disconnect();
                            return a2;
                        }
                    } catch (Throwable th) {
                        c2150n = a2;
                        e2 = th;
                        if (this.f4955c.mo5518a()) {
                            this.f4955c.mo5515a(c2150n);
                        }
                        if (a != null) {
                            a.disconnect();
                        }
                        throw e2;
                    }
                }
                throw new C2149m(exception, a2);
            } catch (Exception e4) {
                exception.printStackTrace();
                if (null != null) {
                    if (c2150n2.m5501a() > 0) {
                        if (this.f4955c.mo5518a()) {
                            this.f4955c.mo5515a(null);
                        }
                        if (a != null) {
                            a.disconnect();
                        }
                        return null;
                    }
                }
                throw new C2149m(exception, c2150n2);
            } catch (Throwable th2) {
                e2 = th2;
                if (this.f4955c.mo5518a()) {
                    this.f4955c.mo5515a(c2150n);
                }
                if (a != null) {
                    a.disconnect();
                }
                throw e2;
            }
        } catch (Throwable th3) {
            e2 = th3;
            a = null;
            if (this.f4955c.mo5518a()) {
                this.f4955c.mo5515a(c2150n);
            }
            if (a != null) {
                a.disconnect();
            }
            throw e2;
        }
    }

    /* renamed from: a */
    public C2150n m5459a(String str, C2152p c2152p) {
        return m5469b(new C2146i(str, c2152p));
    }

    /* renamed from: a */
    public C2150n m5460a(String str, String str2, byte[] bArr) {
        return m5469b(new C2148k(str, null, str2, bArr));
    }

    /* renamed from: a */
    protected C2150n m5461a(HttpURLConnection httpURLConnection) {
        InputStream b;
        Throwable th;
        byte[] bArr = null;
        try {
            b = this.f4953a.mo5513b(httpURLConnection);
            if (b != null) {
                try {
                    bArr = this.f4953a.mo5512a(b);
                } catch (Throwable th2) {
                    th = th2;
                    if (b != null) {
                        try {
                            b.close();
                        } catch (Exception e) {
                        }
                    }
                    throw th;
                }
            }
            C2150n c2150n = new C2150n(httpURLConnection, bArr);
            if (b != null) {
                try {
                    b.close();
                } catch (Exception e2) {
                }
            }
            return c2150n;
        } catch (Throwable th3) {
            th = th3;
            b = null;
            if (b != null) {
                b.close();
            }
            throw th;
        }
    }

    /* renamed from: a */
    public C2152p m5462a() {
        return new C2152p();
    }

    /* renamed from: a */
    protected HttpURLConnection m5463a(String str) {
        try {
            URL url = new URL(str);
            return this.f4953a.mo5508a(str);
        } catch (Throwable e) {
            throw new IllegalArgumentException(str + " is not a valid URL", e);
        }
    }

    /* renamed from: a */
    protected void m5464a(C2145l c2145l, C2124b c2124b) {
        this.f4954b.mo5514a(this, c2124b).mo5519a(c2145l);
    }

    /* renamed from: a */
    public void m5465a(String str, C2152p c2152p, C2124b c2124b) {
        m5464a(new C2146i(str, c2152p), c2124b);
    }

    /* renamed from: a */
    protected void m5466a(HttpURLConnection httpURLConnection, C2147j c2147j, String str) {
        httpURLConnection.setConnectTimeout(this.f4956d);
        httpURLConnection.setReadTimeout(this.f4957e);
        this.f4953a.mo5510a(httpURLConnection, c2147j, str);
    }

    @AnyThread
    /* renamed from: a */
    public void m5467a(Set<String> set) {
        this.f4962m = set;
    }

    /* renamed from: a */
    protected boolean m5468a(Throwable th, long j) {
        long currentTimeMillis = (System.currentTimeMillis() - j) + 10;
        if (this.f4955c.mo5518a()) {
            this.f4955c.mo5516a("ELAPSED TIME = " + currentTimeMillis + ", CT = " + this.f4956d + ", RT = " + this.f4957e);
        }
        return this.f4960k ? currentTimeMillis >= ((long) this.f4957e) : currentTimeMillis >= ((long) this.f4956d);
    }

    /* renamed from: b */
    public C2150n m5469b(C2145l c2145l) {
        C2150n c2150n = null;
        try {
            c2150n = m5458a(c2145l.m5493a(), c2145l.m5494b(), c2145l.m5495c(), c2145l.m5496d());
        } catch (C2149m e) {
            this.f4953a.mo5511a(e);
        } catch (Exception e2) {
            this.f4953a.mo5511a(new C2149m(e2, c2150n));
        }
        return c2150n;
    }

    /* renamed from: b */
    public C2150n m5470b(String str, C2152p c2152p) {
        return m5469b(new C2148k(str, c2152p));
    }

    /* renamed from: b */
    protected C2150n m5471b(HttpURLConnection httpURLConnection) {
        Throwable th;
        byte[] bArr = null;
        InputStream errorStream;
        try {
            errorStream = httpURLConnection.getErrorStream();
            if (errorStream != null) {
                try {
                    bArr = this.f4953a.mo5512a(errorStream);
                } catch (Throwable th2) {
                    th = th2;
                    if (errorStream != null) {
                        try {
                            errorStream.close();
                        } catch (Exception e) {
                        }
                    }
                    throw th;
                }
            }
            C2150n c2150n = new C2150n(httpURLConnection, bArr);
            if (errorStream != null) {
                try {
                    errorStream.close();
                } catch (Exception e2) {
                }
            }
            return c2150n;
        } catch (Throwable th3) {
            th = th3;
            errorStream = null;
            if (errorStream != null) {
                errorStream.close();
            }
            throw th;
        }
    }

    /* renamed from: b */
    public void m5472b() {
        this.f4959i.clear();
    }

    @AnyThread
    /* renamed from: b */
    public void m5473b(int i) {
        if (i < 1 || i > 18) {
            throw new IllegalArgumentException("Maximum retries must be between 1 and 18");
        }
        this.f4958h = i;
    }

    /* renamed from: b */
    public void m5474b(String str, C2152p c2152p, C2124b c2124b) {
        m5464a(new C2148k(str, c2152p), c2124b);
    }

    @AnyThread
    /* renamed from: b */
    public void m5475b(Set<String> set) {
        this.f4961l = set;
    }

    @AnyThread
    /* renamed from: c */
    public void m5476c(int i) {
        this.f4956d = i;
    }

    @AnyThread
    /* renamed from: d */
    public void m5477d(int i) {
        this.f4957e = i;
    }
}
