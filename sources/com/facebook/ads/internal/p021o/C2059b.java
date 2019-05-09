package com.facebook.ads.internal.p021o;

import java.util.ArrayList;
import java.util.List;

/* renamed from: com.facebook.ads.internal.o.b */
public class C2059b {
    /* renamed from: a */
    private static final List<C2058a> f4619a = new ArrayList();

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public static java.lang.String m5022a() {
        /*
        r1 = f4619a;
        monitor-enter(r1);
        r0 = f4619a;	 Catch:{ all -> 0x0039 }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x0039 }
        if (r0 == 0) goto L_0x000f;
    L_0x000b:
        r0 = "";
        monitor-exit(r1);	 Catch:{ all -> 0x0039 }
    L_0x000e:
        return r0;
    L_0x000f:
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x0039 }
        r2 = f4619a;	 Catch:{ all -> 0x0039 }
        r0.<init>(r2);	 Catch:{ all -> 0x0039 }
        r2 = f4619a;	 Catch:{ all -> 0x0039 }
        r2.clear();	 Catch:{ all -> 0x0039 }
        monitor-exit(r1);	 Catch:{ all -> 0x0039 }
        r1 = new org.json.JSONArray;
        r1.<init>();
        r2 = r0.iterator();
    L_0x0025:
        r0 = r2.hasNext();
        if (r0 == 0) goto L_0x003c;
    L_0x002b:
        r0 = r2.next();
        r0 = (com.facebook.ads.internal.p021o.C2058a) r0;
        r0 = r0.m5021a();
        r1.put(r0);
        goto L_0x0025;
    L_0x0039:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0039 }
        throw r0;
    L_0x003c:
        r0 = r1.toString();
        goto L_0x000e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.o.b.a():java.lang.String");
    }

    /* renamed from: a */
    public static void m5023a(C2058a c2058a) {
        synchronized (f4619a) {
            f4619a.add(c2058a);
        }
    }
}
