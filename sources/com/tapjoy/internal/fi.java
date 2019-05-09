package com.tapjoy.internal;

import android.os.SystemClock;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public abstract class fi {
    /* renamed from: a */
    static Set f7766a = null;
    /* renamed from: b */
    private static final ThreadLocal f7767b = new C29251();
    /* renamed from: c */
    private static fi f7768c;
    /* renamed from: d */
    private static volatile boolean f7769d = false;

    /* renamed from: com.tapjoy.internal.fi$1 */
    static class C29251 extends ThreadLocal {
        C29251() {
        }

        protected final /* synthetic */ Object initialValue() {
            return new HashMap();
        }
    }

    /* renamed from: com.tapjoy.internal.fi$a */
    public static final class C2926a {
        /* renamed from: a */
        final String f7762a;
        /* renamed from: b */
        private final TreeMap f7763b = new TreeMap();
        /* renamed from: c */
        private final Map f7764c = new HashMap();
        /* renamed from: d */
        private volatile long f7765d;

        C2926a(String str) {
            this.f7762a = str;
        }

        /* renamed from: a */
        public final C2926a m7734a() {
            try {
                this.f7765d = SystemClock.elapsedRealtime();
            } catch (NullPointerException e) {
                this.f7765d = -1;
            }
            return this;
        }

        /* renamed from: b */
        public final C2926a m7739b() {
            long j = this.f7765d;
            if (j != -1) {
                try {
                    m7736a("spent_time", SystemClock.elapsedRealtime() - j);
                } catch (NullPointerException e) {
                }
            }
            return this;
        }

        /* renamed from: a */
        public final C2926a m7737a(String str, Object obj) {
            this.f7763b.put(str, obj);
            return this;
        }

        /* renamed from: a */
        public final C2926a m7738a(Map map) {
            if (map != null) {
                this.f7763b.putAll(map);
            }
            return this;
        }

        /* renamed from: a */
        public final C2926a m7735a(String str) {
            this.f7763b.put("failure", str);
            return this;
        }

        /* renamed from: b */
        public final C2926a m7740b(String str) {
            this.f7763b.put("misuse", str);
            return this;
        }

        /* renamed from: a */
        public final C2926a m7736a(String str, long j) {
            this.f7764c.put(str, Long.valueOf(j));
            return this;
        }

        /* renamed from: b */
        public final C2926a m7741b(Map map) {
            if (map != null) {
                this.f7764c.putAll(map);
            }
            return this;
        }

        /* renamed from: c */
        public final void m7742c() {
            String a;
            Map map = null;
            String str = this.f7762a;
            if (this.f7763b.size() > 0) {
                a = bm.m7204a(this.f7763b);
            } else {
                a = null;
            }
            if (this.f7764c.size() > 0) {
                map = this.f7764c;
            }
            fi.m7751b(str, a, map);
        }
    }

    /* renamed from: a */
    protected abstract void mo6283a();

    /* renamed from: a */
    protected abstract void mo6284a(long j);

    /* renamed from: a */
    protected abstract void mo6285a(long j, String str, String str2, Map map);

    /* renamed from: a */
    public static void m7744a(fk fkVar) {
        if (f7768c == null) {
            f7768c = fkVar;
            if (f7769d) {
                fkVar.mo6284a(C2999y.m8233b());
            }
        }
    }

    /* renamed from: a */
    public static void m7749a(boolean z) {
        if (f7769d != z) {
            f7769d = z;
            if (f7768c == null) {
                return;
            }
            if (z) {
                f7768c.mo6284a(C2999y.m8233b());
            } else {
                f7768c.mo6283a();
            }
        }
    }

    /* renamed from: a */
    public static void m7748a(Collection collection) {
        if (collection == null || collection.isEmpty()) {
            f7766a = null;
        } else {
            f7766a = new HashSet(collection);
        }
    }

    /* renamed from: b */
    private static void m7751b(String str, String str2, Map map) {
        Set set = f7766a;
        if ((set == null || !set.contains(str)) && f7769d && f7768c != null) {
            f7768c.mo6285a(C2999y.m8233b(), str, str2, map);
        }
    }

    /* renamed from: a */
    public static void m7747a(String str, TreeMap treeMap, Map map) {
        m7751b(str, treeMap != null ? bm.m7204a((Object) treeMap) : null, map);
    }

    /* renamed from: a */
    public static C2926a m7743a(String str) {
        C2926a a = new C2926a(str).m7734a();
        ((Map) f7767b.get()).put(str, a);
        return a;
    }

    /* renamed from: b */
    public static C2926a m7750b(String str) {
        C2926a c2926a = (C2926a) ((Map) f7767b.get()).remove(str);
        return c2926a != null ? c2926a.m7739b() : new C2926a(str);
    }

    /* renamed from: c */
    public static C2926a m7752c(String str) {
        return (C2926a) ((Map) f7767b.get()).get(str);
    }

    /* renamed from: d */
    public static C2926a m7753d(String str) {
        return (C2926a) ((Map) f7767b.get()).remove(str);
    }

    /* renamed from: a */
    public static void m7745a(String str, C2926a c2926a) {
        if (c2926a == null) {
            new Object[1][0] = str;
        } else if (str.equals(c2926a.f7762a)) {
            ((Map) f7767b.get()).put(str, c2926a);
        } else {
            Object[] objArr = new Object[]{str, c2926a.f7762a};
        }
    }

    /* renamed from: e */
    public static C2926a m7754e(String str) {
        return new C2926a(str);
    }
}
