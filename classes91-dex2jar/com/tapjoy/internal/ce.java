// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.LinkedHashMap;
import java.util.Map;
import java.io.InputStream;
import java.net.URI;

public abstract class ce extends cf
{
    public abstract Object a(final bs p0);
    
    @Override
    public final Object a(final URI uri, InputStream a) {
        final InputStream inputStream = null;
        final bs a2 = bs.a(a);
        a2.a("BASE_URI", uri);
        int r;
        try {
            a2.h();
            r = 0;
            a = inputStream;
            while (a2.j()) {
                final String l = a2.l();
                if ("status".equals(l)) {
                    r = a2.r();
                }
                else if ("message".equals(l)) {
                    a2.m();
                }
                else if ("data".equals(l)) {
                    a = (InputStream)this.a(a2);
                }
                else {
                    a2.s();
                }
            }
        }
        finally {
            a2.close();
        }
        a2.i();
        if (r != 200) {
            final String s;
            throw new cg(r, s);
        }
        a2.close();
        return a;
    }
    
    @Override
    public final Map a() {
        final LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        linkedHashMap.put("Accept", "application/json");
        return linkedHashMap;
    }
}
