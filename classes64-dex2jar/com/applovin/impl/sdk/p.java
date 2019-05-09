// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.HashSet;
import java.util.Collection;
import java.util.Iterator;
import com.applovin.sdk.AppLovinSdkUtils;
import com.applovin.sdk.AppLovinSdk;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.LinkedHashSet;
import com.applovin.sdk.AppLovinLogger;

final class p
{
    private final AppLovinSdkImpl a;
    private final AppLovinLogger b;
    private LinkedHashSet<n> c;
    private final Object d;
    private volatile boolean e;
    
    p(final AppLovinSdkImpl a) {
        this.d = new Object();
        this.a = a;
        this.b = a.getLogger();
        this.c = this.c();
    }
    
    private LinkedHashSet<n> b(final JSONArray jsonArray) {
        final LinkedHashSet<n> set = new LinkedHashSet<n>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); ++i) {
            final JSONObject a = bu.a(jsonArray, i, null, this.a);
            this.b.d("AdZoneManager", "Loading zone: " + a + "...");
            set.add(n.a(bu.a(a, "id", (String)null, this.a), a, this.a));
        }
        return set;
    }
    
    private LinkedHashSet<n> c() {
        Object iterator = new LinkedHashSet<n>();
        Object o;
        try {
            o = this.a.get(ef.e);
            Object b = iterator;
            if (AppLovinSdkUtils.isValidString((String)o)) {
                final JSONArray jsonArray = new JSONArray((String)o);
                if (jsonArray.length() > 0) {
                    b = this.b(jsonArray);
                }
                else {
                    this.b.d("AdZoneManager", "Unable to inflate json string: " + (String)o);
                    b = iterator;
                }
            }
            o = b;
            if (!((HashSet)b).isEmpty()) {
                this.b.d("AdZoneManager", "Retrieved persisted zones: " + b);
                iterator = ((HashSet<Object>)b).iterator();
                while (true) {
                    o = b;
                    if (!((Iterator)iterator).hasNext()) {
                        break;
                    }
                    ((Iterator<n>)iterator).next().a(this.a);
                }
            }
        }
        catch (Throwable t) {
            this.b.e("AdZoneManager", "Encountered error retrieving persisted zones", t);
            o = iterator;
            if (!((HashSet)iterator).isEmpty()) {
                this.b.d("AdZoneManager", "Retrieved persisted zones: " + iterator);
                final Iterator<n> iterator2 = ((HashSet<n>)iterator).iterator();
                while (true) {
                    o = iterator;
                    if (!iterator2.hasNext()) {
                        break;
                    }
                    iterator2.next().a(this.a);
                }
            }
        }
        finally {
            if (!((HashSet)iterator).isEmpty()) {
                this.b.d("AdZoneManager", "Retrieved persisted zones: " + iterator);
                final Iterator<n> iterator3 = ((HashSet<n>)iterator).iterator();
                while (iterator3.hasNext()) {
                    iterator3.next().a(this.a);
                }
            }
        }
        return (LinkedHashSet<n>)o;
    }
    
    private void c(final JSONArray jsonArray) {
        if (this.a.get(ea.cY)) {
            this.b.d("AdZoneManager", "Persisting zones...");
            this.a.put(ef.e, jsonArray.toString());
        }
    }
    
    public LinkedHashSet<n> a(final JSONArray jsonArray) {
        LinkedHashSet<n> set;
        if (jsonArray == null) {
            set = new LinkedHashSet<n>();
        }
        else {
            while (true) {
                LinkedHashSet<n> set2 = new LinkedHashSet<n>(jsonArray.length());
                while (true) {
                    synchronized (this.d) {
                        if (!this.e) {
                            this.b.d("AdZoneManager", "Found " + jsonArray.length() + " zone(s)...");
                            final LinkedHashSet<n> b = this.b(jsonArray);
                            set2 = new LinkedHashSet<n>(b);
                            set2.removeAll(this.c);
                            this.c = b;
                            this.e = true;
                            // monitorexit(this.d)
                            set = set2;
                            if (b != null) {
                                this.c(jsonArray);
                                this.b.d("AdZoneManager", "Finished loading zones");
                                return set2;
                            }
                            break;
                        }
                    }
                    final LinkedHashSet<n> b = null;
                    continue;
                }
            }
        }
        return set;
    }
    
    public boolean a() {
        return this.e;
    }
    
    public boolean a(final n n) {
        synchronized (this.d) {
            return this.c.contains(n);
        }
    }
    
    public LinkedHashSet<n> b() {
        synchronized (this.d) {
            return this.c;
        }
    }
}
