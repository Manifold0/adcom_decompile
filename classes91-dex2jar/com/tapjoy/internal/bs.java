// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.io.ByteArrayInputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.net.URISyntaxException;
import java.net.URI;
import java.util.List;
import java.io.InputStream;
import java.util.HashMap;

public abstract class bs implements bp, bu
{
    private HashMap a;
    
    public static bs a(final InputStream inputStream) {
        return a.a().a(inputStream);
    }
    
    private void a(final List list) {
        this.f();
        while (this.j()) {
            list.add(this.u());
        }
        this.g();
    }
    
    public static bs b(final String s) {
        return a.a().a(s);
    }
    
    private static URI d(final String s) {
        try {
            return new URI(s);
        }
        catch (URISyntaxException ex) {
            throw new ca(ex);
        }
    }
    
    private boolean t() {
        if (this.k() == bx.i) {
            this.o();
            return true;
        }
        return false;
    }
    
    private Object u() {
        final bx k = this.k();
        switch (bs$1.a[k.ordinal()]) {
            default: {
                throw new IllegalStateException("Expected a value but was " + k);
            }
            case 1: {
                return this.c();
            }
            case 2: {
                return this.d();
            }
            case 3: {
                this.o();
                return null;
            }
            case 4: {
                return this.n();
            }
            case 5: {
                return new cn(this.m());
            }
            case 6: {
                return this.m();
            }
        }
    }
    
    public final Object a(final bn bn) {
        if (this.t()) {
            return null;
        }
        return bn.a(this);
    }
    
    @Override
    public final Object a(final String s) {
        if (this.a != null) {
            return this.a.get(s);
        }
        return null;
    }
    
    @Override
    public final void a(final String s, final Object o) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(s, o);
    }
    
    public final void a(final List list, final bn bn) {
        this.f();
        while (this.j()) {
            list.add(bn.a(this));
        }
        this.g();
    }
    
    public final void a(final Map map) {
        this.h();
        while (this.j()) {
            map.put(this.l(), this.u());
        }
        this.i();
    }
    
    public final boolean a() {
        return this.k() == bx.c;
    }
    
    public final String b() {
        if (this.t()) {
            return null;
        }
        return this.m();
    }
    
    public final String c(final String s) {
        if (this.t()) {
            return s;
        }
        return this.m();
    }
    
    public final List c() {
        final LinkedList list = new LinkedList();
        this.a(list);
        return list;
    }
    
    public final Map d() {
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.a(linkedHashMap);
        return linkedHashMap;
    }
    
    public final URL e() {
        final URI uri = (URI)this.a("BASE_URI");
        if (uri != null) {
            return uri.resolve(d(this.m())).toURL();
        }
        return new URL(this.m());
    }
    
    public abstract static class a
    {
        private static a a;
        
        public static a a() {
            a a;
            if ((a = bs.a.a) == null) {
                a = (bs.a.a = bt.a);
            }
            return a;
        }
        
        public final bs a(final InputStream inputStream) {
            return this.a(new InputStreamReader(inputStream, cp.c));
        }
        
        public bs a(final Reader reader) {
            return this.a(db.a(reader));
        }
        
        public bs a(final String s) {
            return this.a(new ByteArrayInputStream(s.getBytes(cp.c.name())));
        }
    }
}
