package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinLogger;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.applovin.impl.sdk.c */
class C1275c {
    /* renamed from: a */
    private final AppLovinSdkImpl f2179a;
    /* renamed from: b */
    private final AppLovinLogger f2180b;
    /* renamed from: c */
    private final Object f2181c = new Object();
    /* renamed from: d */
    private final C1279f f2182d = new C1279f();

    C1275c(AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.f2179a = appLovinSdkImpl;
        this.f2180b = appLovinSdkImpl.getLogger();
    }

    /* renamed from: a */
    private C1278e m2414a(C1227q c1227q) {
        C1278e c1278e;
        synchronized (this.f2181c) {
            String o = c1227q.m1779o();
            c1278e = (C1278e) this.f2182d.get(o);
            if (c1278e == null) {
                c1278e = new C1278e(o, c1227q.m1780p(), c1227q.m1781q());
                this.f2182d.put(o, c1278e);
            }
        }
        return c1278e;
    }

    /* renamed from: a */
    private void m2415a(JSONObject jSONObject) {
        dx c1276d = new C1276d(this, "POST", new JSONObject(), "RepeatSubmitAdEvents", this.f2179a);
        c1276d.m2497a(m2416c());
        c1276d.m2498a(jSONObject);
        c1276d.m2502b(m2417d());
        c1276d.m2500b(((Integer) this.f2179a.get(ea.dI)).intValue());
        c1276d.m2503c(((Integer) this.f2179a.get(ea.dJ)).intValue());
        c1276d.m2495a(ea.f2416m);
        c1276d.m2501b(ea.f2420q);
        this.f2179a.getTaskManager().m2855a(c1276d, fe.BACKGROUND);
    }

    /* renamed from: c */
    private String m2416c() {
        return ag.m2237a("s", null, this.f2179a);
    }

    /* renamed from: d */
    private String m2417d() {
        return ag.m2247c("s", null, this.f2179a);
    }

    /* renamed from: e */
    private void m2418e() {
        HashSet hashSet;
        synchronized (this.f2181c) {
            hashSet = new HashSet(this.f2182d.size());
            for (C1278e c1278e : this.f2182d.values()) {
                try {
                    String a = c1278e.m2654b();
                    if (a != null) {
                        hashSet.add(a);
                    }
                } catch (Throwable e) {
                    this.f2180b.mo4174e("AdEventStatsManager", "Failed to serialize " + c1278e, e);
                }
            }
        }
        this.f2179a.put(ef.f2449j, hashSet);
    }

    /* renamed from: a */
    void m2419a() {
        if (!((Boolean) this.f2179a.get(ea.dH)).booleanValue()) {
            return;
        }
        if (ab.m2200b()) {
            Set<String> set = (Set) this.f2179a.get(ef.f2449j, new HashSet(0));
            this.f2179a.remove(ef.f2449j);
            if (set == null || set.isEmpty()) {
                this.f2180b.mo4172d("AdEventStatsManager", "No serialized ad events found");
                return;
            }
            this.f2180b.mo4172d("AdEventStatsManager", "De-serializing " + set.size() + " stat ad events");
            JSONArray jSONArray = new JSONArray();
            for (String str : set) {
                try {
                    jSONArray.put(new JSONObject(str));
                } catch (Throwable e) {
                    this.f2180b.mo4174e("AdEventStatsManager", "Failed to parse: " + str, e);
                }
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("stats", jSONArray);
                m2415a(jSONObject);
                return;
            } catch (Throwable e2) {
                this.f2180b.mo4174e("AdEventStatsManager", "Failed to create stats to submit", e2);
                return;
            }
        }
        this.f2180b.mo4172d("AdEventStatsManager", "Not loading new event stat due to old Android version...");
    }

    /* renamed from: a */
    void m2420a(C1274b c1274b, long j, C1227q c1227q) {
        if (c1227q == null) {
            throw new IllegalArgumentException("No ad specified");
        } else if (c1274b == null) {
            throw new IllegalArgumentException("No key specified");
        } else if (((Boolean) this.f2179a.get(ea.dH)).booleanValue()) {
            synchronized (this.f2181c) {
                m2414a(c1227q).m2655a(c1274b.m2346a(), j);
            }
            m2418e();
        }
    }

    /* renamed from: b */
    void m2421b() {
        synchronized (this.f2181c) {
            this.f2182d.clear();
        }
    }
}
