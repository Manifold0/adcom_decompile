// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import android.text.TextUtils;
import java.util.HashSet;
import java.util.Collection;

class b
{
    static final b a;
    static final b b;
    static final b c;
    static final b d;
    static final b e;
    static final b f;
    static final b g;
    static final b h;
    static final b i;
    static final b j;
    static final b k;
    static final b l;
    static final b m;
    static final b n;
    static final b o;
    static final b p;
    static final b q;
    static final b r;
    private static final Collection<String> t;
    private final String s;
    
    static {
        t = new HashSet<String>(18);
        a = a("srt");
        b = a("sft");
        c = a("sfs");
        d = a("sadb");
        e = a("sacb");
        f = a("stdl");
        g = a("stdi");
        h = a("snas");
        i = a("snat");
        j = a("stah");
        k = a("stas");
        l = a("stac");
        m = a("stbe");
        n = a("stbc");
        o = a("saan");
        p = a("suvs");
        q = a("svpv");
        r = a("stpd");
    }
    
    private b(final String s) {
        this.s = s;
    }
    
    private static b a(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("No key name specified");
        }
        if (com.applovin.impl.sdk.b.t.contains(s)) {
            throw new IllegalArgumentException("Key has already been used: " + s);
        }
        com.applovin.impl.sdk.b.t.add(s);
        return new b(s);
    }
    
    public String a() {
        return this.s;
    }
}
