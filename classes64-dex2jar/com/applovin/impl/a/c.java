// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.a;

import java.util.Set;
import com.applovin.impl.sdk.AppLovinSdkImpl;
import org.json.JSONObject;

public class c
{
    private JSONObject a;
    private JSONObject b;
    private AppLovinSdkImpl c;
    private long d;
    private String e;
    private String f;
    private k g;
    private o h;
    private f i;
    private Set<l> j;
    private Set<l> k;
    
    private c() {
    }
    
    public a a() {
        return new a(this, null);
    }
    
    public c a(final long d) {
        this.d = d;
        return this;
    }
    
    public c a(final f i) {
        this.i = i;
        return this;
    }
    
    public c a(final k g) {
        this.g = g;
        return this;
    }
    
    public c a(final o h) {
        this.h = h;
        return this;
    }
    
    public c a(final AppLovinSdkImpl c) {
        if (c == null) {
            throw new IllegalArgumentException("No sdk specified.");
        }
        this.c = c;
        return this;
    }
    
    public c a(final String e) {
        this.e = e;
        return this;
    }
    
    public c a(final Set<l> j) {
        this.j = j;
        return this;
    }
    
    public c a(final JSONObject a) {
        if (a == null) {
            throw new IllegalArgumentException("No ad object specified.");
        }
        this.a = a;
        return this;
    }
    
    public c b(final String f) {
        this.f = f;
        return this;
    }
    
    public c b(final Set<l> k) {
        this.k = k;
        return this;
    }
    
    public c b(final JSONObject b) {
        if (b == null) {
            throw new IllegalArgumentException("No full ad response specified.");
        }
        this.b = b;
        return this;
    }
}
