package com.tapjoy.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.ConcurrentHashMap;

public class fm extends Observable {
    /* renamed from: b */
    public final List f7716b = new ArrayList();

    /* renamed from: com.tapjoy.internal.fm$a */
    public class C2928a {
        /* renamed from: a */
        public final String f7789a;
        /* renamed from: b */
        public volatile Map f7790b = new ConcurrentHashMap();
        /* renamed from: c */
        final /* synthetic */ fm f7791c;

        C2928a(fm fmVar, String str) {
            this.f7791c = fmVar;
            this.f7789a = str;
        }

        /* renamed from: a */
        public final Object m7769a(String str) {
            Map map = this.f7790b;
            return map != null ? map.get(str) : null;
        }
    }

    /* renamed from: a */
    protected final C2928a m7707a(String str) {
        C2928a c2928a = new C2928a(this, str);
        this.f7716b.add(c2928a);
        return c2928a;
    }

    protected void setChanged() {
        super.setChanged();
        notifyObservers();
    }

    /* renamed from: b */
    public final boolean m7709b(String str) {
        return m7708a(str, false);
    }

    /* renamed from: a */
    public final boolean m7708a(String str, boolean z) {
        for (C2928a a : this.f7716b) {
            Object a2 = a.m7769a(str);
            if (a2 != null) {
                if (a2 instanceof Boolean) {
                    return ((Boolean) a2).booleanValue();
                }
                if (!(a2 instanceof String)) {
                    continue;
                } else if ("true".equals(a2)) {
                    return true;
                } else {
                    if ("false".equals(a2)) {
                        return false;
                    }
                }
            }
        }
        return z;
    }

    /* renamed from: c */
    public final long m7710c(String str) {
        for (C2928a a : this.f7716b) {
            Object a2 = a.m7769a(str);
            if (a2 != null) {
                if (a2 instanceof Number) {
                    return ((Number) a2).longValue();
                }
                if (a2 instanceof String) {
                    try {
                        return Long.parseLong((String) a2);
                    } catch (IllegalArgumentException e) {
                    }
                } else {
                    continue;
                }
            }
        }
        return 0;
    }

    /* renamed from: a */
    private static long m7706a(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        if (obj instanceof String) {
            return Long.parseLong((String) obj);
        }
        throw new IllegalArgumentException();
    }

    /* renamed from: d */
    public final fl m7711d(String str) {
        for (C2928a a : this.f7716b) {
            Object a2 = a.m7769a(str);
            if (a2 instanceof List) {
                List list = (List) a2;
                try {
                    double doubleValue;
                    long a3 = m7706a(list.get(0));
                    long a4 = m7706a(list.get(1));
                    long a5 = m7706a(list.get(2));
                    a2 = list.get(3);
                    if (a2 instanceof Number) {
                        doubleValue = ((Number) a2).doubleValue();
                    } else if (a2 instanceof String) {
                        doubleValue = Double.parseDouble((String) a2);
                    } else {
                        throw new IllegalArgumentException();
                    }
                    return new fl(a3, a4, a5, doubleValue);
                } catch (RuntimeException e) {
                }
            }
        }
        return fl.f7783a;
    }
}
