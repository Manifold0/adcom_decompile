package com.applovin.impl.sdk;

import android.text.TextUtils;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinLogger;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.applovin.impl.sdk.n */
public final class C1287n {
    /* renamed from: a */
    private static final Map<String, C1287n> f2597a = new HashMap();
    /* renamed from: b */
    private static final Object f2598b = new Object();
    /* renamed from: c */
    private AppLovinSdkImpl f2599c;
    /* renamed from: d */
    private AppLovinLogger f2600d;
    /* renamed from: e */
    private JSONObject f2601e;
    /* renamed from: f */
    private final String f2602f;
    /* renamed from: g */
    private String f2603g;
    /* renamed from: h */
    private AppLovinAdSize f2604h;
    /* renamed from: i */
    private AppLovinAdType f2605i;
    /* renamed from: j */
    private C1288o f2606j;

    private C1287n(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType, C1288o c1288o, String str, AppLovinSdkImpl appLovinSdkImpl) {
        if (TextUtils.isEmpty(str) && (appLovinAdType == null || appLovinAdSize == null || c1288o == C1288o.NONE)) {
            throw new IllegalArgumentException("No zone identifier or type/size/mediation type specified");
        }
        this.f2599c = appLovinSdkImpl;
        this.f2600d = appLovinSdkImpl != null ? appLovinSdkImpl.getLogger() : null;
        this.f2604h = appLovinAdSize;
        this.f2605i = appLovinAdType;
        this.f2606j = c1288o;
        if (TextUtils.isEmpty(str)) {
            this.f2602f = (appLovinAdSize.getLabel() + "_" + appLovinAdType.getLabel() + "_" + c1288o.toString()).toLowerCase(Locale.ENGLISH);
            return;
        }
        this.f2602f = str.toLowerCase(Locale.ENGLISH);
        this.f2603g = str.toLowerCase(Locale.ENGLISH);
    }

    /* renamed from: a */
    private ec m3034a(String str, ec ecVar) {
        return this.f2599c.retrieveSetting(str + this.f2602f, ecVar);
    }

    /* renamed from: a */
    public static C1287n m3035a(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType, C1288o c1288o, AppLovinSdkImpl appLovinSdkImpl) {
        return C1287n.m3036a(appLovinAdSize, appLovinAdType, c1288o, null, appLovinSdkImpl);
    }

    /* renamed from: a */
    public static C1287n m3036a(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType, C1288o c1288o, String str, AppLovinSdkImpl appLovinSdkImpl) {
        C1287n c1287n = new C1287n(appLovinAdSize, appLovinAdType, c1288o, str, appLovinSdkImpl);
        synchronized (f2598b) {
            String str2 = c1287n.f2602f;
            if (f2597a.containsKey(str2)) {
                c1287n = (C1287n) f2597a.get(str2);
            } else {
                f2597a.put(str2, c1287n);
            }
        }
        return c1287n;
    }

    /* renamed from: a */
    public static C1287n m3037a(String str, AppLovinSdkImpl appLovinSdkImpl) {
        return C1287n.m3036a(null, null, C1288o.NONE, str, appLovinSdkImpl);
    }

    /* renamed from: a */
    public static C1287n m3038a(String str, JSONObject jSONObject, AppLovinSdkImpl appLovinSdkImpl) {
        C1287n a = C1287n.m3037a(str, appLovinSdkImpl);
        a.f2601e = jSONObject;
        return a;
    }

    /* renamed from: a */
    private boolean m3039a(ec<String> ecVar, AppLovinAdSize appLovinAdSize) {
        return ((String) this.f2599c.get((ec) ecVar)).toUpperCase(Locale.ENGLISH).contains(appLovinAdSize.getLabel());
    }

    /* renamed from: b */
    public static C1287n m3040b(String str, AppLovinSdkImpl appLovinSdkImpl) {
        return C1287n.m3036a(AppLovinAdSize.NATIVE, AppLovinAdType.NATIVE, C1288o.DIRECT, str, appLovinSdkImpl);
    }

    /* renamed from: b */
    public static Collection<C1287n> m3041b(AppLovinSdkImpl appLovinSdkImpl) {
        Object linkedHashSet = new LinkedHashSet(8);
        Collections.addAll(linkedHashSet, new C1287n[]{C1287n.m3042c(appLovinSdkImpl), C1287n.m3043d(appLovinSdkImpl), C1287n.m3044e(appLovinSdkImpl), C1287n.m3045f(appLovinSdkImpl), C1287n.m3046g(appLovinSdkImpl), C1287n.m3047h(appLovinSdkImpl), C1287n.m3048i(appLovinSdkImpl), C1287n.m3049j(appLovinSdkImpl)});
        return Collections.unmodifiableSet(linkedHashSet);
    }

