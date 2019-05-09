package com.applovin.impl.p016a;

import android.net.Uri;
import android.webkit.MimeTypeMap;
import com.applovin.impl.sdk.aa;
import com.applovin.impl.sdk.ee;
import com.applovin.impl.sdk.gd;
import com.applovin.impl.sdk.gf;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* renamed from: com.applovin.impl.a.o */
public class C1242o {
    /* renamed from: a */
    private List<C1245r> f1653a = Collections.EMPTY_LIST;
    /* renamed from: b */
    private List<String> f1654b = Collections.EMPTY_LIST;
    /* renamed from: c */
    private int f1655c;
    /* renamed from: d */
    private Uri f1656d;
    /* renamed from: e */
    private final Set<C1239l> f1657e = new HashSet();
    /* renamed from: f */
    private final Map<String, Set<C1239l>> f1658f = new HashMap();

    private C1242o() {
    }

    private C1242o(C1234g c1234g) {
        this.f1654b = c1234g.m1886g();
    }

    /* renamed from: a */
    private static int m1921a(String str, AppLovinSdk appLovinSdk) {
        try {
            List a = aa.m2194a(str, ":");
            if (a.size() == 3) {
                return (int) (((long) gd.m2957e((String) a.get(2))) + (TimeUnit.HOURS.toSeconds((long) gd.m2957e((String) a.get(0))) + TimeUnit.MINUTES.toSeconds((long) gd.m2957e((String) a.get(1)))));
            }
        } catch (Throwable th) {
            appLovinSdk.getLogger().mo4173e("VastVideoCreative", "Unable to parse duration from \"" + str + "\"");
        }
        return 0;
    }

