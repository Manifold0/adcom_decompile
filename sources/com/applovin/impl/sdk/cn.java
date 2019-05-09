package com.applovin.impl.sdk;

import android.content.pm.ApplicationInfo;
import com.applovin.mediation.AppLovinMediationAdapter;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinMediationProvider;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;

class cn {
    /* renamed from: a */
    private static final Map<String, String> f2226a = new HashMap();
    /* renamed from: b */
    private final AppLovinSdkImpl f2227b;
    /* renamed from: c */
    private final AppLovinLogger f2228c;
    /* renamed from: d */
    private final Object f2229d = new Object();
    /* renamed from: e */
    private final Map<String, cp> f2230e = new HashMap();
    /* renamed from: f */
    private final Collection<String> f2231f = new HashSet();
    /* renamed from: g */
    private final Collection<co> f2232g = new HashSet();

    static {
        f2226a.put("chartboost", "com.applovin.mediation.impl.AppLovinChartboostMediationAdapter");
        f2226a.put("facebook", "com.applovin.mediation.impl.AppLovinFacebookMediationAdapter");
        f2226a.put("google", "com.applovin.mediation.impl.AppLovinGoogleMediationAdapter");
        f2226a.put(AppLovinMediationProvider.HEYZAP, "com.applovin.mediation.impl.AppLovinHeyzapMediationAdapter");
        f2226a.put("inmobi", "com.applovin.mediation.impl.AppLovinInMobiMediationAdapter");
        f2226a.put(AppLovinMediationProvider.MOPUB, "com.applovin.mediation.impl.AppLovinMoPubMediationAdapter");
        f2226a.put(AppLovinMediationProvider.IRONSOURCE, "com.applovin.mediation.impl.AppLovinIronSourceMediationAdapter");
        f2226a.put("vungle", "com.applovin.mediation.impl.AppLovinVungleMediationAdapter");
        f2226a.put("unity", "com.applovin.mediation.impl.AppLovinUnityMediationAdapter");
    }

    cn(AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.f2227b = appLovinSdkImpl;
        this.f2228c = appLovinSdkImpl.getLogger();
    }

    /* renamed from: a */
    private cp m2437a(co coVar, Map<String, String> map) {
        if (coVar == null) {
            throw new IllegalArgumentException("No adapter spec specified");
        }
        synchronized (this.f2229d) {
            String a = coVar.m2453a();
            if (this.f2231f.contains(a)) {
                this.f2228c.mo4172d("MediationAdapterManager", "Not attempting to load " + coVar + " due to prior errors");
                return null;
            } else if (this.f2230e.containsKey(a)) {
                r0 = (cp) this.f2230e.get(a);
                return r0;
            } else {
                r0 = m2440b(coVar, map);
                if (r0 != null) {
                    this.f2228c.mo4172d("MediationAdapterManager", "Loaded " + coVar);
                    this.f2230e.put(a, r0);
                    return r0;
                }
                this.f2228c.mo4173e("MediationAdapterManager", "Failed to load " + coVar);
                this.f2231f.add(a);
                return null;
            }
        }
    }

    /* renamed from: a */
    private String m2438a(String str) {
        if (!AppLovinSdkUtils.isValidString(str)) {
            return null;
        }
        try {
            ApplicationInfo applicationInfo = this.f2227b.getApplicationContext().getPackageManager().getApplicationInfo(this.f2227b.getApplicationContext().getPackageName(), 128);
            Collection<co> b = co.m2452b(applicationInfo.metaData.getString("applovin.mediation:load"), this.f2228c);
            if (!b.isEmpty()) {
                for (co coVar : b) {
                    if (coVar.m2454b().equalsIgnoreCase(str) && AppLovinSdkUtils.isValidString(coVar.m2453a())) {
                        return coVar.m2453a();
                    }
                }
            }
            return applicationInfo.metaData.getString("applovin.mediation." + str + ":" + "class");
        } catch (Throwable th) {
            this.f2228c.mo4174e("MediationAdapterManager", "Unable to retrieve classname from Android Manifest for adapter name: " + str, th);
            return null;
        }
    }

    /* renamed from: a */
    private void m2439a(Collection<co> collection, String str) {
        for (co coVar : collection) {
            cp a = m2445a(coVar.m2454b(), coVar.m2453a(), null);
            if (a != null) {
                this.f2228c.mo4175i("MediationAdapterManager", "Loaded " + str + " adapter: " + a);
            }
        }
    }

    /* renamed from: b */
    private cp m2440b(co coVar, Map<String, String> map) {
        try {
            Class cls = Class.forName(coVar.m2453a());
            if (cls != null) {
                Object newInstance = cls.newInstance();
                if (newInstance instanceof AppLovinMediationAdapter) {
                    cp cpVar = new cp(coVar.m2454b(), (AppLovinMediationAdapter) newInstance, this.f2227b);
                    cpVar.m2472a((Map) map);
                    if (cpVar.m2473b()) {
                        return cpVar;
                    }
                    this.f2228c.userError("MediationAdapterManager", "Failed to initialize " + coVar);
                    return null;
                }
                this.f2228c.userError("MediationAdapterManager", coVar + " error: not an instance of '" + AppLovinMediationAdapter.class.getName() + "'.");
                return null;
            }
            this.f2228c.userError("MediationAdapterManager", "No class found for " + coVar);
            return null;
        } catch (Throwable th) {
            this.f2228c.userError("MediationAdapterManager", "Failed to load: " + coVar, th);
            return null;
        }
    }