    /* renamed from: c */
    public static C1287n m3042c(AppLovinSdkImpl appLovinSdkImpl) {
        return C1287n.m3035a(AppLovinAdSize.BANNER, AppLovinAdType.REGULAR, C1288o.DIRECT, appLovinSdkImpl);
    }

    /* renamed from: d */
    public static C1287n m3043d(AppLovinSdkImpl appLovinSdkImpl) {
        return C1287n.m3035a(AppLovinAdSize.MREC, AppLovinAdType.REGULAR, C1288o.DIRECT, appLovinSdkImpl);
    }

    /* renamed from: e */
    public static C1287n m3044e(AppLovinSdkImpl appLovinSdkImpl) {
        return C1287n.m3035a(AppLovinAdSize.LEADER, AppLovinAdType.REGULAR, C1288o.DIRECT, appLovinSdkImpl);
    }

    /* renamed from: f */
    public static C1287n m3045f(AppLovinSdkImpl appLovinSdkImpl) {
        return C1287n.m3035a(AppLovinAdSize.INTERSTITIAL, AppLovinAdType.REGULAR, C1288o.DIRECT, appLovinSdkImpl);
    }

    /* renamed from: g */
    public static C1287n m3046g(AppLovinSdkImpl appLovinSdkImpl) {
        return C1287n.m3035a(AppLovinAdSize.INTERSTITIAL, AppLovinAdType.REGULAR, C1288o.INDIRECT, appLovinSdkImpl);
    }

    /* renamed from: h */
    public static C1287n m3047h(AppLovinSdkImpl appLovinSdkImpl) {
        return C1287n.m3035a(AppLovinAdSize.INTERSTITIAL, AppLovinAdType.INCENTIVIZED, C1288o.DIRECT, appLovinSdkImpl);
    }

    /* renamed from: i */
    public static C1287n m3048i(AppLovinSdkImpl appLovinSdkImpl) {
        return C1287n.m3035a(AppLovinAdSize.INTERSTITIAL, AppLovinAdType.INCENTIVIZED, C1288o.INDIRECT, appLovinSdkImpl);
    }

    /* renamed from: j */
    public static C1287n m3049j(AppLovinSdkImpl appLovinSdkImpl) {
        return C1287n.m3035a(AppLovinAdSize.NATIVE, AppLovinAdType.NATIVE, C1288o.DIRECT, appLovinSdkImpl);
    }

    /* renamed from: n */
    private boolean m3050n() {
        try {
            return !TextUtils.isEmpty(this.f2603g) ? true : m3055d() == C1288o.DIRECT ? AppLovinAdType.INCENTIVIZED.equals(m3054c()) ? ((Boolean) this.f2599c.get(ea.f2392P)).booleanValue() : m3039a(ea.f2390N, m3053b()) : m3055d() == C1288o.INDIRECT ? AppLovinAdType.INCENTIVIZED.equals(m3054c()) ? ((Boolean) this.f2599c.get(ea.f2393Q)).booleanValue() : m3039a(ea.f2391O, m3053b()) : false;
        } catch (Throwable th) {
            this.f2600d.mo4174e("AdZone", "Unable to safely test preload merge capability", th);
            return false;
        }
    }

    /* renamed from: a */
    String m3051a() {
        return this.f2602f;
    }

    /* renamed from: a */
    void m3052a(AppLovinSdkImpl appLovinSdkImpl) {
        this.f2599c = appLovinSdkImpl;
        this.f2600d = appLovinSdkImpl.getLogger();
    }

    /* renamed from: b */
    AppLovinAdSize m3053b() {
        if (this.f2604h == null && bu.m2396a(this.f2601e, "ad_size")) {
            this.f2604h = new AppLovinAdSize(bu.m2389a(this.f2601e, "ad_size", null, this.f2599c));
        }
        return this.f2604h;
    }

    /* renamed from: c */
    AppLovinAdType m3054c() {
        if (this.f2605i == null && bu.m2396a(this.f2601e, "ad_type")) {
            this.f2605i = new AppLovinAdType(bu.m2389a(this.f2601e, "ad_type", null, this.f2599c));
        }
        return this.f2605i;
    }

    /* renamed from: d */
    C1288o m3055d() {
        if (this.f2606j == C1288o.NONE && bu.m2396a(this.f2601e, "type")) {
            this.f2606j = C1288o.m3065a(bu.m2389a(this.f2601e, "type", null, this.f2599c));
        }
        return this.f2606j;
    }

    /* renamed from: e */
    public boolean m3056e() {
        return AppLovinAdSize.NATIVE.equals(m3053b()) && AppLovinAdType.NATIVE.equals(m3054c());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.f2602f.equalsIgnoreCase(((C1287n) obj).f2602f);
    }

