package com.applovin.impl.sdk;

class fh implements Runnable {
    /* renamed from: a */
    final /* synthetic */ fd f2511a;
    /* renamed from: b */
    private final String f2512b;
    /* renamed from: c */
    private final dx f2513c;
    /* renamed from: d */
    private final fe f2514d;

    fh(fd fdVar, dx dxVar, fe feVar) {
        this.f2511a = fdVar;
        this.f2512b = dxVar.m2482a();
        this.f2513c = dxVar;
        this.f2514d = feVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r10 = this;
        r8 = 1;
        r2 = java.lang.System.currentTimeMillis();
        com.applovin.impl.sdk.ab.m2196a();	 Catch:{ Throwable -> 0x00e7 }
        r0 = r10.f2511a;	 Catch:{ Throwable -> 0x00e7 }
        r0 = r0.f2496b;	 Catch:{ Throwable -> 0x00e7 }
        r0 = r0.m2144e();	 Catch:{ Throwable -> 0x00e7 }
        if (r0 == 0) goto L_0x001b;
    L_0x0015:
        r0 = r10.f2513c;	 Catch:{ Throwable -> 0x00e7 }
        r0 = r0.f2261g;	 Catch:{ Throwable -> 0x00e7 }
        if (r0 == 0) goto L_0x00ce;
    L_0x001b:
        r0 = r10.f2511a;	 Catch:{ Throwable -> 0x00e7 }
        r0 = r0.f2497c;	 Catch:{ Throwable -> 0x00e7 }
        r1 = r10.f2512b;	 Catch:{ Throwable -> 0x00e7 }
        r4 = "Task started execution...";
        r0.mo4175i(r1, r4);	 Catch:{ Throwable -> 0x00e7 }
        r0 = r10.f2513c;	 Catch:{ Throwable -> 0x00e7 }
        r0.run();	 Catch:{ Throwable -> 0x00e7 }
        r0 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x00e7 }
        r0 = r0 - r2;
        r4 = r10.f2511a;	 Catch:{ Throwable -> 0x00e7 }
        r4 = r4.f2497c;	 Catch:{ Throwable -> 0x00e7 }
        r5 = r10.f2512b;	 Catch:{ Throwable -> 0x00e7 }
        r6 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00e7 }
        r6.<init>();	 Catch:{ Throwable -> 0x00e7 }
        r7 = "Task executed successfully in ";
        r6 = r6.append(r7);	 Catch:{ Throwable -> 0x00e7 }
        r6 = r6.append(r0);	 Catch:{ Throwable -> 0x00e7 }
        r7 = "ms.";
        r6 = r6.append(r7);	 Catch:{ Throwable -> 0x00e7 }
        r6 = r6.toString();	 Catch:{ Throwable -> 0x00e7 }
        r4.mo4175i(r5, r6);	 Catch:{ Throwable -> 0x00e7 }
        r4 = r10.f2511a;	 Catch:{ Throwable -> 0x00e7 }
        r4 = r4.f2496b;	 Catch:{ Throwable -> 0x00e7 }
        r4 = r4.m2139a();	 Catch:{ Throwable -> 0x00e7 }
        r5 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00e7 }
        r5.<init>();	 Catch:{ Throwable -> 0x00e7 }
        r6 = r10.f2512b;	 Catch:{ Throwable -> 0x00e7 }
        r5 = r5.append(r6);	 Catch:{ Throwable -> 0x00e7 }
        r6 = "_count";
        r5 = r5.append(r6);	 Catch:{ Throwable -> 0x00e7 }
        r5 = r5.toString();	 Catch:{ Throwable -> 0x00e7 }
        r4.m2310a(r5);	 Catch:{ Throwable -> 0x00e7 }
        r5 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00e7 }
        r5.<init>();	 Catch:{ Throwable -> 0x00e7 }
        r6 = r10.f2512b;	 Catch:{ Throwable -> 0x00e7 }
        r5 = r5.append(r6);	 Catch:{ Throwable -> 0x00e7 }
        r6 = "_time";
        r5 = r5.append(r6);	 Catch:{ Throwable -> 0x00e7 }
        r5 = r5.toString();	 Catch:{ Throwable -> 0x00e7 }
        r4.m2311a(r5, r0);	 Catch:{ Throwable -> 0x00e7 }
    L_0x0090:
        r0 = r10.f2511a;
        r1 = r10.f2514d;
        r0 = r0.m2847a(r1);
        r0 = r0 - r8;
        r2 = r10.f2511a;
        r2 = r2.f2497c;
        r3 = "TaskManager";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = r10.f2514d;
        r4 = r4.append(r5);
        r5 = " queue finished task ";
        r4 = r4.append(r5);
        r5 = r10.f2513c;
        r5 = r5.m2482a();
        r4 = r4.append(r5);
        r5 = " with queue size ";
        r4 = r4.append(r5);
        r0 = r4.append(r0);
        r0 = r0.toString();
        r2.mo4175i(r3, r0);
    L_0x00cd:
        return;
    L_0x00ce:
        r0 = r10.f2511a;	 Catch:{ Throwable -> 0x00e7 }
        r0 = r0.f2497c;	 Catch:{ Throwable -> 0x00e7 }
        r1 = r10.f2512b;	 Catch:{ Throwable -> 0x00e7 }
        r4 = "Task re-scheduled...";
        r0.mo4175i(r1, r4);	 Catch:{ Throwable -> 0x00e7 }
        r0 = r10.f2511a;	 Catch:{ Throwable -> 0x00e7 }
        r1 = r10.f2513c;	 Catch:{ Throwable -> 0x00e7 }
        r4 = r10.f2514d;	 Catch:{ Throwable -> 0x00e7 }
        r6 = 2000; // 0x7d0 float:2.803E-42 double:9.88E-321;
        r0.m2856a(r1, r4, r6);	 Catch:{ Throwable -> 0x00e7 }
        goto L_0x0090;
    L_0x00e7:
        r0 = move-exception;
        r1 = r10.f2511a;	 Catch:{ all -> 0x0151 }
        r1 = r1.f2497c;	 Catch:{ all -> 0x0151 }
        r4 = r10.f2512b;	 Catch:{ all -> 0x0151 }
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0151 }
        r5.<init>();	 Catch:{ all -> 0x0151 }
        r6 = "Task failed execution in ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x0151 }
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0151 }
        r2 = r6 - r2;
        r2 = r5.append(r2);	 Catch:{ all -> 0x0151 }
        r3 = "ms.";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0151 }
        r2 = r2.toString();	 Catch:{ all -> 0x0151 }
        r1.mo4174e(r4, r2, r0);	 Catch:{ all -> 0x0151 }
        r0 = r10.f2511a;
        r1 = r10.f2514d;
        r0 = r0.m2847a(r1);
        r0 = r0 - r8;
        r2 = r10.f2511a;
        r2 = r2.f2497c;
        r3 = "TaskManager";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = r10.f2514d;
        r4 = r4.append(r5);
        r5 = " queue finished task ";
        r4 = r4.append(r5);
        r5 = r10.f2513c;
        r5 = r5.m2482a();
        r4 = r4.append(r5);
        r5 = " with queue size ";
        r4 = r4.append(r5);
        r0 = r4.append(r0);
        r0 = r0.toString();
        r2.mo4175i(r3, r0);
        goto L_0x00cd;
    L_0x0151:
        r0 = move-exception;
        r1 = r10.f2511a;
        r2 = r10.f2514d;
        r2 = r1.m2847a(r2);
        r2 = r2 - r8;
        r1 = r10.f2511a;
        r1 = r1.f2497c;
        r4 = "TaskManager";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = r10.f2514d;
        r5 = r5.append(r6);
        r6 = " queue finished task ";
        r5 = r5.append(r6);
        r6 = r10.f2513c;
        r6 = r6.m2482a();
        r5 = r5.append(r6);
        r6 = " with queue size ";
        r5 = r5.append(r6);
        r2 = r5.append(r2);
        r2 = r2.toString();
        r1.mo4175i(r4, r2);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.fh.run():void");
    }
}
