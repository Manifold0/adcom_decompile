package com.applovin.impl.p016a;

import com.applovin.impl.sdk.aa;
import com.applovin.impl.sdk.gd;
import com.applovin.impl.sdk.gf;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.tapjoy.TJAdUnitConstants.String;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* renamed from: com.applovin.impl.a.l */
public class C1239l {
    /* renamed from: a */
    private String f1645a;
    /* renamed from: b */
    private String f1646b;
    /* renamed from: c */
    private String f1647c;
    /* renamed from: d */
    private long f1648d = -1;
    /* renamed from: e */
    private int f1649e = -1;

    private C1239l() {
    }

    /* renamed from: a */
    private static int m1897a(String str, C1234g c1234g) {
        return String.VIDEO_START.equalsIgnoreCase(str) ? 0 : String.VIDEO_FIRST_QUARTILE.equalsIgnoreCase(str) ? 25 : String.VIDEO_MIDPOINT.equalsIgnoreCase(str) ? 50 : String.VIDEO_THIRD_QUARTILE.equalsIgnoreCase(str) ? 75 : String.VIDEO_COMPLETE.equalsIgnoreCase(str) ? c1234g != null ? c1234g.m1887h() : 95 : -1;
    }

    /* renamed from: a */
    public static C1239l m1898a(gf gfVar, C1234g c1234g, AppLovinSdk appLovinSdk) {
        if (gfVar == null) {
            throw new IllegalArgumentException("No node specified.");
        } else if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified.");
        } else {
            try {
                String c = gfVar.m2977c();
                if (AppLovinSdkUtils.isValidString(c)) {
                    C1239l c1239l = new C1239l();
                    c1239l.f1647c = c;
                    c1239l.f1645a = (String) gfVar.m2975b().get("id");
                    c1239l.f1646b = (String) gfVar.m2975b().get("event");
                    c1239l.f1649e = C1239l.m1897a(c1239l.m1899a(), c1234g);
                    c = (String) gfVar.m2975b().get("offset");
                    if (AppLovinSdkUtils.isValidString(c)) {
                        c = c.trim();
                        if (c.contains("%")) {
                            c1239l.f1649e = gd.m2957e(c.substring(0, c.length() - 1));
                        } else if (c.contains(":")) {
                            List a = aa.m2194a(c, ":");
                            int size = a.size();
                            if (size > 0) {
                                long j = 0;
                                int i = size - 1;
                                while (i >= 0) {
                                    long j2;
                                    c = (String) a.get(i);
                                    if (gd.m2956d(c)) {
                                        int parseInt = Integer.parseInt(c);
                                        if (i == size - 1) {
                                            j2 = ((long) parseInt) + j;
                                        } else if (i == size - 2) {
                                            j2 = TimeUnit.MINUTES.toSeconds((long) parseInt) + j;
                                        } else if (i == size - 3) {
                                            j2 = TimeUnit.HOURS.toSeconds((long) parseInt) + j;
                                        }
                                        i--;
                                        j = j2;
                                    }
                                    j2 = j;
                                    i--;
                                    j = j2;
                                }
                                c1239l.f1648d = j;
                                c1239l.f1649e = -1;
                            }
                        } else {
                            appLovinSdk.getLogger().mo4173e("VastTracker", "Unable to parse time offset from rawOffsetString = " + c);
                        }
                    }
                    return c1239l;
                }
                appLovinSdk.getLogger().mo4173e("VastTracker", "Unable to create tracker. Could not find URL.");
                return null;
            } catch (Throwable th) {
                appLovinSdk.getLogger().mo4174e("VastTracker", "Error occurred while initializing", th);
            }
        }
    }

    /* renamed from: a */
    public String m1899a() {
        return this.f1646b;
    }

    /* renamed from: a */
    public boolean m1900a(long j, int i) {
        return (((this.f1648d > 0 ? 1 : (this.f1648d == 0 ? 0 : -1)) >= 0) && (j >= this.f1648d)) ? true : (this.f1649e >= 0) && (i >= this.f1649e);
    }

    /* renamed from: b */
    public String m1901b() {
        return this.f1647c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1239l)) {
            return false;
        }
        C1239l c1239l = (C1239l) obj;
        if (this.f1648d != c1239l.f1648d || this.f1649e != c1239l.f1649e) {
            return false;
        }
        if (this.f1645a != null) {
            if (!this.f1645a.equals(c1239l.f1645a)) {
                return false;
            }
        } else if (c1239l.f1645a != null) {
            return false;
        }
        if (this.f1646b != null) {
            if (!this.f1646b.equals(c1239l.f1646b)) {
                return false;
            }
        } else if (c1239l.f1646b != null) {
            return false;
        }
        return this.f1647c.equals(c1239l.f1647c);
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.f1645a != null ? this.f1645a.hashCode() : 0) * 31;
        if (this.f1646b != null) {
            i = this.f1646b.hashCode();
        }
        return ((((((hashCode + i) * 31) + this.f1647c.hashCode()) * 31) + ((int) (this.f1648d ^ (this.f1648d >>> 32)))) * 31) + this.f1649e;
    }

    public String toString() {
        return "VastTracker{identifier='" + this.f1645a + '\'' + ", event='" + this.f1646b + '\'' + ", uriString='" + this.f1647c + '\'' + ", offsetSeconds=" + this.f1648d + ", offsetPercent=" + this.f1649e + '}';
    }
}
