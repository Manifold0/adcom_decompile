package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Iterator;
import java.util.LinkedHashSet;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.applovin.impl.sdk.p */
final class C1289p {
    /* renamed from: a */
    private final AppLovinSdkImpl f2612a;
    /* renamed from: b */
    private final AppLovinLogger f2613b;
    /* renamed from: c */
    private LinkedHashSet<C1287n> f2614c;
    /* renamed from: d */
    private final Object f2615d = new Object();
    /* renamed from: e */
    private volatile boolean f2616e;

    C1289p(AppLovinSdkImpl appLovinSdkImpl) {
        this.f2612a = appLovinSdkImpl;
        this.f2613b = appLovinSdkImpl.getLogger();
        this.f2614c = m3067c();
    }

    /* renamed from: b */
    private LinkedHashSet<C1287n> m3066b(JSONArray jSONArray) {
        LinkedHashSet<C1287n> linkedHashSet = new LinkedHashSet(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject a = bu.m2394a(jSONArray, i, null, this.f2612a);
            this.f2613b.mo4172d("AdZoneManager", "Loading zone: " + a + "...");
            linkedHashSet.add(C1287n.m3038a(bu.m2389a(a, "id", null, this.f2612a), a, this.f2612a));
        }
        return linkedHashSet;
    }

    /* renamed from: c */
    private LinkedHashSet<C1287n> m3067c() {
        Iterator it;
        LinkedHashSet<C1287n> linkedHashSet = new LinkedHashSet();
        try {
            String str = (String) this.f2612a.get(ef.f2444e);
            if (AppLovinSdkUtils.isValidString(str)) {
                JSONArray jSONArray = new JSONArray(str);
                if (jSONArray.length() > 0) {
                    linkedHashSet = m3066b(jSONArray);
                } else {
                    this.f2613b.mo4172d("AdZoneManager", "Unable to inflate json string: " + str);
                }
            }
            if (!linkedHashSet.isEmpty()) {
                this.f2613b.mo4172d("AdZoneManager", "Retrieved persisted zones: " + linkedHashSet);
                it = linkedHashSet.iterator();
                while (it.hasNext()) {
                    ((C1287n) it.next()).m3052a(this.f2612a);
                }
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            if (!linkedHashSet.isEmpty()) {
                this.f2613b.mo4172d("AdZoneManager", "Retrieved persisted zones: " + linkedHashSet);
                Iterator it2 = linkedHashSet.iterator();
                while (it2.hasNext()) {
                    ((C1287n) it2.next()).m3052a(this.f2612a);
                }
            }
        }
        return linkedHashSet;
    }

    /* renamed from: c */
    private void m3068c(JSONArray jSONArray) {
        if (((Boolean) this.f2612a.get(ea.cY)).booleanValue()) {
            this.f2613b.mo4172d("AdZoneManager", "Persisting zones...");
            this.f2612a.put(ef.f2444e, jSONArray.toString());
        }
    }

    /* renamed from: a */
    public LinkedHashSet<C1287n> m3069a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return new LinkedHashSet();
        }
        LinkedHashSet<C1287n> linkedHashSet;
        Object obj;
        LinkedHashSet<C1287n> linkedHashSet2 = new LinkedHashSet(jSONArray.length());
        synchronized (this.f2615d) {
            if (this.f2616e) {
                linkedHashSet = linkedHashSet2;
                obj = null;
            } else {
                this.f2613b.mo4172d("AdZoneManager", "Found " + jSONArray.length() + " zone(s)...");
                Object b = m3066b(jSONArray);
                linkedHashSet2 = new LinkedHashSet(b);
                linkedHashSet2.removeAll(this.f2614c);
                this.f2614c = b;
                this.f2616e = true;
                Object obj2 = b;
                linkedHashSet = linkedHashSet2;
                obj = obj2;
            }
        }
        if (obj == null) {
            return linkedHashSet;
        }
        m3068c(jSONArray);
        this.f2613b.mo4172d("AdZoneManager", "Finished loading zones");
        return linkedHashSet;
    }

    /* renamed from: a */
    public boolean m3070a() {
        return this.f2616e;
    }

    /* renamed from: a */
    public boolean m3071a(C1287n c1287n) {
        boolean contains;
        synchronized (this.f2615d) {
            contains = this.f2614c.contains(c1287n);
        }
        return contains;
    }

    /* renamed from: b */
    public LinkedHashSet<C1287n> m3072b() {
        LinkedHashSet<C1287n> linkedHashSet;
        synchronized (this.f2615d) {
            linkedHashSet = this.f2614c;
        }
        return linkedHashSet;
    }
}