    /* renamed from: f */
    public int m3057f() {
        if (bu.m2396a(this.f2601e, "capacity")) {
            return bu.m2385a(this.f2601e, "capacity", 0, this.f2599c);
        }
        if (!TextUtils.isEmpty(this.f2603g)) {
            return m3056e() ? ((Integer) this.f2599c.get(ea.aM)).intValue() : ((Integer) this.f2599c.get(ea.aL)).intValue();
        } else {
            return ((Integer) this.f2599c.get(m3034a("preload_capacity_", ea.ar))).intValue();
        }
    }

    /* renamed from: g */
    public int m3058g() {
        if (bu.m2396a(this.f2601e, "extended_capacity")) {
            return bu.m2385a(this.f2601e, "extended_capacity", 0, this.f2599c);
        }
        if (!TextUtils.isEmpty(this.f2603g)) {
            return !m3056e() ? ((Integer) this.f2599c.get(ea.aN)).intValue() : 0;
        } else {
            return ((Integer) this.f2599c.get(m3034a("extended_preload_capacity_", ea.aB))).intValue();
        }
    }

    /* renamed from: h */
    public int m3059h() {
        return bu.m2385a(this.f2601e, "preload_count", 0, this.f2599c);
    }

    public int hashCode() {
        return this.f2602f.hashCode();
    }

    /* renamed from: i */
    public boolean m3060i() {
        return bu.m2396a(this.f2601e, "refresh_enabled") ? bu.m2387a(this.f2601e, "refresh_enabled", Boolean.valueOf(false), this.f2599c).booleanValue() : AppLovinAdSize.BANNER.equals(m3053b()) ? ((Boolean) this.f2599c.get(ea.f2379C)).booleanValue() : AppLovinAdSize.MREC.equals(m3053b()) ? ((Boolean) this.f2599c.get(ea.f2381E)).booleanValue() : AppLovinAdSize.LEADER.equals(m3053b()) ? ((Boolean) this.f2599c.get(ea.f2383G)).booleanValue() : false;
    }

    /* renamed from: j */
    public long m3061j() {
        return bu.m2396a(this.f2601e, "refresh_seconds") ? (long) bu.m2385a(this.f2601e, "refresh_seconds", 0, this.f2599c) : AppLovinAdSize.BANNER.equals(m3053b()) ? ((Long) this.f2599c.get(ea.f2380D)).longValue() : AppLovinAdSize.MREC.equals(m3053b()) ? ((Long) this.f2599c.get(ea.f2382F)).longValue() : AppLovinAdSize.LEADER.equals(m3053b()) ? ((Long) this.f2599c.get(ea.f2384H)).longValue() : -1;
    }

    /* renamed from: k */
    public boolean m3062k() {
        if (!((Boolean) this.f2599c.get(ea.f2386J)).booleanValue() || !m3050n()) {
            return false;
        }
        if (TextUtils.isEmpty(this.f2603g)) {
            ec a = m3034a("preload_merge_init_tasks_", null);
            if (a == null || !((Boolean) this.f2599c.get(a)).booleanValue()) {
                return false;
            }
            return m3057f() > 0;
        } else if (this.f2601e != null && m3059h() == 0) {
            return false;
        } else {
            String toUpperCase = ((String) this.f2599c.get(ea.f2390N)).toUpperCase(Locale.ENGLISH);
            return (toUpperCase.contains(AppLovinAdSize.INTERSTITIAL.getLabel()) || toUpperCase.contains(AppLovinAdSize.BANNER.getLabel()) || toUpperCase.contains(AppLovinAdSize.MREC.getLabel()) || toUpperCase.contains(AppLovinAdSize.LEADER.getLabel())) ? ((Boolean) this.f2599c.get(ea.bl)).booleanValue() : this.f2599c.getZoneManager().m3071a(this) && m3059h() > 0 && ((Boolean) this.f2599c.get(ea.cX)).booleanValue();
        }
    }

    /* renamed from: l */
    public boolean m3063l() {
        return bu.m2396a(this.f2601e, "wrapped_ads_enabled") ? bu.m2387a(this.f2601e, "wrapped_ads_enabled", Boolean.valueOf(false), this.f2599c).booleanValue() : m3053b() != null ? this.f2599c.getAsList(ea.cU).contains(m3053b().getLabel()) : ((Boolean) this.f2599c.get(ea.cT)).booleanValue();
    }

    /* renamed from: m */
    public boolean m3064m() {
        return C1287n.m3041b(this.f2599c).contains(this);
    }

    public String toString() {
        return "AdZone{identifier=" + this.f2602f + ", zoneObject=" + this.f2601e + '}';
    }
}
