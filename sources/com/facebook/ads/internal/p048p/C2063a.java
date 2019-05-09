package com.facebook.ads.internal.p048p;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import com.facebook.ads.internal.p046v.p047a.C2124b;
import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.facebook.ads.internal.p.a */
public abstract class C2063a {
    /* renamed from: a */
    private static final String f4622a = C2063a.class.getSimpleName();
    /* renamed from: b */
    private static final AtomicBoolean f4623b = new AtomicBoolean();
    /* renamed from: c */
    private static C2124b f4624c;
    /* renamed from: d */
    private static String f4625d;
    /* renamed from: e */
    private static LinkedHashMap<String, String> f4626e;
    /* renamed from: f */
    private static String f4627f;

    /* renamed from: a */
    public static String m5033a(int i) {
        StringBuilder stringBuilder = new StringBuilder(i);
        String str = "bbbbbbbbbb";
        for (int i2 = 0; i2 < i / str.length(); i2++) {
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

    @SuppressLint({"CatchGeneralException"})
    /* renamed from: a */
    public static void m5034a(Context context, C2124b c2124b) {
        if (!f4623b.get() && (context instanceof Application)) {
            f4627f = context.getPackageName();
            f4624c = c2124b;
            f4625d = UUID.randomUUID().toString();
            f4623b.compareAndSet(false, true);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.annotation.SuppressLint({"CatchGeneralException"})
    /* renamed from: a */
    public static void m5035a(android.content.Context r6, java.lang.String r7, java.lang.String r8) {
        /*
        r1 = 0;
        r0 = f4623b;
        r0 = r0.get();
        if (r0 == 0) goto L_0x001b;
    L_0x0009:
        r0 = com.facebook.ads.internal.p050r.C2078a.m5116z(r6);
        if (r0 == 0) goto L_0x001b;
    L_0x000f:
        r2 = java.lang.Math.random();
        r4 = com.facebook.ads.internal.p050r.C2078a.m5063B(r6);
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 <= 0) goto L_0x001c;
    L_0x001b:
        return;
    L_0x001c:
        r0 = new java.util.LinkedHashMap;
        r0.<init>();
        f4626e = r0;
        r0 = f4626e;
        r2 = com.facebook.ads.internal.p048p.C2064b.ti;
        r2 = r2.toString();
        r0.put(r2, r8);
        r0 = f4626e;
        r2 = com.facebook.ads.internal.p048p.C2064b.bt;
        r2 = r2.toString();
        r3 = "AN_ANDROID";
        r0.put(r2, r3);
        r0 = f4626e;
        r2 = com.facebook.ads.internal.p048p.C2064b.sn;
        r2 = r2.toString();
        r0.put(r2, r7);
        r0 = f4626e;
        r2 = com.facebook.ads.internal.p048p.C2064b.ap;
        r2 = r2.toString();
        r3 = f4627f;
        r0.put(r2, r3);
        r0 = f4626e;
        r2 = com.facebook.ads.internal.p048p.C2064b.r1;
        r2 = r2.toString();
        r3 = f4625d;
        r0.put(r2, r3);
        r0 = new com.facebook.ads.internal.v.a.p;
        r0.<init>();
        r2 = f4626e;
        r0 = r0.m5508a(r2);
        r0 = r0.m5509a();
        r2 = com.facebook.ads.internal.p050r.C2078a.m5064C(r6);
        if (r2 == 0) goto L_0x00b0;
    L_0x0075:
        r2 = java.lang.Math.random();
        r4 = com.facebook.ads.internal.p050r.C2078a.m5066E(r6);
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 > 0) goto L_0x00b0;
    L_0x0081:
        r2 = com.facebook.ads.internal.p050r.C2078a.m5065D(r6);
        r2 = com.facebook.ads.internal.p048p.C2063a.m5033a(r2);
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r0 = r3.append(r0);
        r3 = "&";
        r0 = r0.append(r3);
        r3 = com.facebook.ads.internal.p048p.C2064b._p;
        r3 = r3.toString();
        r0 = r0.append(r3);
        r3 = "=";
        r0 = r0.append(r3);
        r0 = r0.append(r2);
        r0 = r0.toString();
    L_0x00b0:
        r1 = com.facebook.ads.internal.p025w.p057e.C2612d.m6709a(r6);	 Catch:{ Exception -> 0x00e2 }
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00e2 }
        r2.<init>();	 Catch:{ Exception -> 0x00e2 }
        r3 = com.facebook.ads.internal.p050r.C2078a.m5062A(r6);	 Catch:{ Exception -> 0x00e2 }
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x00e2 }
        r3 = "&";
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x00e2 }
        r0 = r2.append(r0);	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x00e2 }
        if (r0 == 0) goto L_0x00dd;
    L_0x00d1:
        r2 = r0.isEmpty();	 Catch:{ Exception -> 0x00e2 }
        if (r2 != 0) goto L_0x00dd;
    L_0x00d7:
        r2 = 0;
        r3 = f4624c;	 Catch:{ Exception -> 0x00e2 }
        r1.m5465a(r0, r2, r3);	 Catch:{ Exception -> 0x00e2 }
    L_0x00dd:
        r1.m5472b();
        goto L_0x001b;
    L_0x00e2:
        r0 = move-exception;
        r2 = f4622a;	 Catch:{ all -> 0x00ef }
        r3 = "Bot Detection Network Signal Error";
        android.util.Log.e(r2, r3, r0);	 Catch:{ all -> 0x00ef }
        r1.m5472b();
        goto L_0x001b;
    L_0x00ef:
        r0 = move-exception;
        r1.m5472b();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.p.a.a(android.content.Context, java.lang.String, java.lang.String):void");
    }
}
