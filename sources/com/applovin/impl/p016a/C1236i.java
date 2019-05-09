package com.applovin.impl.p016a;

import android.net.Uri;
import android.webkit.URLUtil;
import com.applovin.impl.sdk.gf;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;

/* renamed from: com.applovin.impl.a.i */
public class C1236i {
    /* renamed from: a */
    private C1237j f1635a;
    /* renamed from: b */
    private Uri f1636b;
    /* renamed from: c */
    private String f1637c;

    private C1236i() {
    }

    /* renamed from: a */
    static C1236i m1889a(gf gfVar, C1236i c1236i, AppLovinSdk appLovinSdk) {
        if (gfVar == null) {
            throw new IllegalArgumentException("No node specified.");
        } else if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified.");
        } else {
            C1236i c1236i2 = c1236i != null ? c1236i : new C1236i();
            try {
                if (c1236i2.f1636b != null || AppLovinSdkUtils.isValidString(c1236i2.f1637c)) {
                    return c1236i2;
                }
                String a = C1236i.m1890a(gfVar, "StaticResource");
                if (URLUtil.isValidUrl(a)) {
                    c1236i2.f1636b = Uri.parse(a);
                    c1236i2.f1635a = C1237j.STATIC;
                    return c1236i2;
                }
                a = C1236i.m1890a(gfVar, "IFrameResource");
                if (AppLovinSdkUtils.isValidString(a)) {
                    c1236i2.f1635a = C1237j.IFRAME;
                    if (URLUtil.isValidUrl(a)) {
                        c1236i2.f1636b = Uri.parse(a);
                        return c1236i2;
                    }
                    c1236i2.f1637c = a;
                    return c1236i2;
                }
                a = C1236i.m1890a(gfVar, "HTMLResource");
                if (!AppLovinSdkUtils.isValidString(a)) {
                    return c1236i2;
                }
                c1236i2.f1635a = C1237j.HTML;
                if (URLUtil.isValidUrl(a)) {
                    c1236i2.f1636b = Uri.parse(a);
                    return c1236i2;
                }
                c1236i2.f1637c = a;
                return c1236i2;
            } catch (Throwable th) {
                appLovinSdk.getLogger().mo4174e("VastNonVideoResource", "Error occurred while initializing", th);
                return null;
            }
        }
    }

    /* renamed from: a */
    private static String m1890a(gf gfVar, String str) {
        gf b = gfVar.m2974b(str);
        return b != null ? b.m2977c() : null;
    }

    /* renamed from: a */
    public C1237j m1891a() {
        return this.f1635a;
    }

    /* renamed from: a */
    public void m1892a(Uri uri) {
        this.f1636b = uri;
    }

    /* renamed from: a */
    public void m1893a(String str) {
        this.f1637c = str;
    }

    /* renamed from: b */
    public Uri m1894b() {
        return this.f1636b;
    }

    /* renamed from: c */
    public String m1895c() {
        return this.f1637c;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1236i)) {
            return false;
        }
        C1236i c1236i = (C1236i) obj;
        if (this.f1635a != c1236i.f1635a) {
            return false;
        }
        if (this.f1636b != null) {
            if (!this.f1636b.equals(c1236i.f1636b)) {
                return false;
            }
        } else if (c1236i.f1636b != null) {
            return false;
        }
        if (this.f1637c != null) {
            z = this.f1637c.equals(c1236i.f1637c);
        } else if (c1236i.f1637c != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f1636b != null ? this.f1636b.hashCode() : 0) + ((this.f1635a != null ? this.f1635a.hashCode() : 0) * 31)) * 31;
        if (this.f1637c != null) {
            i = this.f1637c.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "VastNonVideoResource{type=" + this.f1635a + ", resourceUri=" + this.f1636b + ", resourceContents='" + this.f1637c + '\'' + '}';
    }
}