    /* renamed from: e */
    private Collection<co> m2442e() {
        try {
            ApplicationInfo applicationInfo = this.f2227b.getApplicationContext().getPackageManager().getApplicationInfo(this.f2227b.getApplicationContext().getPackageName(), 128);
            if (applicationInfo.metaData == null) {
                return Collections.emptyList();
            }
            String string = applicationInfo.metaData.getString("applovin.mediation:load");
            Collection<co> b = co.m2452b(string, this.f2228c);
            if (b == null || b.isEmpty()) {
                this.f2228c.mo4172d("MediationAdapterManager", "No adapter specs found in: '" + string + "'");
                return Collections.emptyList();
            }
            Collection<co> arrayList = new ArrayList(b.size());
            for (co coVar : b) {
                if (!AppLovinSdkUtils.isValidString(coVar.m2454b())) {
                    this.f2228c.userError("MediationAdapterManager", "Ignored loading of adapter with class " + coVar.m2453a() + ": no name specified");
                } else if (AppLovinSdkUtils.isValidString(coVar.m2453a())) {
                    arrayList.add(coVar);
                } else {
                    String string2 = applicationInfo.metaData.getString("applovin.mediation." + coVar.m2454b() + ":" + "class");
                    if (AppLovinSdkUtils.isValidString(string2)) {
                        arrayList.add(new co(coVar.m2454b(), string2));
                    } else {
                        string2 = (String) f2226a.get(coVar.m2454b());
                        if (AppLovinSdkUtils.isValidString(string2)) {
                            arrayList.add(new co(coVar.m2454b(), string2));
                        } else {
                            this.f2228c.userError("MediationAdapterManager", "Ignored loading of " + coVar.m2454b() + ": no default adapter class found");
                        }
                    }
                }
            }
            return arrayList;
        } catch (Throwable th) {
            this.f2228c.userError("MediationAdapterManager", "Unable to load applovin.mediation:loadfrom AndroidManifest.xml", th);
            return Collections.emptyList();
        }
    }

    /* renamed from: f */
    private Collection<co> m2443f() {
        return co.m2452b((String) this.f2227b.get(ef.f2445f), this.f2228c);
    }

    /* renamed from: g */
    private void m2444g() {
        String a;
        synchronized (this.f2229d) {
            a = co.m2451a(this.f2232g);
        }
        this.f2227b.put(ef.f2445f, a);
    }

    /* renamed from: a */
    cp m2445a(String str, String str2, Map<String, String> map) {
        if (AppLovinSdkUtils.isValidString(str)) {
            String str3;
            if (AppLovinSdkUtils.isValidString(str2)) {
                this.f2228c.mo4172d("MediationAdapterManager", "Loading adapter using explicit classname: " + str2);
                str3 = str2;
            } else if (map == null || !map.containsKey("class")) {
                str3 = m2438a(str);
                if (!AppLovinSdkUtils.isValidString(str3)) {
                    str3 = (String) f2226a.get(str.toLowerCase(Locale.ENGLISH));
                    if (AppLovinSdkUtils.isValidString(str3)) {
                        this.f2228c.mo4172d("MediationAdapterManager", "Loading '" + str + "' adapter using resolved default classname: " + str3);
                    } else {
                        this.f2228c.mo4178w("MediationAdapterManager", "Unable to find default classname for '" + str + "'");
                        return null;
                    }
                }
            } else {
                str3 = (String) map.get("class");
                if (AppLovinSdkUtils.isValidString(str3)) {
                    this.f2228c.mo4172d("MediationAdapterManager", "Loading '" + str + "' adapter using configured classname: " + str3);
                } else {
                    this.f2228c.mo4178w("MediationAdapterManager", "Invalid configured classname for '" + str + "'");
                    return null;
                }
            }
            return m2437a(new co(str, str3), (Map) map);
        }
        this.f2228c.mo4173e("MediationAdapterManager", "No adapter name provided for " + str2 + ", not loading the adapter ");
        return null;
    }

    /* renamed from: a */
    void m2446a() {
        synchronized (this.f2229d) {
            if (((Boolean) this.f2227b.get(ea.dC)).booleanValue()) {
                m2439a(m2443f(), "last used");
            }
            if (((Boolean) this.f2227b.get(ea.dD)).booleanValue()) {
                m2439a(m2442e(), "AndroidManifest");
            }
        }
    }

    /* renamed from: a */
    void m2447a(cp cpVar) {
        if (cpVar != null) {
            co coVar = new co(cpVar.m2467a(), cpVar.m2477f());
            synchronized (this.f2229d) {
                if (!this.f2232g.contains(coVar)) {
                    this.f2232g.add(coVar);
                    m2444g();
                }
            }
        }
    }

    /* renamed from: b */
    Collection<String> m2448b() {
        Collection arrayList;
        synchronized (this.f2229d) {
            arrayList = new ArrayList(this.f2231f);
        }
        return arrayList;
    }

    /* renamed from: c */
    Collection<cp> m2449c() {
        Collection arrayList;
        synchronized (this.f2229d) {
            arrayList = new ArrayList(this.f2230e.values());
        }
        return arrayList;
    }
}
