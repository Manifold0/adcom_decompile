package com.applovin.impl.p016a;

import android.net.Uri;
import android.webkit.URLUtil;
import com.applovin.impl.sdk.gd;
import com.applovin.impl.sdk.gf;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Locale;

/* renamed from: com.applovin.impl.a.r */
public class C1245r {
    /* renamed from: a */
    private Uri f1665a;
    /* renamed from: b */
    private Uri f1666b;
    /* renamed from: c */
    private C1246s f1667c;
    /* renamed from: d */
    private String f1668d;
    /* renamed from: e */
    private int f1669e;
    /* renamed from: f */
    private int f1670f;
    /* renamed from: g */
    private int f1671g;

    private C1245r() {
    }

    /* renamed from: a */
    public static C1245r m1932a(gf gfVar, AppLovinSdk appLovinSdk) {
        if (gfVar == null) {
            throw new IllegalArgumentException("No node specified.");
        } else if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified.");
        } else {
            try {
                String c = gfVar.m2977c();
                if (URLUtil.isValidUrl(c)) {
                    Uri parse = Uri.parse(c);
                    C1245r c1245r = new C1245r();
                    c1245r.f1665a = parse;
                    c1245r.f1666b = parse;
                    c1245r.f1671g = gd.m2957e((String) gfVar.m2975b().get("bitrate"));
                    c1245r.f1667c = C1245r.m1933a((String) gfVar.m2975b().get("delivery"));
                    c1245r.f1670f = gd.m2957e((String) gfVar.m2975b().get("height"));
                    c1245r.f1669e = gd.m2957e((String) gfVar.m2975b().get("width"));
                    c1245r.f1668d = ((String) gfVar.m2975b().get("type")).toLowerCase(Locale.ENGLISH);
                    return c1245r;
                }
                appLovinSdk.getLogger().mo4173e("VastVideoFile", "Unable to create video file. Could not find URL.");
                return null;
            } catch (Throwable th) {
                appLovinSdk.getLogger().mo4174e("VastVideoFile", "Error occurred while initializing", th);
            }
        }
    }

    /* renamed from: a */
    private static C1246s m1933a(String str) {
        if (AppLovinSdkUtils.isValidString(str)) {
            if ("progressive".equalsIgnoreCase(str)) {
                return C1246s.Progressive;
            }
            if ("streaming".equalsIgnoreCase(str)) {
                return C1246s.Streaming;
            }
        }
        return C1246s.Progressive;
    }

    /* renamed from: a */
    public Uri m1934a() {
        return this.f1665a;
    }

    /* renamed from: a */
    public void m1935a(Uri uri) {
        this.f1666b = uri;
    }

    /* renamed from: b */
    public Uri m1936b() {
        return this.f1666b;
    }

    /* renamed from: c */
    public boolean m1937c() {
        return this.f1667c == C1246s.Streaming;
    }

    /* renamed from: d */
    public String m1938d() {
        return this.f1668d;
    }

    /* renamed from: e */
    public int m1939e() {
        return this.f1671g;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1245r)) {
            return false;
        }
        C1245r c1245r = (C1245r) obj;
        if (this.f1669e != c1245r.f1669e || this.f1670f != c1245r.f1670f || this.f1671g != c1245r.f1671g) {
            return false;
        }
        if (this.f1665a != null) {
            if (!this.f1665a.equals(c1245r.f1665a)) {
                return false;
            }
        } else if (c1245r.f1665a != null) {
            return false;
        }
        if (this.f1666b != null) {
            if (!this.f1666b.equals(c1245r.f1666b)) {
                return false;
            }
        } else if (c1245r.f1666b != null) {
            return false;
        }
        if (this.f1667c != c1245r.f1667c) {
            return false;
        }
        if (this.f1668d != null) {
            z = this.f1668d.equals(c1245r.f1668d);
        } else if (c1245r.f1668d != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f1667c != null ? this.f1667c.hashCode() : 0) + (((this.f1666b != null ? this.f1666b.hashCode() : 0) + ((this.f1665a != null ? this.f1665a.hashCode() : 0) * 31)) * 31)) * 31;
        if (this.f1668d != null) {
            i = this.f1668d.hashCode();
        }
        return ((((((hashCode + i) * 31) + this.f1669e) * 31) + this.f1670f) * 31) + this.f1671g;
    }

    public String toString() {
        return "VastVideoFile{sourceVideoUri=" + this.f1665a + ", videoUri=" + this.f1666b + ", deliveryType=" + this.f1667c + ", fileType='" + this.f1668d + '\'' + ", width=" + this.f1669e + ", height=" + this.f1670f + ", bitrate=" + this.f1671g + '}';
    }
}
