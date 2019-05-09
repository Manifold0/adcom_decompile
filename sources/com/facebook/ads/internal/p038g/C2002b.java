package com.facebook.ads.internal.p038g;

import android.support.annotation.WorkerThread;

@WorkerThread
/* renamed from: com.facebook.ads.internal.g.b */
public class C2002b {
    /* renamed from: a */
    public static String f4430a = "";
    /* renamed from: b */
    public static String f4431b = "";
    /* renamed from: c */
    public static boolean f4432c = false;
    /* renamed from: d */
    public static String f4433d = "";

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public static void m4828a(android.content.Context r4) {
        /*
        r1 = 0;
        r0 = "SDKIDFA";
        r0 = com.facebook.ads.internal.p025w.p071f.C2616a.m6730a(r0, r4);	 Catch:{ Exception -> 0x00c5 }
        r2 = 0;
        r2 = r4.getSharedPreferences(r0, r2);	 Catch:{ Exception -> 0x00c5 }
        r0 = "attributionId";
        r0 = r2.contains(r0);	 Catch:{ Exception -> 0x00c5 }
        if (r0 == 0) goto L_0x001e;
    L_0x0014:
        r0 = "attributionId";
        r3 = "";
        r0 = r2.getString(r0, r3);	 Catch:{ Exception -> 0x00c5 }
        f4430a = r0;	 Catch:{ Exception -> 0x00c5 }
    L_0x001e:
        r0 = "advertisingId";
        r0 = r2.contains(r0);	 Catch:{ Exception -> 0x00c5 }
        if (r0 == 0) goto L_0x0042;
    L_0x0026:
        r0 = "advertisingId";
        r3 = "";
        r0 = r2.getString(r0, r3);	 Catch:{ Exception -> 0x00c5 }
        f4431b = r0;	 Catch:{ Exception -> 0x00c5 }
        r0 = "limitAdTracking";
        r3 = f4432c;	 Catch:{ Exception -> 0x00c5 }
        r0 = r2.getBoolean(r0, r3);	 Catch:{ Exception -> 0x00c5 }
        f4432c = r0;	 Catch:{ Exception -> 0x00c5 }
        r0 = com.facebook.ads.internal.p038g.C2001a.C2000c.SHARED_PREFS;	 Catch:{ Exception -> 0x00c5 }
        r0 = r0.name();	 Catch:{ Exception -> 0x00c5 }
        f4433d = r0;	 Catch:{ Exception -> 0x00c5 }
    L_0x0042:
        r0 = r4.getContentResolver();	 Catch:{ Exception -> 0x00ad }
        r0 = com.facebook.ads.internal.p038g.C2004c.m4829a(r0);	 Catch:{ Exception -> 0x00ad }
    L_0x004a:
        if (r0 == 0) goto L_0x0054;
    L_0x004c:
        r3 = r0.f4434a;	 Catch:{ Exception -> 0x00c5 }
        if (r3 == 0) goto L_0x0054;
    L_0x0050:
        r3 = r0.f4434a;	 Catch:{ Exception -> 0x00c5 }
        f4430a = r3;	 Catch:{ Exception -> 0x00c5 }
    L_0x0054:
        r3 = com.facebook.ads.internal.p025w.p026b.C2565a.m6616a();	 Catch:{ Exception -> 0x00c5 }
        if (r3 == 0) goto L_0x006a;
    L_0x005a:
        r3 = "aid_override";
        r3 = com.facebook.ads.internal.p025w.p026b.C2565a.m6617b(r3);	 Catch:{ Exception -> 0x00c5 }
        if (r3 == 0) goto L_0x006a;
    L_0x0062:
        r3 = "aid_override";
        r3 = com.facebook.ads.internal.p025w.p026b.C2565a.m6615a(r3);	 Catch:{ Exception -> 0x00c5 }
        f4430a = r3;	 Catch:{ Exception -> 0x00c5 }
    L_0x006a:
        r0 = com.facebook.ads.internal.p038g.C2001a.m4823a(r4, r0);	 Catch:{ Exception -> 0x00b9 }
    L_0x006e:
        if (r0 == 0) goto L_0x0090;
    L_0x0070:
        r1 = r0.m4825a();	 Catch:{ Exception -> 0x00c5 }
        r3 = r0.m4826b();	 Catch:{ Exception -> 0x00c5 }
        r3 = java.lang.Boolean.valueOf(r3);	 Catch:{ Exception -> 0x00c5 }
        if (r1 == 0) goto L_0x0090;
    L_0x007e:
        f4431b = r1;	 Catch:{ Exception -> 0x00c5 }
        r1 = r3.booleanValue();	 Catch:{ Exception -> 0x00c5 }
        f4432c = r1;	 Catch:{ Exception -> 0x00c5 }
        r0 = r0.m4827c();	 Catch:{ Exception -> 0x00c5 }
        r0 = r0.name();	 Catch:{ Exception -> 0x00c5 }
        f4433d = r0;	 Catch:{ Exception -> 0x00c5 }
    L_0x0090:
        r0 = r2.edit();	 Catch:{ Exception -> 0x00c5 }
        r1 = "attributionId";
        r2 = f4430a;	 Catch:{ Exception -> 0x00c5 }
        r0.putString(r1, r2);	 Catch:{ Exception -> 0x00c5 }
        r1 = "advertisingId";
        r2 = f4431b;	 Catch:{ Exception -> 0x00c5 }
        r0.putString(r1, r2);	 Catch:{ Exception -> 0x00c5 }
        r1 = "limitAdTracking";
        r2 = f4432c;	 Catch:{ Exception -> 0x00c5 }
        r0.putBoolean(r1, r2);	 Catch:{ Exception -> 0x00c5 }
        r0.apply();	 Catch:{ Exception -> 0x00c5 }
    L_0x00ac:
        return;
    L_0x00ad:
        r0 = move-exception;
        r3 = "Error retrieving attribution id from fb4a";
        r0 = com.facebook.ads.internal.p021o.C2058a.m5020a(r0, r3);	 Catch:{ Exception -> 0x00c5 }
        com.facebook.ads.internal.p021o.C2059b.m5023a(r0);	 Catch:{ Exception -> 0x00c5 }
        r0 = r1;
        goto L_0x004a;
    L_0x00b9:
        r0 = move-exception;
        r3 = "Error retrieving advertising id from Google Play Services";
        r0 = com.facebook.ads.internal.p021o.C2058a.m5020a(r0, r3);	 Catch:{ Exception -> 0x00c5 }
        com.facebook.ads.internal.p021o.C2059b.m5023a(r0);	 Catch:{ Exception -> 0x00c5 }
        r0 = r1;
        goto L_0x006e;
    L_0x00c5:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00ac;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.g.b.a(android.content.Context):void");
    }
}
