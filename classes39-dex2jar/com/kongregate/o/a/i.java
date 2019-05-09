// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.a;

import java.net.URI;
import org.json.JSONObject;
import com.kongregate.o.g.c;
import com.kongregate.o.g.b;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import com.google.gson.Gson;
import android.text.TextUtils;
import com.kongregate.android.internal.util.j;
import java.util.HashMap;
import java.util.Collection;
import android.os.Looper;
import android.os.Handler;
import java.util.Map;
import java.util.List;
import android.content.SharedPreferences;
import java.util.concurrent.atomic.AtomicBoolean;
import android.content.Context;

public class i
{
    private Context a;
    private String b;
    private String c;
    private String d;
    private AtomicBoolean e;
    private SharedPreferences f;
    private List<Map<String, Object>> g;
    private final Object h;
    private Boolean i;
    private final Handler j;
    private final Runnable k;
    
    public i(final Context context) {
        this.h = new Object();
        this.i = false;
        this.j = new Handler(Looper.getMainLooper());
        this.k = new Runnable() {
            @Override
            public void run() {
                com.kongregate.o.a.i.this.f();
                com.kongregate.o.a.i.this.i = false;
                com.kongregate.o.a.i.this.e();
            }
        };
        this.a = context.getApplicationContext();
    }
    
    private void a(final List<Map<String, Object>> list) {
        synchronized (this.h) {
            this.g.removeAll(list);
            this.c();
        }
    }
    
    private void b(final String s, final Map<String, Object> map) {
        com.kongregate.android.internal.util.i.a(map);
        final HashMap<String, Map<String, Object>> hashMap = new HashMap<String, Map<String, Object>>();
        hashMap.put("payload", map);
        hashMap.put("event_type", (Map<String, Object>)s);
        synchronized (this.h) {
            if (this.g == null) {
                com.kongregate.android.internal.util.j.a("KongAnalyticsWrapper - Pending events not initialized");
                return;
            }
            this.g.add((Map<String, Object>)hashMap);
            if (this.g.size() > 100) {
                com.kongregate.android.internal.util.j.a("Saved events overflow, discarding oldest event");
                this.g.remove(0);
            }
            this.c();
            // monitorexit(this.h)
            this.f();
        }
    }
    
    private boolean b(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && !s.startsWith("swrve.") && !s.startsWith("delta.");
    }
    
    private void c() {
        synchronized (this.h) {
            this.f.edit().putString("pending_events", new Gson().toJson((Object)this.g)).commit();
        }
    }
    
    private void d() {
        final String string = this.f.getString("pending_events", "[]");
        final ArrayList list = new ArrayList();
        List list2;
        while (true) {
            try {
                list2 = (List)new Gson().fromJson(string, new TypeToken<List<Map<String, Object>>>() {}.getType());
                final Object h = this.h;
                // monitorenter(h)
                final i i = this;
                final List list3 = list2;
                i.g = (List<Map<String, Object>>)list3;
                return;
            }
            catch (JsonSyntaxException h) {
                com.kongregate.android.internal.util.j.c("Failed to parse saved event json, discarding corrupt event data");
                continue;
            }
            break;
        }
        try {
            final i i = this;
            final List list3 = list2;
            i.g = (List<Map<String, Object>>)list3;
        }
        finally {
        }
        // monitorexit(h)
    }
    
    private void e() {
        if (this.i) {
            return;
        }
        this.i = true;
        this.j.postDelayed(this.k, 30000L);
    }
    
    private void f() {
        if (!this.e.compareAndSet(false, true)) {
            return;
        }
        new HashMap<String, String>().put("X-Api-Key", this.c);
        final ArrayList list;
        synchronized (this.h) {
            list = new ArrayList((Collection<? extends E>)this.g);
            // monitorexit(this.h)
            if (list == null || list.size() == 0) {
                this.e.set(false);
                return;
            }
        }
        final HashMap<String, ArrayList> hashMap = new HashMap<String, ArrayList>();
        hashMap.put("events", list);
        try {
            final Map<String, String> map;
            com.kongregate.o.g.b.a().a(this.d, (Map<String, Object>)hashMap, "application/json", map, (b.a)new b.a() {
                private void b(final c c) {
                    final long n = c.e();
                    com.kongregate.android.internal.util.j.a("Event submit failed: " + n);
                    if (n == 0L || n >= 500L) {
                        com.kongregate.android.internal.util.j.a("Non-fatal error, will retry");
                    }
                    else {
                        com.kongregate.android.internal.util.j.a("Rejected payload, discarding sent events");
                        com.kongregate.o.a.i.this.a(list);
                    }
                    com.kongregate.o.a.i.this.e.set(false);
                }
                
                @Override
                public void a(final c c) {
                    super.a(c);
                    this.b(c);
                }
                
                @Override
                public void a(final c c, final JSONObject jsonObject) {
                    com.kongregate.android.internal.util.j.a("Event submitted complete: " + c.e());
                    if (c.f()) {
                        com.kongregate.o.a.i.this.a(list);
                    }
                    com.kongregate.o.a.i.this.e.set(false);
                }
                
                @Override
                public void b(final c c, final JSONObject jsonObject) {
                    super.b(c, jsonObject);
                    this.b(c);
                }
            });
        }
        catch (IllegalStateException ex) {
            com.kongregate.android.internal.util.j.a("HttpQueueManager not initialized");
            this.e.set(false);
        }
    }
    
    public void a() {
        this.f();
        this.j.removeCallbacks(this.k);
        this.i = false;
    }
    
    public void a(final String s) {
        this.a(s, new HashMap<String, Object>());
    }
    
    public void a(final String s, final Map<String, Object> map) {
        if (this.b(s)) {
            this.b(s, map);
        }
    }
    
    public boolean a(final Map<String, Object> map) {
        this.b = com.kongregate.android.internal.sdk.b.a(map, "kong_analytics_id");
        this.c = com.kongregate.android.internal.sdk.b.a(map, "kong_analytics_key");
        if (TextUtils.isEmpty((CharSequence)this.b) || TextUtils.isEmpty((CharSequence)this.c)) {
            com.kongregate.android.internal.util.j.a("Kong Analytics Service - Analytics ID or API Key missing, not initialized");
            return false;
        }
        if (!this.b.startsWith("/")) {
            this.b = "/" + this.b;
        }
        final String a = com.kongregate.android.internal.sdk.b.a(map, "kong_analytics_domain", "analytics.kongregate.io");
        try {
            this.d = URI.create("https://" + a + this.b).toString();
            this.g = new ArrayList<Map<String, Object>>();
            final Context a2 = this.a;
            final Context a3 = this.a;
            this.f = a2.getSharedPreferences("kongregate_analytics_wrapper", 0);
            this.e = new AtomicBoolean(false);
            this.d();
            this.e();
            com.kongregate.android.internal.util.j.a("Kong Analytics Service Initialized");
            return true;
        }
        catch (IllegalArgumentException ex) {
            com.kongregate.android.internal.util.j.a("Invalid Kong Analytics URL");
            return false;
        }
    }
    
    public void b() {
        this.e();
    }
}
