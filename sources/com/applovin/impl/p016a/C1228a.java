package com.applovin.impl.p016a;

import android.net.Uri;
import com.applovin.impl.sdk.aa;
import com.applovin.impl.sdk.an;
import com.applovin.impl.sdk.bu;
import com.applovin.impl.sdk.ee;
import com.applovin.impl.sdk.gd;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: com.applovin.impl.a.a */
public class C1228a extends an {
    /* renamed from: e */
    private final String f1579e;
    /* renamed from: f */
    private final String f1580f;
    /* renamed from: g */
    private final C1238k f1581g;
    /* renamed from: h */
    private final long f1582h;
    /* renamed from: i */
    private final C1242o f1583i;
    /* renamed from: j */
    private final C1233f f1584j;
    /* renamed from: k */
    private final Set<C1239l> f1585k;
    /* renamed from: l */
    private final Set<C1239l> f1586l;

    private C1228a(C1230c c1230c) {
        super(c1230c.f1587a, c1230c.f1588b, c1230c.f1589c);
        this.f1579e = c1230c.f1591e;
        this.f1581g = c1230c.f1593g;
        this.f1580f = c1230c.f1592f;
        this.f1583i = c1230c.f1594h;
        this.f1584j = c1230c.f1595i;
        this.f1585k = c1230c.f1596j;
        this.f1586l = c1230c.f1597k;
        this.f1582h = c1230c.f1590d;
    }