    /* renamed from: a */
    public static C1242o m1922a(gf gfVar, C1242o c1242o, C1234g c1234g, AppLovinSdk appLovinSdk) {
        if (gfVar == null) {
            throw new IllegalArgumentException("No node specified.");
        } else if (c1234g == null) {
            throw new IllegalArgumentException("No context specified.");
        } else if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified.");
        } else {
            C1242o c1242o2 = c1242o != null ? c1242o : new C1242o(c1234g);
            try {
                gf b;
                if (c1242o2.f1655c == 0) {
                    b = gfVar.m2974b("Duration");
                    if (b != null) {
                        int a = C1242o.m1921a(b.m2977c(), appLovinSdk);
                        if (a > 0) {
                            c1242o2.f1655c = a;
                        }
                    }
                }
                b = gfVar.m2974b("MediaFiles");
                if (b != null) {
                    List a2 = C1242o.m1923a(b, appLovinSdk);
                    if (a2 != null && a2.size() > 0) {
                        if (c1242o2.f1653a != null) {
                            a2.addAll(c1242o2.f1653a);
                        }
                        c1242o2.f1653a = a2;
                    }
                }
                b = gfVar.m2974b("VideoClicks");
                if (b != null) {
                    if (c1242o2.f1656d == null) {
                        gf b2 = b.m2974b("ClickThrough");
                        if (b2 != null) {
                            String c = b2.m2977c();
                            if (AppLovinSdkUtils.isValidString(c)) {
                                c1242o2.f1656d = Uri.parse(c);
                            }
                        }
                    }
                    C1241n.m1912a(b.m2973a("ClickTracking"), c1242o2.f1657e, c1234g, appLovinSdk);
                }
                C1241n.m1911a(gfVar, c1242o2.f1658f, c1234g, appLovinSdk);
                return c1242o2;
            } catch (Throwable th) {
                appLovinSdk.getLogger().mo4174e("VastVideoCreative", "Error occurred while initializing", th);
                return null;
            }
        }
    }

    /* renamed from: a */
    private static List<C1245r> m1923a(gf gfVar, AppLovinSdk appLovinSdk) {
        List<gf> a = gfVar.m2973a("MediaFile");
        List<C1245r> arrayList = new ArrayList(a.size());
        ee eeVar = new ee(appLovinSdk);
        List a2 = aa.m2193a(eeVar.aa());
        List a3 = aa.m2193a(eeVar.ab());
        for (gf a4 : a) {
            C1245r a5 = C1245r.m1932a(a4, appLovinSdk);
            if (a5 != null) {
                try {
                    String d = a5.m1938d();
                    if (!AppLovinSdkUtils.isValidString(d) || a2.contains(d)) {
                        if (eeVar.ac()) {
                            d = MimeTypeMap.getFileExtensionFromUrl(a5.m1936b().toString());
                            if (AppLovinSdkUtils.isValidString(d) && !a3.contains(d)) {
                                arrayList.add(a5);
                            }
                        }
                        appLovinSdk.getLogger().mo4178w("VastVideoCreative", "Video file not supported: " + a5);
                    } else {
                        arrayList.add(a5);
                    }
                } catch (Throwable th) {
                    appLovinSdk.getLogger().mo4174e("VastVideoCreative", "Failed to validate vidoe file: " + a5, th);
                }
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public C1245r m1924a(C1244q c1244q) {
        if (this.f1653a == null || this.f1653a.size() == 0) {
            return null;
        }
        List arrayList = new ArrayList(3);
        for (String str : this.f1654b) {
            for (C1245r c1245r : this.f1653a) {
                String d = c1245r.m1938d();
                if (AppLovinSdkUtils.isValidString(d) && str.equalsIgnoreCase(d)) {
                    arrayList.add(c1245r);
                }
            }
            if (!arrayList.isEmpty()) {
                break;
            }
        }
        List list = !arrayList.isEmpty() ? arrayList : this.f1653a;
        Collections.sort(list, new C1243p(this));
        return c1244q == C1244q.LOW ? (C1245r) list.get(0) : c1244q == C1244q.MEDIUM ? (C1245r) list.get(list.size() / 2) : (C1245r) list.get(list.size() - 1);
    }

    /* renamed from: a */
    public List<C1245r> m1925a() {
        return this.f1653a;
    }

    /* renamed from: b */
    public int m1926b() {
        return this.f1655c;
    }

    /* renamed from: c */
    public Uri m1927c() {
        return this.f1656d;
    }

    /* renamed from: d */
    public Set<C1239l> m1928d() {
        return this.f1657e;
    }

    /* renamed from: e */
    public Map<String, Set<C1239l>> m1929e() {
        return this.f1658f;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1242o)) {
            return false;
        }
        C1242o c1242o = (C1242o) obj;
        if (this.f1655c != c1242o.f1655c) {
            return false;
        }
        if (this.f1653a != null) {
            if (!this.f1653a.equals(c1242o.f1653a)) {
                return false;
            }
        } else if (c1242o.f1653a != null) {
            return false;
        }
        if (this.f1656d != null) {
            if (!this.f1656d.equals(c1242o.f1656d)) {
                return false;
            }
        } else if (c1242o.f1656d != null) {
            return false;
        }
        if (this.f1657e != null) {
            if (!this.f1657e.equals(c1242o.f1657e)) {
                return false;
            }
        } else if (c1242o.f1657e != null) {
            return false;
        }
        if (this.f1658f != null) {
            z = this.f1658f.equals(c1242o.f1658f);
        } else if (c1242o.f1658f != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f1657e != null ? this.f1657e.hashCode() : 0) + (((this.f1656d != null ? this.f1656d.hashCode() : 0) + ((((this.f1653a != null ? this.f1653a.hashCode() : 0) * 31) + this.f1655c) * 31)) * 31)) * 31;
        if (this.f1658f != null) {
            i = this.f1658f.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "VastVideoCreative{videoFiles=" + this.f1653a + ", durationSeconds=" + this.f1655c + ", destinationUri=" + this.f1656d + ", clickTrackers=" + this.f1657e + ", eventTrackers=" + this.f1658f + '}';
    }
}
