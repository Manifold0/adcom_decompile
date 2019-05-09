// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Iterator;
import java.util.Hashtable;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class fb extends fm
{
    static final Map a;
    private final a c;
    private final a d;
    private final a e;
    private final a f;
    private final a g;
    
    static {
        a = Collections.unmodifiableMap((Map<?, ?>)new HashMap<Object, Object>());
    }
    
    fb() {
        this.c = this.a("BuildConfig");
        this.d = this.a("ServerFinal");
        this.e = this.a("AppRuntime");
        this.e.b = new ConcurrentHashMap();
        this.f = this.a("ConnectFlags");
        this.g = this.a("ServerDefault");
        final a a = this.a("SDKDefault");
        Label_0098: {
            if ("".isEmpty()) {
                break Label_0098;
            }
            try {
                this.c.b = bs.b("").d();
                final HashMap<String, Integer> b = new HashMap<String, Integer>();
                b.put("placement_request_content_retry_timeout", -1);
                b.put("placement_request_content_retry_backoff", (Integer)Arrays.asList(0L, 500L, 10000L, 2.0));
                a.b = b;
            }
            catch (IOException ex) {
                throw new Error("BuildConfig.TJC_CONFIGURATION malformed", ex);
            }
        }
    }
    
    public final void a(final Hashtable hashtable) {
        final HashMap<String, Object> b = new HashMap<String, Object>();
        for (final Map.Entry<Object, V> entry : hashtable.entrySet()) {
            String s;
            if ((s = fb.a.get(entry.getKey())) == null) {
                s = entry.getKey();
            }
            this.e.b.remove(s);
            b.put(s, entry.getValue());
        }
        this.f.b = b;
        this.setChanged();
    }
    
    public final void a(Map b) {
        Map<K, Map> b2 = null;
        if (b != null) {
            final Map<K, Map> map = ((Map<K, Map<K, Map>>)b).get("final");
            b2 = ((Map<K, Map<K, Map>>)b).get("default");
            b = map;
        }
        else {
            b = null;
        }
        this.d.b = (Map)b;
        this.g.b = b2;
        this.setChanged();
    }
}
