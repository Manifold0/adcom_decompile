// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.LinkedHashMap;
import org.json.JSONArray;
import java.util.Iterator;
import java.io.Serializable;
import org.json.JSONException;
import java.util.HashSet;
import java.util.Map;
import org.json.JSONObject;
import com.applovin.sdk.AppLovinLogger;

class c
{
    private final AppLovinSdkImpl a;
    private final AppLovinLogger b;
    private final Object c;
    private final f d;
    
    c(final AppLovinSdkImpl a) {
        this.c = new Object();
        this.d = new f(this, null);
        if (a == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.a = a;
        this.b = a.getLogger();
    }
    
    private e a(final q q) {
        synchronized (this.c) {
            final String o = q.o();
            e e;
            if ((e = ((LinkedHashMap<K, e>)this.d).get(o)) == null) {
                e = new e(o, q.p(), q.q(), null);
                this.d.put(o, e);
            }
            return e;
        }
    }
    
    private void a(final JSONObject jsonObject) {
        final d d = new d(this, "POST", new JSONObject(), "RepeatSubmitAdEvents", this.a);
        d.a(this.c());
        d.a(jsonObject);
        d.b(this.d());
        d.b(this.a.get(ea.dI));
        d.c(this.a.get(ea.dJ));
        d.a(ea.m);
        d.b(ea.q);
        this.a.getTaskManager().a(d, fe.b);
    }
    
    private String c() {
        return ag.a("s", null, this.a);
    }
    
    private String d() {
        return ag.c("s", null, this.a);
    }
    
    private void e() {
        synchronized (this.c) {
            final HashSet<String> set = new HashSet<String>(this.d.size());
            for (final e e : ((LinkedHashMap<K, e>)this.d).values()) {
                try {
                    final String a = e.b();
                    if (a == null) {
                        continue;
                    }
                    set.add(a);
                }
                catch (JSONException ex) {
                    this.b.e("AdEventStatsManager", "Failed to serialize " + e, (Throwable)ex);
                }
            }
        }
        // monitorexit(o)
        final Throwable t;
        this.a.put((ef<Serializable>)ef.j, t);
    }
    
    void a() {
        if (!this.a.get(ea.dH)) {
            return;
        }
        if (!ab.b()) {
            this.b.d("AdEventStatsManager", "Not loading new event stat due to old Android version...");
            return;
        }
        final HashSet<String> set = this.a.get((ef<HashSet<String>>)ef.j, new HashSet<String>(0));
        this.a.remove(ef.j);
        Label_0238: {
            if (set == null || set.isEmpty()) {
                break Label_0238;
            }
            this.b.d("AdEventStatsManager", "De-serializing " + set.size() + " stat ad events");
            final JSONArray jsonArray = new JSONArray();
            for (final String s : set) {
                try {
                    jsonArray.put((Object)new JSONObject(s));
                }
                catch (JSONException ex) {
                    this.b.e("AdEventStatsManager", "Failed to parse: " + s, (Throwable)ex);
                }
            }
            try {
                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("stats", (Object)jsonArray);
                this.a(jsonObject);
                return;
            }
            catch (JSONException ex2) {
                this.b.e("AdEventStatsManager", "Failed to create stats to submit", (Throwable)ex2);
                return;
            }
        }
        this.b.d("AdEventStatsManager", "No serialized ad events found");
    }
    
    void a(final b b, final long n, final q q) {
        if (q == null) {
            throw new IllegalArgumentException("No ad specified");
        }
        if (b == null) {
            throw new IllegalArgumentException("No key specified");
        }
        if (!this.a.get(ea.dH)) {
            return;
        }
        synchronized (this.c) {
            this.a(q).a(b.a(), n);
            // monitorexit(this.c)
            this.e();
        }
    }
    
    void b() {
        synchronized (this.c) {
            this.d.clear();
        }
    }
}