    /* renamed from: a */
    private Set<C1239l> m1832a(C1231d c1231d, String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            return Collections.emptySet();
        }
        Map d = (c1231d != C1231d.f1598a || this.f1583i == null) ? (c1231d != C1231d.COMPANION_AD || this.f1584j == null) ? null : this.f1584j.m1879d() : this.f1583i.m1929e();
        Set hashSet = new HashSet();
        if (!(d == null || d.isEmpty())) {
            for (Object obj : strArr) {
                if (d.containsKey(obj)) {
                    hashSet.addAll((Collection) d.get(obj));
                }
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }

    public static C1230c an() {
        return new C1230c();
    }

    private C1244q ao() {
        C1244q[] a = C1244q.m1931a();
        int Y = new ee(this.c).m2698Y();
        return (Y < 0 || Y >= a.length) ? C1244q.UNSPECIFIED : a[Y];
    }

    private Set<C1239l> ap() {
        return this.f1583i != null ? this.f1583i.m1928d() : Collections.emptySet();
    }

    private Set<C1239l> aq() {
        return this.f1584j != null ? this.f1584j.m1878c() : Collections.emptySet();
    }

    /* renamed from: E */
    public boolean mo3999E() {
        return mo4003g() != null;
    }

    /* renamed from: a */
    public C1242o mo4000a() {
        return this.f1583i;
    }

    /* renamed from: a */
    public String m1835a(String str) {
        try {
            String a = bu.m2389a(this.a, "vimp_url", "", this.c);
            if (AppLovinSdkUtils.isValidString(a)) {
                a = a.replace("{CLCODE}", gd.m2953c(mo3996n()));
                if (AppLovinSdkUtils.isValidString(str)) {
                    a = a.replace("{PLACEMENT}", gd.m2953c(str));
                } else {
                    a = a.replace("{PLACEMENT}", "");
                }
                return a.toString();
            }
        } catch (Throwable th) {
            this.c.getLogger().mo4174e("VastAd", "Unable to create VAST impression URL", th);
        }
        return "";
    }

    /* renamed from: a */
    public Set<C1239l> m1836a(C1232e c1232e, String str) {
        return m1837a(c1232e, new String[]{str});
    }

    /* renamed from: a */
    public Set<C1239l> m1837a(C1232e c1232e, String[] strArr) {
        this.c.getLogger().mo4172d("VastAd", "Retrieving trackers of type '" + c1232e + "' and events '" + strArr + "'...");
        if (c1232e == C1232e.IMPRESSION) {
            return this.f1585k;
        }
        if (c1232e == C1232e.VIDEO_CLICK) {
            return ap();
        }
        if (c1232e == C1232e.COMPANION_CLICK) {
            return aq();
        }
        if (c1232e == C1232e.f1604d) {
            return m1832a(C1231d.f1598a, strArr);
        }
        if (c1232e == C1232e.COMPANION) {
            return m1832a(C1231d.COMPANION_AD, strArr);
        }
        if (c1232e == C1232e.ERROR) {
            return this.f1586l;
        }
        this.c.getLogger().mo4173e("VastAd", "Failed to retrieve trackers of invalid type '" + c1232e + "' and events '" + strArr + "'");
        return Collections.emptySet();
    }

    /* renamed from: a */
    public boolean m1838a(AppLovinSdk appLovinSdk) {
        return bu.m2387a(this.a, "cache_companion_ad", Boolean.valueOf(true), appLovinSdk).booleanValue();
    }

    /* renamed from: b */
    public boolean mo4001b() {
        C1245r c = m1841c();
        return c != null ? c.m1937c() : false;
    }

    /* renamed from: b */
    public boolean m1840b(AppLovinSdk appLovinSdk) {
        return bu.m2387a(this.a, "cache_video", Boolean.valueOf(true), appLovinSdk).booleanValue();
    }

    /* renamed from: c */
    public C1245r m1841c() {
        return this.f1583i != null ? this.f1583i.m1924a(ao()) : null;
    }

    /* renamed from: c */
    public void m1842c(String str) {
        try {
            synchronized (this.d) {
                this.a.put("html_template", str);
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: d */
    public Uri mo4002d() {
        C1245r c = m1841c();
        return c != null ? c.m1936b() : null;
    }

    /* renamed from: e */
    public C1233f m1844e() {
        return this.f1584j;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1228a) || !super.equals(obj)) {
            return false;
        }
        C1228a c1228a = (C1228a) obj;
        if (this.f1579e != null) {
            if (!this.f1579e.equals(c1228a.f1579e)) {
                return false;
            }
        } else if (c1228a.f1579e != null) {
            return false;
        }
        if (this.f1580f != null) {
            if (!this.f1580f.equals(c1228a.f1580f)) {
                return false;
            }
        } else if (c1228a.f1580f != null) {
            return false;
        }
        if (this.f1581g != null) {
            if (!this.f1581g.equals(c1228a.f1581g)) {
                return false;
            }
        } else if (c1228a.f1581g != null) {
            return false;
        }
        if (this.f1583i != null) {
            if (!this.f1583i.equals(c1228a.f1583i)) {
                return false;
            }
        } else if (c1228a.f1583i != null) {
            return false;
        }
        if (this.f1584j != null) {
            if (!this.f1584j.equals(c1228a.f1584j)) {
                return false;
            }
        } else if (c1228a.f1584j != null) {
            return false;
        }
        if (this.f1585k != null) {
            if (!this.f1585k.equals(c1228a.f1585k)) {
                return false;
            }
        } else if (c1228a.f1585k != null) {
            return false;
        }
        if (this.f1586l != null) {
            z = this.f1586l.equals(c1228a.f1586l);
        } else if (c1228a.f1586l != null) {
            z = false;
        }
        return z;
    }

    /* renamed from: f */
    public boolean mo3992f() {
        if (this.f1583i == null) {
            return false;
        }
        List a = this.f1583i.m1925a();
        return a != null && a.size() > 0;
    }

    /* renamed from: g */
    public Uri mo4003g() {
        return this.f1583i != null ? this.f1583i.m1927c() : null;
    }

    /* renamed from: h */
    public List<String> m1847h() {
        return aa.m2193a(bu.m2389a(this.a, "vast_resource_cache_prefix", null, this.c));
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f1585k != null ? this.f1585k.hashCode() : 0) + (((this.f1584j != null ? this.f1584j.hashCode() : 0) + (((this.f1583i != null ? this.f1583i.hashCode() : 0) + (((this.f1581g != null ? this.f1581g.hashCode() : 0) + (((this.f1580f != null ? this.f1580f.hashCode() : 0) + (((this.f1579e != null ? this.f1579e.hashCode() : 0) + (super.hashCode() * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (this.f1586l != null) {
            i = this.f1586l.hashCode();
        }
        return hashCode + i;
    }

    /* renamed from: i */
    public boolean m1848i() {
        return bu.m2387a(this.a, "vast_fire_click_trackers_on_html_clicks", Boolean.valueOf(false), this.c).booleanValue();
    }

    /* renamed from: j */
    public String m1849j() {
        return bu.m2389a(this.a, "html_template", "", this.c);
    }

    /* renamed from: k */
    public Uri m1850k() {
        String a = bu.m2389a(this.a, "html_template_url", null, this.c);
        return AppLovinSdkUtils.isValidString(a) ? Uri.parse(a) : null;
    }

    /* renamed from: l */
    public long mo3994l() {
        return this.f1582h;
    }

    public String toString() {
        return "VastAd{title='" + this.f1579e + '\'' + ", adDescription='" + this.f1580f + '\'' + ", systemInfo=" + this.f1581g + ", videoCreative=" + this.f1583i + ", companionAd=" + this.f1584j + ", impressionTrackers=" + this.f1585k + ", errorTrackers=" + this.f1586l + '}';
    }
}
