package com.applovin.impl.p016a;

import android.net.Uri;
import com.applovin.impl.sdk.gd;
import com.applovin.impl.sdk.gf;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* renamed from: com.applovin.impl.a.f */
public class C1233f {
    /* renamed from: a */
    private int f1608a;
    /* renamed from: b */
    private int f1609b;
    /* renamed from: c */
    private Uri f1610c;
    /* renamed from: d */
    private C1236i f1611d;
    /* renamed from: e */
    private Set<C1239l> f1612e = new HashSet();
    /* renamed from: f */
    private Map<String, Set<C1239l>> f1613f = new HashMap();

    private C1233f() {
    }

    /* renamed from: a */
    public static C1233f m1875a(gf gfVar, C1233f c1233f, C1234g c1234g, AppLovinSdk appLovinSdk) {
        if (gfVar == null) {
            throw new IllegalArgumentException("No node specified.");
        } else if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified.");
        } else {
            C1233f c1233f2 = c1233f != null ? c1233f : new C1233f();
            try {
                if (c1233f2.f1608a == 0 && c1233f2.f1609b == 0) {
                    int e = gd.m2957e((String) gfVar.m2975b().get("width"));
                    int e2 = gd.m2957e((String) gfVar.m2975b().get("height"));
                    if (e > 0 && e2 > 0) {
                        c1233f2.f1608a = e;
                        c1233f2.f1609b = e2;
                    }
                }
                c1233f2.f1611d = C1236i.m1889a(gfVar, c1233f2.f1611d, appLovinSdk);
                if (c1233f2.f1610c == null) {
                    gf b = gfVar.m2974b("CompanionClickThrough");
                    if (b != null) {
                        String c = b.m2977c();
                        if (AppLovinSdkUtils.isValidString(c)) {
                            c1233f2.f1610c = Uri.parse(c);
                        }
                    }
                }
                C1241n.m1912a(gfVar.m2973a("CompanionClickTracking"), c1233f2.f1612e, c1234g, appLovinSdk);
                C1241n.m1911a(gfVar, c1233f2.f1613f, c1234g, appLovinSdk);
                return c1233f2;
            } catch (Throwable th) {
                appLovinSdk.getLogger().mo4174e("VastCompanionAd", "Error occurred while initializing", th);
                return null;
            }
        }
    }

    /* renamed from: a */
    public Uri m1876a() {
        return this.f1610c;
    }

    /* renamed from: b */
    public C1236i m1877b() {
        return this.f1611d;
    }

    /* renamed from: c */
    public Set<C1239l> m1878c() {
        return this.f1612e;
    }

    /* renamed from: d */
    public Map<String, Set<C1239l>> m1879d() {
        return this.f1613f;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1233f)) {
            return false;
        }
        C1233f c1233f = (C1233f) obj;
        if (this.f1608a != c1233f.f1608a || this.f1609b != c1233f.f1609b) {
            return false;
        }
        if (this.f1610c != null) {
            if (!this.f1610c.equals(c1233f.f1610c)) {
                return false;
            }
        } else if (c1233f.f1610c != null) {
            return false;
        }
        if (this.f1611d != null) {
            if (!this.f1611d.equals(c1233f.f1611d)) {
                return false;
            }
        } else if (c1233f.f1611d != null) {
            return false;
        }
        if (this.f1612e != null) {
            if (!this.f1612e.equals(c1233f.f1612e)) {
                return false;
            }
        } else if (c1233f.f1612e != null) {
            return false;
        }
        if (this.f1613f != null) {
            z = this.f1613f.equals(c1233f.f1613f);
        } else if (c1233f.f1613f != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f1612e != null ? this.f1612e.hashCode() : 0) + (((this.f1611d != null ? this.f1611d.hashCode() : 0) + (((this.f1610c != null ? this.f1610c.hashCode() : 0) + (((this.f1608a * 31) + this.f1609b) * 31)) * 31)) * 31)) * 31;
        if (this.f1613f != null) {
            i = this.f1613f.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "VastCompanionAd{width=" + this.f1608a + ", height=" + this.f1609b + ", destinationUri=" + this.f1610c + ", nonVideoResource=" + this.f1611d + ", clickTrackers=" + this.f1612e + ", eventTrackers=" + this.f1613f + '}';
    }
}
