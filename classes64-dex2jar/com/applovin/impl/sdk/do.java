// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.Collection;
import java.util.Map;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.HashSet;
import com.applovin.sdk.AppLovinPostbackListener;
import java.util.Iterator;
import java.util.LinkedHashSet;
import org.json.JSONObject;
import android.content.SharedPreferences;
import java.util.ArrayList;
import com.applovin.sdk.AppLovinLogger;

public class do
{
    private final AppLovinSdkImpl a;
    private final AppLovinLogger b;
    private ArrayList<dq> c;
    private ArrayList<dq> d;
    private final Object e;
    private final SharedPreferences f;
    
    do(final AppLovinSdkImpl a) {
        if (a == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.a = a;
        this.b = a.getLogger();
        this.f = a.getApplicationContext().getSharedPreferences("com.applovin.sdk.impl.postbackQueue.domain", 0);
        this.e = new Object();
        this.c = this.c();
        this.d = new ArrayList<dq>();
    }
    
    private void a(final dq dq) {
        synchronized (this.e) {
            this.b(dq);
            this.c(dq);
        }
    }
    
    private dq b(final String s) {
        try {
            final JSONObject jsonObject = new JSONObject(s);
            return new dq(jsonObject.getString("targetUrl"), bu.a(jsonObject.getJSONObject("requestBody")), jsonObject.getInt("attemptNumber"), jsonObject.getString("backupUrl"));
        }
        catch (Exception ex) {
            this.b.w("PersistentPostbackManager", "Unable to inflate postback request from JSON.", ex);
            return null;
        }
    }
    
    private void b(final dq dq) {
        synchronized (this.e) {
            if (this.c.size() < this.a.get(ea.bT)) {
                this.c.add(dq);
                this.d();
                this.b.d("PersistentPostbackManager", "Enqueued postback: " + dq);
            }
            else {
                this.b.w("PersistentPostbackManager", "Persistent queue has reached maximum size; postback retried in memory only." + dq);
            }
        }
    }
    
    private ArrayList<dq> c() {
        if (ab.b()) {
            final LinkedHashSet<String> set = this.a.get((ef<LinkedHashSet<String>>)ef.b, new LinkedHashSet<String>(0), this.f);
            final ArrayList list = new ArrayList<dq>(Math.max(1, set.size()));
            final int intValue = this.a.get(ea.bU);
            this.b.d("PersistentPostbackManager", "Deserializing " + set.size() + " postback(s).");
            for (final String s : set) {
                final dq b = this.b(s);
                if (b != null) {
                    if (b.a() > intValue) {
                        list.add(b);
                    }
                    else {
                        this.b.d("PersistentPostbackManager", "Skipping deserialization because maximum attempt count exceeded for postback: " + b);
                    }
                }
                else {
                    this.b.e("PersistentPostbackManager", "Unable to deserialize postback json: " + s);
                }
            }
            this.b.d("PersistentPostbackManager", "Successfully loaded postback queue with " + list.size() + " postback(s).");
            return (ArrayList<dq>)list;
        }
        this.b.d("PersistentPostbackManager", "Loading new postback queue due to old Android version...");
        return new ArrayList<dq>();
    }
    
    private void c(final dq dq) {
        this.b.d("PersistentPostbackManager", "Preparing to submit postback..." + dq);
        if (this.a.e()) {
            this.b.d("PersistentPostbackManager", "Skipping postback dispatch because SDK is still initializing - postback will be dispatched afterwards");
            return;
        }
        synchronized (this.e) {
            dq.a(dq.a() + 1);
            this.d();
            // monitorexit(this.e)
            final int intValue = this.a.get(ea.bU);
            if (dq.a() > intValue) {
                this.b.w("PersistentPostbackManager", "Exceeded maximum persisted attempt count of " + intValue + ". Dequeuing postback: " + dq);
                this.d(dq);
                return;
            }
        }
        final dq dq2;
        this.a.getPostbackService().dispatchPostbackAsync(dq2.b(), dq2.d(), dq2.c(), new dp(this, dq2));
    }
    
    private void d() {
        if (ab.c()) {
            final LinkedHashSet<String> set = new LinkedHashSet<String>(this.c.size());
            final Iterator<dq> iterator = this.c.iterator();
            while (iterator.hasNext()) {
                final String f = this.f(iterator.next());
                if (f != null) {
                    set.add(f);
                }
            }
            this.a.put(ef.b, set);
            this.b.d("PersistentPostbackManager", "Wrote updated postback queue to disk.");
            return;
        }
        this.b.d("PersistentPostbackManager", "Skipping writing postback queue to disk due to old Android version...");
    }
    
    private void d(final dq dq) {
        synchronized (this.e) {
            this.c.remove(dq);
            this.d();
            // monitorexit(this.e)
            this.b.d("PersistentPostbackManager", "Dequeued successfully transmitted postback: " + dq);
        }
    }
    
    private void e(final dq dq) {
        synchronized (this.e) {
            this.d.add(dq);
        }
    }
    
    private String f(final dq dq) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("attemptNumber", dq.a());
            jsonObject.put("targetUrl", (Object)dq.b());
            final String c = dq.c();
            if (AppLovinSdkUtils.isValidString(c)) {
                jsonObject.put("backupUrl", (Object)c);
            }
            final Map<String, String> d = dq.d();
            if (d != null) {
                jsonObject.put("requestBody", (Object)new JSONObject((Map)d));
            }
            return jsonObject.toString();
        }
        catch (Exception ex) {
            this.b.w("PersistentPostbackManager", "Unable to serialize postback request to JSON.", ex);
            return null;
        }
    }
    
    public void a() {
        synchronized (this.e) {
            if (this.c != null) {
                final Iterator<dq> iterator = new ArrayList<dq>(this.c).iterator();
                while (iterator.hasNext()) {
                    this.c(iterator.next());
                }
            }
        }
    }
    // monitorexit(o)
    
    public void a(final String s) {
        this.a(s, null);
    }
    
    public void a(final String s, final Map<String, String> map) {
        this.a(s, map, true);
    }
    
    public void a(final String s, final Map<String, String> map, final boolean b) {
        this.a(s, map, b, null);
    }
    
    public void a(String s, final Map<String, String> map, final boolean b, final String s2) {
        if (!AppLovinSdkUtils.isValidString(s)) {
            return;
        }
        String string = s;
        String string2 = s2;
        if (b) {
            final String string3 = "&postback_ts=" + System.currentTimeMillis();
            s = (string = s + string3);
            string2 = s2;
            if (AppLovinSdkUtils.isValidString(s2)) {
                string2 = s2 + string3;
                string = s;
            }
        }
        this.a(new dq(string, map, 0, string2));
    }
    
    public void b() {
        synchronized (this.e) {
            final Iterator<dq> iterator = this.d.iterator();
            while (iterator.hasNext()) {
                this.c(iterator.next());
            }
        }
        this.d.clear();
    }
    // monitorexit(o)
}
