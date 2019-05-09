package com.chartboost.sdk;

import android.app.Activity;
import com.chartboost.sdk.Chartboost.CBFramework;
import com.chartboost.sdk.Chartboost.CBMediation;
import com.chartboost.sdk.Libraries.CBLogging.Level;

/* renamed from: com.chartboost.sdk.f */
class C1404f implements Runnable {
    /* renamed from: a */
    boolean f2882a = false;
    /* renamed from: b */
    CBFramework f2883b = null;
    /* renamed from: c */
    CBMediation f2884c = null;
    /* renamed from: d */
    String f2885d = null;
    /* renamed from: e */
    String f2886e = null;
    /* renamed from: f */
    Level f2887f = null;
    /* renamed from: g */
    ChartboostDelegate f2888g = null;
    /* renamed from: h */
    Activity f2889h = null;
    /* renamed from: i */
    String f2890i = null;
    /* renamed from: j */
    String f2891j = null;
    /* renamed from: k */
    private final int f2892k;

    C1404f(int i) {
        this.f2892k = i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r9 = this;
        r1 = 0;
        r0 = r9.f2892k;	 Catch:{ Exception -> 0x000c }
        switch(r0) {
            case 0: goto L_0x00a3;
            case 1: goto L_0x0007;
            case 2: goto L_0x0006;
            case 3: goto L_0x002e;
            case 4: goto L_0x0054;
            case 5: goto L_0x007e;
            case 6: goto L_0x0084;
            case 7: goto L_0x008a;
            case 8: goto L_0x0096;
            default: goto L_0x0006;
        };	 Catch:{ Exception -> 0x000c }
    L_0x0006:
        return;
    L_0x0007:
        r0 = r9.f2882a;	 Catch:{ Exception -> 0x000c }
        com.chartboost.sdk.C1410i.f2944u = r0;	 Catch:{ Exception -> 0x000c }
        goto L_0x0006;
    L_0x000c:
        r0 = move-exception;
        r1 = com.chartboost.sdk.C1404f.class;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "run (";
        r2 = r2.append(r3);
        r3 = r9.f2892k;
        r2 = r2.append(r3);
        r3 = ")";
        r2 = r2.append(r3);
        r2 = r2.toString();
        com.chartboost.sdk.Tracking.C1391a.m3206a(r1, r2, r0);
        goto L_0x0006;
    L_0x002e:
        r0 = r9.f2884c;	 Catch:{ Exception -> 0x000c }
        com.chartboost.sdk.C1410i.f2932i = r0;	 Catch:{ Exception -> 0x000c }
        r0 = r9.f2885d;	 Catch:{ Exception -> 0x000c }
        com.chartboost.sdk.C1410i.f2933j = r0;	 Catch:{ Exception -> 0x000c }
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x000c }
        r0.<init>();	 Catch:{ Exception -> 0x000c }
        r1 = com.chartboost.sdk.C1410i.f2932i;	 Catch:{ Exception -> 0x000c }
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x000c }
        r1 = " ";
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x000c }
        r1 = com.chartboost.sdk.C1410i.f2933j;	 Catch:{ Exception -> 0x000c }
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x000c }
        r0 = r0.toString();	 Catch:{ Exception -> 0x000c }
        com.chartboost.sdk.C1410i.f2931h = r0;	 Catch:{ Exception -> 0x000c }
        goto L_0x0006;
    L_0x0054:
        r0 = r9.f2883b;	 Catch:{ Exception -> 0x000c }
        if (r0 != 0) goto L_0x0060;
    L_0x0058:
        r0 = "ChartboostCommand";
        r1 = "Pass a valid CBFramework enum value";
        com.chartboost.sdk.Libraries.CBLogging.m3099b(r0, r1);	 Catch:{ Exception -> 0x000c }
        goto L_0x0006;
    L_0x0060:
        r0 = r9.f2883b;	 Catch:{ Exception -> 0x000c }
        com.chartboost.sdk.C1410i.f2927d = r0;	 Catch:{ Exception -> 0x000c }
        r0 = r9.f2885d;	 Catch:{ Exception -> 0x000c }
        com.chartboost.sdk.C1410i.f2928e = r0;	 Catch:{ Exception -> 0x000c }
        r0 = "%s %s";
        r1 = 2;
        r1 = new java.lang.Object[r1];	 Catch:{ Exception -> 0x000c }
        r2 = 0;
        r3 = r9.f2883b;	 Catch:{ Exception -> 0x000c }
        r1[r2] = r3;	 Catch:{ Exception -> 0x000c }
        r2 = 1;
        r3 = r9.f2885d;	 Catch:{ Exception -> 0x000c }
        r1[r2] = r3;	 Catch:{ Exception -> 0x000c }
        r0 = java.lang.String.format(r0, r1);	 Catch:{ Exception -> 0x000c }
        com.chartboost.sdk.C1410i.f2929f = r0;	 Catch:{ Exception -> 0x000c }
        goto L_0x0006;
    L_0x007e:
        r0 = r9.f2885d;	 Catch:{ Exception -> 0x000c }
        com.chartboost.sdk.C1392b.m3233a(r0);	 Catch:{ Exception -> 0x000c }
        goto L_0x0006;
    L_0x0084:
        r0 = r9.f2886e;	 Catch:{ Exception -> 0x000c }
        com.chartboost.sdk.C1410i.f2924a = r0;	 Catch:{ Exception -> 0x000c }
        goto L_0x0006;
    L_0x008a:
        r0 = com.chartboost.sdk.C1392b.m3238b();	 Catch:{ Exception -> 0x000c }
        if (r0 == 0) goto L_0x0006;
    L_0x0090:
        r0 = r9.f2887f;	 Catch:{ Exception -> 0x000c }
        com.chartboost.sdk.Libraries.CBLogging.f2675a = r0;	 Catch:{ Exception -> 0x000c }
        goto L_0x0006;
    L_0x0096:
        r0 = r9.f2888g;	 Catch:{ Exception -> 0x000c }
        com.chartboost.sdk.C1410i.f2926c = r0;	 Catch:{ Exception -> 0x000c }
        r0 = "SdkSettings.assignDelegate";
        r1 = r9.f2888g;	 Catch:{ Exception -> 0x000c }
        com.chartboost.sdk.impl.aq.m3408a(r0, r1);	 Catch:{ Exception -> 0x000c }
        goto L_0x0006;
    L_0x00a3:
        r0 = com.chartboost.sdk.C1409h.m3324a();	 Catch:{ Exception -> 0x000c }
        if (r0 != 0) goto L_0x0006;
    L_0x00a9:
        r8 = com.chartboost.sdk.C1409h.class;
        monitor-enter(r8);	 Catch:{ Exception -> 0x000c }
        r0 = com.chartboost.sdk.C1409h.m3324a();	 Catch:{ all -> 0x00c0 }
        if (r0 != 0) goto L_0x0140;
    L_0x00b2:
        r0 = r9.f2889h;	 Catch:{ all -> 0x00c0 }
        if (r0 != 0) goto L_0x00c3;
    L_0x00b6:
        r0 = "ChartboostCommand";
        r1 = "Activity object is null. Please pass a valid activity object";
        com.chartboost.sdk.Libraries.CBLogging.m3099b(r0, r1);	 Catch:{ all -> 0x00c0 }
        monitor-exit(r8);	 Catch:{ all -> 0x00c0 }
        goto L_0x0006;
    L_0x00c0:
        r0 = move-exception;
        monitor-exit(r8);	 Catch:{ all -> 0x00c0 }
        throw r0;	 Catch:{ Exception -> 0x000c }
    L_0x00c3:
        r0 = r9.f2889h;	 Catch:{ all -> 0x00c0 }
        r0 = com.chartboost.sdk.C1392b.m3236a(r0);	 Catch:{ all -> 0x00c0 }
        if (r0 != 0) goto L_0x00d5;
    L_0x00cb:
        r0 = "ChartboostCommand";
        r1 = "Permissions not set correctly";
        com.chartboost.sdk.Libraries.CBLogging.m3099b(r0, r1);	 Catch:{ all -> 0x00c0 }
        monitor-exit(r8);	 Catch:{ all -> 0x00c0 }
        goto L_0x0006;
    L_0x00d5:
        r0 = r9.f2889h;	 Catch:{ all -> 0x00c0 }
        r0 = com.chartboost.sdk.C1392b.m3239b(r0);	 Catch:{ all -> 0x00c0 }
        if (r0 != 0) goto L_0x00e4;
    L_0x00dd:
        r0 = "ChartboostCommand";
        r2 = "Please add CBImpressionActivity in AndroidManifest.xml following README.md instructions.";
        com.chartboost.sdk.Libraries.CBLogging.m3099b(r0, r2);	 Catch:{ all -> 0x00c0 }
    L_0x00e4:
        r0 = r9.f2890i;	 Catch:{ all -> 0x00c0 }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ all -> 0x00c0 }
        if (r0 != 0) goto L_0x00f4;
    L_0x00ec:
        r0 = r9.f2891j;	 Catch:{ all -> 0x00c0 }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ all -> 0x00c0 }
        if (r0 == 0) goto L_0x00fe;
    L_0x00f4:
        r0 = "ChartboostCommand";
        r1 = "AppId or AppSignature is null. Please pass a valid id's";
        com.chartboost.sdk.Libraries.CBLogging.m3099b(r0, r1);	 Catch:{ all -> 0x00c0 }
        monitor-exit(r8);	 Catch:{ all -> 0x00c0 }
        goto L_0x0006;
    L_0x00fe:
        r4 = com.chartboost.sdk.impl.C1454s.m3627a();	 Catch:{ all -> 0x00c0 }
        r0 = com.chartboost.sdk.C1405g.m3317a();	 Catch:{ all -> 0x00c0 }
        r6 = r4.f3322a;	 Catch:{ all -> 0x00c0 }
        com.chartboost.sdk.impl.C1450o.m3610a();	 Catch:{ all -> 0x00c0 }
        r2 = 0;
        r3 = com.chartboost.sdk.impl.ac.m3363a();	 Catch:{ Throwable -> 0x0143 }
        r5 = r0.m3318a(r3);	 Catch:{ Throwable -> 0x0143 }
        r5 = (java.util.concurrent.ScheduledExecutorService) r5;	 Catch:{ Throwable -> 0x0143 }
        r3 = 4;
        r3 = com.chartboost.sdk.impl.ac.m3362a(r3);	 Catch:{ Throwable -> 0x0159 }
        r7 = r0.m3318a(r3);	 Catch:{ Throwable -> 0x0159 }
        r7 = (java.util.concurrent.ExecutorService) r7;	 Catch:{ Throwable -> 0x0159 }
        r0 = new com.chartboost.sdk.h;	 Catch:{ all -> 0x00c0 }
        r1 = r9.f2889h;	 Catch:{ all -> 0x00c0 }
        r2 = r9.f2890i;	 Catch:{ all -> 0x00c0 }
        r3 = r9.f2891j;	 Catch:{ all -> 0x00c0 }
        r0.<init>(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ all -> 0x00c0 }
        com.chartboost.sdk.C1409h.m3327a(r0);	 Catch:{ all -> 0x00c0 }
        r1 = r0.f2903b;	 Catch:{ all -> 0x00c0 }
        r1.m3602c();	 Catch:{ all -> 0x00c0 }
        r1 = new com.chartboost.sdk.h$a;	 Catch:{ all -> 0x00c0 }
        r0.getClass();	 Catch:{ all -> 0x00c0 }
        r2 = 3;
        r1.<init>(r0, r2);	 Catch:{ all -> 0x00c0 }
        r0.m3332a(r1);	 Catch:{ all -> 0x00c0 }
    L_0x0140:
        monitor-exit(r8);	 Catch:{ all -> 0x00c0 }
        goto L_0x0006;
    L_0x0143:
        r0 = move-exception;
        r5 = r1;
    L_0x0145:
        if (r5 == 0) goto L_0x014a;
    L_0x0147:
        r5.shutdown();	 Catch:{ all -> 0x00c0 }
    L_0x014a:
        if (r1 == 0) goto L_0x014f;
    L_0x014c:
        r2.shutdown();	 Catch:{ all -> 0x00c0 }
    L_0x014f:
        r1 = "ChartboostCommand";
        r2 = "Unable to start threads";
        com.chartboost.sdk.Libraries.CBLogging.m3098a(r1, r2, r0);	 Catch:{ all -> 0x00c0 }
        monitor-exit(r8);	 Catch:{ all -> 0x00c0 }
        goto L_0x0006;
    L_0x0159:
        r0 = move-exception;
        goto L_0x0145;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.chartboost.sdk.f.run():void");
    }
}
