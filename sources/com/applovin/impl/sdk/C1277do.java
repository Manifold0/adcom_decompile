package com.applovin.impl.sdk;

import android.content.SharedPreferences;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* renamed from: com.applovin.impl.sdk.do */
public class C1277do {
    /* renamed from: a */
    private final AppLovinSdkImpl f2337a;
    /* renamed from: b */
    private final AppLovinLogger f2338b;
    /* renamed from: c */
    private ArrayList<dq> f2339c;
    /* renamed from: d */
    private ArrayList<dq> f2340d;
    /* renamed from: e */
    private final Object f2341e;
    /* renamed from: f */
    private final SharedPreferences f2342f;

    C1277do(AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.f2337a = appLovinSdkImpl;
        this.f2338b = appLovinSdkImpl.getLogger();
        this.f2342f = appLovinSdkImpl.getApplicationContext().getSharedPreferences("com.applovin.sdk.impl.postbackQueue.domain", 0);
        this.f2341e = new Object();
        this.f2339c = m2602c();
        this.f2340d = new ArrayList();
    }

    /* renamed from: a */
    private void m2598a(dq dqVar) {
        synchronized (this.f2341e) {
            m2601b(dqVar);
            m2603c(dqVar);
        }
    }

    /* renamed from: b */
    private dq m2599b(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("attemptNumber");
            return new dq(jSONObject.getString("targetUrl"), bu.m2391a(jSONObject.getJSONObject("requestBody")), i, jSONObject.getString("backupUrl"));
        } catch (Throwable e) {
            this.f2338b.mo4179w("PersistentPostbackManager", "Unable to inflate postback request from JSON.", e);
            return null;
        }
    }

    /* renamed from: b */
    private void m2601b(dq dqVar) {
        synchronized (this.f2341e) {
            if (this.f2339c.size() < ((Integer) this.f2337a.get(ea.bT)).intValue()) {
                this.f2339c.add(dqVar);
                m2604d();
                this.f2338b.mo4172d("PersistentPostbackManager", "Enqueued postback: " + dqVar);
            } else {
                this.f2338b.mo4178w("PersistentPostbackManager", "Persistent queue has reached maximum size; postback retried in memory only." + dqVar);
            }
        }
    }

    /* renamed from: c */
    private ArrayList<dq> m2602c() {
        if (ab.m2200b()) {
            Set<String> set = (Set) this.f2337a.get(ef.f2441b, new LinkedHashSet(0), this.f2342f);
            ArrayList<dq> arrayList = new ArrayList(Math.max(1, set.size()));
            int intValue = ((Integer) this.f2337a.get(ea.bU)).intValue();
            this.f2338b.mo4172d("PersistentPostbackManager", "Deserializing " + set.size() + " postback(s).");
            for (String str : set) {
                dq b = m2599b(str);
                if (b == null) {
                    this.f2338b.mo4173e("PersistentPostbackManager", "Unable to deserialize postback json: " + str);
                } else if (b.m2614a() > intValue) {
                    arrayList.add(b);
                } else {
                    this.f2338b.mo4172d("PersistentPostbackManager", "Skipping deserialization because maximum attempt count exceeded for postback: " + b);
                }
            }
            this.f2338b.mo4172d("PersistentPostbackManager", "Successfully loaded postback queue with " + arrayList.size() + " postback(s).");
            return arrayList;
        }
        this.f2338b.mo4172d("PersistentPostbackManager", "Loading new postback queue due to old Android version...");
        return new ArrayList();
    }

    /* renamed from: c */
    private void m2603c(dq dqVar) {
        this.f2338b.mo4172d("PersistentPostbackManager", "Preparing to submit postback..." + dqVar);
        if (this.f2337a.m2144e()) {
            this.f2338b.mo4172d("PersistentPostbackManager", "Skipping postback dispatch because SDK is still initializing - postback will be dispatched afterwards");
            return;
        }
        synchronized (this.f2341e) {
            dqVar.m2615a(dqVar.m2614a() + 1);
            m2604d();
        }
        int intValue = ((Integer) this.f2337a.get(ea.bU)).intValue();
        if (dqVar.m2614a() > intValue) {
            this.f2338b.mo4178w("PersistentPostbackManager", "Exceeded maximum persisted attempt count of " + intValue + ". Dequeuing postback: " + dqVar);
            m2605d(dqVar);
            return;
        }
        this.f2337a.getPostbackService().dispatchPostbackAsync(dqVar.m2616b(), dqVar.m2618d(), dqVar.m2617c(), new dp(this, dqVar));
    }

    /* renamed from: d */
    private void m2604d() {
        if (ab.m2203c()) {
            LinkedHashSet linkedHashSet = new LinkedHashSet(this.f2339c.size());
            Iterator it = this.f2339c.iterator();
            while (it.hasNext()) {
                String f = m2607f((dq) it.next());
                if (f != null) {
                    linkedHashSet.add(f);
                }
            }
            this.f2337a.put(ef.f2441b, linkedHashSet);
            this.f2338b.mo4172d("PersistentPostbackManager", "Wrote updated postback queue to disk.");
            return;
        }
        this.f2338b.mo4172d("PersistentPostbackManager", "Skipping writing postback queue to disk due to old Android version...");
    }

    /* renamed from: d */
    private void m2605d(dq dqVar) {
        synchronized (this.f2341e) {
            this.f2339c.remove(dqVar);
            m2604d();
        }
        this.f2338b.mo4172d("PersistentPostbackManager", "Dequeued successfully transmitted postback: " + dqVar);
    }

    /* renamed from: e */
    private void m2606e(dq dqVar) {
        synchronized (this.f2341e) {
            this.f2340d.add(dqVar);
        }
    }

    /* renamed from: f */
    private String m2607f(dq dqVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("attemptNumber", dqVar.m2614a());
            jSONObject.put("targetUrl", dqVar.m2616b());
            String c = dqVar.m2617c();
            if (AppLovinSdkUtils.isValidString(c)) {
                jSONObject.put("backupUrl", c);
            }
            Map d = dqVar.m2618d();
            if (d != null) {
                jSONObject.put("requestBody", new JSONObject(d));
            }
            return jSONObject.toString();
        } catch (Throwable e) {
            this.f2338b.mo4179w("PersistentPostbackManager", "Unable to serialize postback request to JSON.", e);
            return null;
        }
    }

    /* renamed from: a */
    public void m2608a() {
        synchronized (this.f2341e) {
            if (this.f2339c != null) {
                for (dq c : new ArrayList(this.f2339c)) {
                    m2603c(c);
                }
            }
        }
    }

    /* renamed from: a */
    public void m2609a(String str) {
        m2610a(str, null);
    }

    /* renamed from: a */
    public void m2610a(String str, Map<String, String> map) {
        m2611a(str, map, true);
    }

    /* renamed from: a */
    public void m2611a(String str, Map<String, String> map, boolean z) {
        m2612a(str, map, z, null);
    }

    /* renamed from: a */
    public void m2612a(String str, Map<String, String> map, boolean z, String str2) {
        if (AppLovinSdkUtils.isValidString(str)) {
            if (z) {
                String str3 = "&postback_ts=" + System.currentTimeMillis();
                str = str + str3;
                if (AppLovinSdkUtils.isValidString(str2)) {
                    str2 = str2 + str3;
                }
            }
            m2598a(new dq(str, map, 0, str2));
        }
    }

    /* renamed from: b */
    public void m2613b() {
        synchronized (this.f2341e) {
            Iterator it = this.f2340d.iterator();
            while (it.hasNext()) {
                m2603c((dq) it.next());
            }
            this.f2340d.clear();
        }
    }
}
