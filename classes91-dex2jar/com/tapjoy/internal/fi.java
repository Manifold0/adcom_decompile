// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.os.SystemClock;
import java.util.HashSet;
import java.util.Collection;
import java.util.TreeMap;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public abstract class fi
{
    static Set a;
    private static final ThreadLocal b;
    private static fi c;
    private static volatile boolean d;
    
    static {
        b = new ThreadLocal() {};
        fi.d = false;
        fi.a = null;
    }
    
    public static a a(final String s) {
        final a a = new a(s).a();
        fi.b.get().put(s, a);
        return a;
    }
    
    public static void a(final fk c) {
        if (fi.c == null) {
            fi.c = c;
            if (fi.d) {
                c.a(y.b());
            }
        }
    }
    
    public static void a(String a, final a a2) {
        if (a2 == null) {
            return;
        }
        if (a.equals(a2.a)) {
            fi.b.get().put(a, a2);
            return;
        }
        a = a2.a;
    }
    
    public static void a(final String s, final TreeMap treeMap, final Map map) {
        String a;
        if (treeMap != null) {
            a = bm.a((Object)treeMap);
        }
        else {
            a = null;
        }
        b(s, a, map);
    }
    
    public static void a(final Collection collection) {
        if (collection == null || collection.isEmpty()) {
            fi.a = null;
            return;
        }
        fi.a = new HashSet(collection);
    }
    
    public static void a(final boolean d) {
        if (fi.d != d) {
            fi.d = d;
            if (fi.c != null) {
                if (!d) {
                    fi.c.a();
                    return;
                }
                fi.c.a(y.b());
            }
        }
    }
    
    public static a b(final String s) {
        final a a = fi.b.get().remove(s);
        if (a != null) {
            return a.b();
        }
        return new a(s);
    }
    
    private static void b(final String s, final String s2, final Map map) {
        final Set a = fi.a;
        if ((a == null || !a.contains(s)) && fi.d && fi.c != null) {
            fi.c.a(y.b(), s, s2, map);
        }
    }
    
    public static a c(final String s) {
        return fi.b.get().get(s);
    }
    
    public static a d(final String s) {
        return fi.b.get().remove(s);
    }
    
    public static a e(final String s) {
        return new a(s);
    }
    
    protected abstract void a();
    
    protected abstract void a(final long p0);
    
    protected abstract void a(final long p0, final String p1, final String p2, final Map p3);
    
    public static final class a
    {
        final String a;
        private final TreeMap b;
        private final Map c;
        private volatile long d;
        
        a(final String a) {
            this.b = new TreeMap();
            this.c = new HashMap();
            this.a = a;
        }
        
        public final a a() {
            try {
                this.d = SystemClock.elapsedRealtime();
                return this;
            }
            catch (NullPointerException ex) {
                this.d = -1L;
                return this;
            }
        }
        
        public final a a(final String s) {
            this.b.put("failure", s);
            return this;
        }
        
        public final a a(final String s, final long n) {
            this.c.put(s, n);
            return this;
        }
        
        public final a a(final String s, final Object o) {
            this.b.put(s, o);
            return this;
        }
        
        public final a a(final Map map) {
            if (map != null) {
                this.b.putAll(map);
            }
            return this;
        }
        
        public final a b() {
            final long d = this.d;
            if (d == -1L) {
                return this;
            }
            try {
                this.a("spent_time", SystemClock.elapsedRealtime() - d);
                return this;
            }
            catch (NullPointerException ex) {
                return this;
            }
        }
        
        public final a b(final String s) {
            this.b.put("misuse", s);
            return this;
        }
        
        public final a b(final Map map) {
            if (map != null) {
                this.c.putAll(map);
            }
            return this;
        }
        
        public final void c() {
            Map c = null;
            final String a = this.a;
            String a2;
            if (this.b.size() > 0) {
                a2 = bm.a((Object)this.b);
            }
            else {
                a2 = null;
            }
            if (this.c.size() > 0) {
                c = this.c;
            }
            b(a, a2, c);
        }
    }
}
