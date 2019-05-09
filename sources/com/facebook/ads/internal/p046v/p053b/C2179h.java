package com.facebook.ads.internal.p046v.p053b;

import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* renamed from: com.facebook.ads.internal.v.b.h */
public class C2179h implements C2178n {
    /* renamed from: a */
    public final String f5034a;
    /* renamed from: b */
    private HttpURLConnection f5035b;
    /* renamed from: c */
    private InputStream f5036c;
    /* renamed from: d */
    private volatile int f5037d;
    /* renamed from: e */
    private volatile String f5038e;

    public C2179h(C2179h c2179h) {
        this.f5037d = Integer.MIN_VALUE;
        this.f5034a = c2179h.f5034a;
        this.f5038e = c2179h.f5038e;
        this.f5037d = c2179h.f5037d;
    }

    public C2179h(String str) {
        MimeTypeMap singleton = MimeTypeMap.getSingleton();
        Object fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
        this(str, TextUtils.isEmpty(fileExtensionFromUrl) ? null : singleton.getMimeTypeFromExtension(fileExtensionFromUrl));
    }

    public C2179h(String str, String str2) {
        this.f5037d = Integer.MIN_VALUE;
        this.f5034a = (String) C2182j.m5591a(str);
        this.f5038e = str2;
    }

    /* renamed from: a */
    private HttpURLConnection m5584a(int i, int i2) {
        HttpURLConnection httpURLConnection;
        String str = this.f5034a;
        int i3 = 0;
        Object obj;
        do {
            Log.d("ProxyCache", "Open connection " + (i > 0 ? " with offset " + i : "") + " to " + str);
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            if (i > 0) {
                httpURLConnection.setRequestProperty("Range", "bytes=" + i + "-");
            }
            if (i2 > 0) {
                httpURLConnection.setConnectTimeout(i2);
                httpURLConnection.setReadTimeout(i2);
            }
            int responseCode = httpURLConnection.getResponseCode();
            obj = (responseCode == 301 || responseCode == IronSourceConstants.OFFERWALL_AVAILABLE || responseCode == 303) ? 1 : null;
            if (obj != null) {
                str = httpURLConnection.getHeaderField("Location");
                i3++;
                httpURLConnection.disconnect();
            }
            if (i3 > 5) {
                throw new C2180l("Too many redirects: " + i3);
            }
        } while (obj != null);
        return httpURLConnection;
    }

    /* renamed from: d */
    private void m5585d() {
        HttpURLConnection a;
        Throwable e;
        Closeable closeable = null;
        Log.d("ProxyCache", "Read content info from " + this.f5034a);
        try {
            a = m5584a(0, 10000);
            try {
                this.f5037d = a.getContentLength();
                this.f5038e = a.getContentType();
                closeable = a.getInputStream();
                Log.i("ProxyCache", "Content info for `" + this.f5034a + "`: mime: " + this.f5038e + ", content-length: " + this.f5037d);
                C2185m.m5594a(closeable);
                if (a != null) {
                    a.disconnect();
                }
            } catch (IOException e2) {
                e = e2;
                try {
                    Log.e("ProxyCache", "Error fetching info from " + this.f5034a, e);
                    C2185m.m5594a(closeable);
                    if (a != null) {
                        a.disconnect();
                    }
                } catch (Throwable th) {
                    e = th;
                    C2185m.m5594a(closeable);
                    if (a != null) {
                        a.disconnect();
                    }
                    throw e;
                }
            }
        } catch (IOException e3) {
            e = e3;
            a = null;
            Log.e("ProxyCache", "Error fetching info from " + this.f5034a, e);
            C2185m.m5594a(closeable);
            if (a != null) {
                a.disconnect();
            }
        } catch (Throwable th2) {
            e = th2;
            a = null;
            C2185m.m5594a(closeable);
            if (a != null) {
                a.disconnect();
            }
            throw e;
        }
    }

    /* renamed from: a */
    public synchronized int mo5531a() {
        if (this.f5037d == Integer.MIN_VALUE) {
            m5585d();
        }
        return this.f5037d;
    }

    /* renamed from: a */
    public int mo5532a(byte[] bArr) {
        if (this.f5036c == null) {
            throw new C2180l("Error reading data from " + this.f5034a + ": connection is absent!");
        }
        try {
            return this.f5036c.read(bArr, 0, bArr.length);
        } catch (Throwable e) {
            throw new C2181i("Reading source " + this.f5034a + " is interrupted", e);
        } catch (Throwable e2) {
            throw new C2180l("Error reading data from " + this.f5034a, e2);
        }
    }

    /* renamed from: a */
    public void mo5533a(int i) {
        try {
            this.f5035b = m5584a(i, -1);
            this.f5038e = this.f5035b.getContentType();
            this.f5036c = new BufferedInputStream(this.f5035b.getInputStream(), 8192);
            HttpURLConnection httpURLConnection = this.f5035b;
            int responseCode = this.f5035b.getResponseCode();
            int contentLength = httpURLConnection.getContentLength();
            if (responseCode != 200) {
                contentLength = responseCode == 206 ? contentLength + i : this.f5037d;
            }
            this.f5037d = contentLength;
        } catch (Throwable e) {
            throw new C2180l("Error opening connection for " + this.f5034a + " with offset " + i, e);
        }
    }

    /* renamed from: b */
    public void mo5534b() {
        if (this.f5035b != null) {
            try {
                this.f5035b.disconnect();
            } catch (Throwable e) {
                throw new C2180l("Error disconnecting HttpUrlConnection", e);
            }
        }
    }

    /* renamed from: c */
    public synchronized String m5590c() {
        if (TextUtils.isEmpty(this.f5038e)) {
            m5585d();
        }
        return this.f5038e;
    }

    public String toString() {
        return "HttpUrlSource{url='" + this.f5034a + "}";
    }
}
