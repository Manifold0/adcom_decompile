// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.HashSet;

public class ef<T>
{
    static final ef<String> a;
    static final ef<HashSet> b;
    static final ef<Integer> c;
    static final ef<String> d;
    static final ef<String> e;
    static final ef<String> f;
    static final ef<Boolean> g;
    static final ef<Boolean> h;
    static final ef<String> i;
    static final ef<HashSet> j;
    public static final ef<Integer> k;
    public static final ef<Boolean> l;
    private final String m;
    private final Class<T> n;
    
    static {
        a = new ef<String>("com.applovin.sdk.impl.isFirstRun", String.class);
        b = new ef<HashSet>("com.applovin.sdk.impl.postbackQueue.key", HashSet.class);
        c = new ef<Integer>("com.applovin.sdk.last_version_code", Integer.class);
        d = new ef<String>("com.applovin.sdk.device_data", String.class);
        e = new ef<String>("com.applovin.sdk.zones", String.class);
        f = new ef<String>("com.applovin.sdk.loaded_mediation_adapters", String.class);
        g = new ef<Boolean>("com.applovin.sdk.compliance.has_user_consent", Boolean.class);
        h = new ef<Boolean>("com.applovin.sdk.compliance.is_age_restricted_user", Boolean.class);
        i = new ef<String>("com.applovin.sdk.stats", String.class);
        j = new ef<HashSet>("com.applovin.sdk.ad.stats", HashSet.class);
        k = new ef<Integer>("com.applovin.sdk.last_video_position", Integer.class);
        l = new ef<Boolean>("com.applovin.sdk.should_resume_video", Boolean.class);
    }
    
    ef(final String m, final Class<T> n) {
        this.m = m;
        this.n = n;
    }
    
    String a() {
        return this.m;
    }
    
    Class<T> b() {
        return this.n;
    }
    
    @Override
    public String toString() {
        return "Key{name='" + this.m + '\'' + "type='" + this.n + '\'' + '}';
    }
}
