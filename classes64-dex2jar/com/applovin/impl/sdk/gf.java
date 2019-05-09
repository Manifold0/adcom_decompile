// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.List;

public class gf
{
    public static final gf a;
    protected String b;
    protected final List<gf> c;
    private final gf d;
    private final String e;
    private final Map<String, String> f;
    
    static {
        a = new gf();
    }
    
    private gf() {
        this.d = null;
        this.e = "";
        this.f = Collections.emptyMap();
        this.b = "";
        this.c = Collections.emptyList();
    }
    
    public gf(final String e, final Map<String, String> map, final gf d) {
        this.d = d;
        this.e = e;
        this.f = Collections.unmodifiableMap((Map<? extends String, ? extends String>)map);
        this.c = new ArrayList<gf>();
    }
    
    public String a() {
        return this.e;
    }
    
    public List<gf> a(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("No name specified.");
        }
        final ArrayList<gf> list = new ArrayList<gf>(this.c.size());
        for (final gf gf : this.c) {
            if (s.equalsIgnoreCase(gf.a())) {
                list.add(gf);
            }
        }
        return list;
    }
    
    public gf b(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("No name specified.");
        }
        for (final gf gf : this.c) {
            if (s.equalsIgnoreCase(gf.a())) {
                return gf;
            }
        }
        return null;
    }
    
    public Map<String, String> b() {
        return this.f;
    }
    
    public gf c(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("No name specified.");
        }
        if (this.c.size() > 0) {
            final ArrayList<gf> list = new ArrayList<gf>();
            list.add(this);
            while (!list.isEmpty()) {
                final gf gf = list.get(0);
                list.remove(0);
                if (s.equalsIgnoreCase(gf.a())) {
                    return gf;
                }
                list.addAll((Collection<?>)gf.d());
            }
        }
        return null;
    }
    
    public String c() {
        return this.b;
    }
    
    public List<gf> d() {
        return Collections.unmodifiableList((List<? extends gf>)this.c);
    }
    
    @Override
    public String toString() {
        return "XmlNode{, elementName='" + this.e + '\'' + ", text='" + this.b + '\'' + ", attributes=" + this.f + '}';
    }
}
