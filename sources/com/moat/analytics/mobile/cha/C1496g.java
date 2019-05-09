package com.moat.analytics.mobile.cha;

import com.moat.analytics.mobile.cha.C1536t.C1532a;

/* renamed from: com.moat.analytics.mobile.cha.g */
final class C1496g {
    /* renamed from: ʻ */
    private static int f3475 = 0;
    /* renamed from: ʼ */
    private static int f3476 = 565428102;
    /* renamed from: ʽ */
    private static int f3477 = -1138247343;
    /* renamed from: ˊॱ */
    private static int f3478 = 117;
    /* renamed from: ॱˋ */
    private static int f3479 = 1;
    /* renamed from: ᐝ */
    private static byte[] f3480 = new byte[]{(byte) -73, (byte) 79, (byte) -77, Byte.MAX_VALUE, (byte) -123, (byte) -125, (byte) -126, (byte) 73, (byte) 125, (byte) -81, (byte) 81, (byte) -77, (byte) 81, Byte.MAX_VALUE, (byte) -83, (byte) -121, (byte) 73, (byte) -78, Byte.MAX_VALUE, (byte) 85, (byte) -82, Byte.MIN_VALUE, (byte) 77, (byte) -79, (byte) 77, (byte) -80, (byte) -126, (byte) 123, (byte) -124, (byte) 77, (byte) -125, (byte) -87, (byte) -123, (byte) 117, (byte) -123, (byte) 120, (byte) -122, (byte) 82, (byte) -83, Byte.MIN_VALUE, (byte) -69, (byte) -66, (byte) 65, (byte) -78, (byte) 65, (byte) -66, (byte) 119, (byte) -120, (byte) -72, (byte) 70, (byte) 117, (byte) -116, (byte) 77, (byte) -66, (byte) -65, (byte) 112, (byte) -108, (byte) 105, (byte) -66, (byte) 69, (byte) 71, (byte) -118, (byte) 110, (byte) -118, (byte) 66, (byte) -78, (byte) 65, (byte) 71, (byte) -65, (byte) 68, (byte) 104, (byte) -120, (byte) 107, (byte) -109, (byte) -78, (byte) 118, (byte) -119, (byte) 113, (byte) -108, (byte) -91, (byte) -81, (byte) -5, (byte) 80, (byte) -25, (byte) 10, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
    /* renamed from: ˊ */
    private boolean f3481 = false;
    /* renamed from: ˋ */
    private int f3482 = 10;
    /* renamed from: ˎ */
    private boolean f3483 = false;
    /* renamed from: ˏ */
    private boolean f3484 = false;
    /* renamed from: ॱ */
    private int f3485 = 200;

    C1496g(String str) {
        m3761(str);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: ॱ */
    private void m3761(java.lang.String r11) {
        /*
        r10 = this;
        r1 = 0;
        r0 = 1;
        if (r11 != 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r4 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0131 }
        r4.<init>(r11);	 Catch:{ Exception -> 0x0131 }
        r2 = -115; // 0xffffffffffffff8d float:NaN double:NaN;
        r3 = 1138247458; // 0x43d84722 float:432.55573 double:5.623689655E-315;
        r5 = 89;
        r6 = -565428102; // 0xffffffffde4c407a float:-3.67947443E18 double:NaN;
        r2 = com.moat.analytics.mobile.cha.C1496g.m3758(r2, r3, r5, r6);	 Catch:{ Exception -> 0x0131 }
        r2 = r2.intern();	 Catch:{ Exception -> 0x0131 }
        r2 = r4.getString(r2);	 Catch:{ Exception -> 0x0131 }
        r3 = -77;
        r5 = 1138247394; // 0x43d846e2 float:432.55377 double:5.62368934E-315;
        r6 = -126; // 0xffffffffffffff82 float:NaN double:NaN;
        r7 = -565428101; // 0xffffffffde4c407b float:-3.67947471E18 double:NaN;
        r3 = com.moat.analytics.mobile.cha.C1496g.m3758(r3, r5, r6, r7);	 Catch:{ Exception -> 0x0131 }
        r3 = r3.intern();	 Catch:{ Exception -> 0x0131 }
        r3 = r2.equals(r3);	 Catch:{ Exception -> 0x0131 }
        r5 = -77;
        r6 = 1138247399; // 0x43d846e7 float:432.55392 double:5.623689363E-315;
        r7 = -70;
        r8 = -565428062; // 0xffffffffde4c40a2 float:-3.67948543E18 double:NaN;
        r5 = com.moat.analytics.mobile.cha.C1496g.m3758(r5, r6, r7, r8);	 Catch:{ Exception -> 0x0131 }
        r5 = r5.intern();	 Catch:{ Exception -> 0x0131 }
        r5 = r2.equals(r5);	 Catch:{ Exception -> 0x0131 }
        r6 = -115; // 0xffffffffffffff8d float:NaN double:NaN;
        r7 = 1138247454; // 0x43d8471e float:432.5556 double:5.623689635E-315;
        r8 = 90;
        r9 = -565428023; // 0xffffffffde4c40c9 float:-3.67949615E18 double:NaN;
        r6 = com.moat.analytics.mobile.cha.C1496g.m3758(r6, r7, r8, r9);	 Catch:{ Exception -> 0x0131 }
        r6 = r6.intern();	 Catch:{ Exception -> 0x0131 }
        r2 = r2.equals(r6);	 Catch:{ Exception -> 0x0131 }
        if (r2 != 0) goto L_0x01e8;
    L_0x0064:
        r2 = 66;
    L_0x0066:
        switch(r2) {
            case 66: goto L_0x013f;
            default: goto L_0x0069;
        };	 Catch:{ Exception -> 0x0131 }
    L_0x0069:
        r2 = com.moat.analytics.mobile.cha.C1496g.m3760(r4);	 Catch:{ Exception -> 0x0131 }
        if (r2 != 0) goto L_0x01f1;
    L_0x006f:
        r2 = 43;
    L_0x0071:
        switch(r2) {
            case 43: goto L_0x0190;
            default: goto L_0x0074;
        };	 Catch:{ Exception -> 0x0131 }
    L_0x0074:
        r2 = -115; // 0xffffffffffffff8d float:NaN double:NaN;
        r3 = 1138247448; // 0x43d84718 float:432.55542 double:5.623689605E-315;
        r5 = -86;
        r6 = -565428022; // 0xffffffffde4c40ca float:-3.67949642E18 double:NaN;
        r2 = com.moat.analytics.mobile.cha.C1496g.m3758(r2, r3, r5, r6);	 Catch:{ Exception -> 0x0131 }
        r2 = r2.intern();	 Catch:{ Exception -> 0x0131 }
        r2 = r4.has(r2);	 Catch:{ Exception -> 0x0131 }
        if (r2 == 0) goto L_0x01fb;
    L_0x008c:
        r2 = 27;
    L_0x008e:
        switch(r2) {
            case 75: goto L_0x00c9;
            default: goto L_0x0091;
        };
    L_0x0091:
        r2 = f3475;
        r2 = r2 + 77;
        r3 = r2 % 128;
        f3479 = r3;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x0214;
    L_0x009d:
        r2 = r0;
    L_0x009e:
        switch(r2) {
            case 1: goto L_0x01ad;
            default: goto L_0x00a1;
        };
    L_0x00a1:
        r2 = -115; // 0xffffffffffffff8d float:NaN double:NaN;
        r3 = 1138247448; // 0x43d84718 float:432.55542 double:5.623689605E-315;
        r5 = -86;
        r6 = -565428022; // 0xffffffffde4c40ca float:-3.67949642E18 double:NaN;
        r2 = com.moat.analytics.mobile.cha.C1496g.m3758(r2, r3, r5, r6);	 Catch:{ Exception -> 0x0131 }
        r2 = r2.intern();	 Catch:{ Exception -> 0x0131 }
        r3 = r4.getInt(r2);	 Catch:{ Exception -> 0x0131 }
        r2 = 100;
        if (r3 < r2) goto L_0x01ff;
    L_0x00bb:
        r2 = r0;
    L_0x00bc:
        switch(r2) {
            case 0: goto L_0x00c9;
            default: goto L_0x00bf;
        };	 Catch:{ Exception -> 0x0131 }
    L_0x00bf:
        r2 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        if (r3 > r2) goto L_0x0202;
    L_0x00c3:
        r2 = r0;
    L_0x00c4:
        switch(r2) {
            case 0: goto L_0x00c9;
            default: goto L_0x00c7;
        };	 Catch:{ Exception -> 0x0131 }
    L_0x00c7:
        r10.f3485 = r3;	 Catch:{ Exception -> 0x0131 }
    L_0x00c9:
        r2 = -115; // 0xffffffffffffff8d float:NaN double:NaN;
        r3 = 1138247444; // 0x43d84714 float:432.5553 double:5.623689585E-315;
        r5 = -11;
        r6 = -565428021; // 0xffffffffde4c40cb float:-3.6794967E18 double:NaN;
        r2 = com.moat.analytics.mobile.cha.C1496g.m3758(r2, r3, r5, r6);	 Catch:{ Exception -> 0x0131 }
        r2 = r2.intern();	 Catch:{ Exception -> 0x0131 }
        r2 = r4.has(r2);	 Catch:{ Exception -> 0x0131 }
        if (r2 == 0) goto L_0x0205;
    L_0x00e1:
        r2 = r1;
    L_0x00e2:
        switch(r2) {
            case 1: goto L_0x010d;
            default: goto L_0x00e5;
        };
    L_0x00e5:
        r2 = f3475;
        r2 = r2 + 53;
        r3 = r2 % 128;
        f3479 = r3;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x00f2;
    L_0x00f1:
        r0 = r1;
    L_0x00f2:
        switch(r0) {
            case 0: goto L_0x01ce;
            default: goto L_0x00f5;
        };
    L_0x00f5:
        r0 = -115; // 0xffffffffffffff8d float:NaN double:NaN;
        r2 = 1138247444; // 0x43d84714 float:432.5553 double:5.623689585E-315;
        r3 = -11;
        r5 = -565428021; // 0xffffffffde4c40cb float:-3.6794967E18 double:NaN;
        r0 = com.moat.analytics.mobile.cha.C1496g.m3758(r0, r2, r3, r5);	 Catch:{ Exception -> 0x0131 }
        r0 = r0.intern();	 Catch:{ Exception -> 0x0131 }
        r0 = r4.getInt(r0);	 Catch:{ Exception -> 0x0131 }
        r10.f3482 = r0;	 Catch:{ Exception -> 0x0131 }
    L_0x010d:
        r0 = com.moat.analytics.mobile.cha.C1496g.m3762(r4);	 Catch:{ Exception -> 0x0131 }
        if (r0 == 0) goto L_0x0208;
    L_0x0113:
        r0 = 28;
    L_0x0115:
        switch(r0) {
            case 28: goto L_0x011a;
            default: goto L_0x0118;
        };
    L_0x0118:
        goto L_0x0004;
    L_0x011a:
        r0 = f3475;
        r0 = r0 + 23;
        r2 = r0 % 128;
        f3479 = r2;
        r0 = r0 % 2;
        if (r0 != 0) goto L_0x0126;
    L_0x0126:
        r0 = com.moat.analytics.mobile.cha.MoatAnalytics.getInstance();	 Catch:{ Exception -> 0x0131 }
        r0 = (com.moat.analytics.mobile.cha.C1495f) r0;	 Catch:{ Exception -> 0x0131 }
        r2 = 1;
        r0.f3471 = r2;	 Catch:{ Exception -> 0x0131 }
        goto L_0x0004;
    L_0x0131:
        r0 = move-exception;
        r10.f3484 = r1;
        r10.f3483 = r1;
        r1 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r10.f3485 = r1;
        com.moat.analytics.mobile.cha.C1518o.m3840(r0);
        goto L_0x0004;
    L_0x013f:
        if (r3 != 0) goto L_0x01eb;
    L_0x0141:
        r2 = r1;
    L_0x0142:
        switch(r2) {
            case 0: goto L_0x0147;
            default: goto L_0x0145;
        };
    L_0x0145:
        goto L_0x0069;
    L_0x0147:
        r2 = f3479;
        r2 = r2 + 57;
        r6 = r2 % 128;
        f3475 = r6;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x0153;
    L_0x0153:
        if (r5 == 0) goto L_0x01ee;
    L_0x0155:
        r2 = r1;
    L_0x0156:
        switch(r2) {
            case 1: goto L_0x0074;
            default: goto L_0x0159;
        };
    L_0x0159:
        goto L_0x0069;
    L_0x015b:
        r2 = com.moat.analytics.mobile.cha.C1496g.m3759(r4);	 Catch:{ Exception -> 0x0131 }
        if (r2 != 0) goto L_0x01f5;
    L_0x0161:
        r2 = r1;
    L_0x0162:
        switch(r2) {
            case 0: goto L_0x0167;
            default: goto L_0x0165;
        };	 Catch:{ Exception -> 0x0131 }
    L_0x0165:
        goto L_0x0074;
    L_0x0167:
        r2 = 1;
        r10.f3484 = r2;	 Catch:{ Exception -> 0x0131 }
        r10.f3483 = r3;	 Catch:{ Exception -> 0x0131 }
        r10.f3481 = r5;	 Catch:{ Exception -> 0x0131 }
        r2 = r10.f3481;	 Catch:{ Exception -> 0x0131 }
        if (r2 == 0) goto L_0x01f8;
    L_0x0172:
        r2 = r0;
    L_0x0173:
        switch(r2) {
            case 0: goto L_0x0074;
            default: goto L_0x0176;
        };
    L_0x0176:
        r2 = f3479;
        r2 = r2 + 81;
        r3 = r2 % 128;
        f3475 = r3;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x0211;
    L_0x0182:
        r2 = r1;
    L_0x0183:
        switch(r2) {
            case 1: goto L_0x018b;
            default: goto L_0x0186;
        };
    L_0x0186:
        r2 = 1;
        r10.f3483 = r2;	 Catch:{ Exception -> 0x0131 }
        goto L_0x0074;
    L_0x018b:
        r2 = 1;
        r10.f3483 = r2;	 Catch:{ Exception -> 0x0131 }
        goto L_0x0074;
    L_0x0190:
        r2 = f3475;
        r2 = r2 + 95;
        r6 = r2 % 128;
        f3479 = r6;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x020c;
    L_0x019c:
        r2 = 80;
    L_0x019e:
        switch(r2) {
            case 6: goto L_0x015b;
            default: goto L_0x01a1;
        };
    L_0x01a1:
        r2 = com.moat.analytics.mobile.cha.C1496g.m3759(r4);	 Catch:{ Exception -> 0x0131 }
        if (r2 != 0) goto L_0x020e;
    L_0x01a7:
        r2 = 60;
    L_0x01a9:
        switch(r2) {
            case 69: goto L_0x0074;
            default: goto L_0x01ac;
        };	 Catch:{ Exception -> 0x0131 }
    L_0x01ac:
        goto L_0x0167;
    L_0x01ad:
        r2 = -115; // 0xffffffffffffff8d float:NaN double:NaN;
        r3 = 1138247448; // 0x43d84718 float:432.55542 double:5.623689605E-315;
        r5 = -86;
        r6 = -565428022; // 0xffffffffde4c40ca float:-3.67949642E18 double:NaN;
        r2 = com.moat.analytics.mobile.cha.C1496g.m3758(r2, r3, r5, r6);	 Catch:{ Exception -> 0x0131 }
        r2 = r2.intern();	 Catch:{ Exception -> 0x0131 }
        r3 = r4.getInt(r2);	 Catch:{ Exception -> 0x0131 }
        r2 = 100;
        if (r3 < r2) goto L_0x0217;
    L_0x01c7:
        r2 = 77;
    L_0x01c9:
        switch(r2) {
            case 19: goto L_0x00c9;
            default: goto L_0x01cc;
        };	 Catch:{ Exception -> 0x0131 }
    L_0x01cc:
        goto L_0x00bf;
    L_0x01ce:
        r0 = -115; // 0xffffffffffffff8d float:NaN double:NaN;
        r2 = 1138247444; // 0x43d84714 float:432.5553 double:5.623689585E-315;
        r3 = -11;
        r5 = -565428021; // 0xffffffffde4c40cb float:-3.6794967E18 double:NaN;
        r0 = com.moat.analytics.mobile.cha.C1496g.m3758(r0, r2, r3, r5);	 Catch:{ Exception -> 0x0131 }
        r0 = r0.intern();	 Catch:{ Exception -> 0x0131 }
        r0 = r4.getInt(r0);	 Catch:{ Exception -> 0x0131 }
        r10.f3482 = r0;	 Catch:{ Exception -> 0x0131 }
        goto L_0x010d;
    L_0x01e8:
        r2 = 2;
        goto L_0x0066;
    L_0x01eb:
        r2 = r0;
        goto L_0x0142;
    L_0x01ee:
        r2 = r0;
        goto L_0x0156;
    L_0x01f1:
        r2 = 56;
        goto L_0x0071;
    L_0x01f5:
        r2 = r0;
        goto L_0x0162;
    L_0x01f8:
        r2 = r1;
        goto L_0x0173;
    L_0x01fb:
        r2 = 75;
        goto L_0x008e;
    L_0x01ff:
        r2 = r1;
        goto L_0x00bc;
    L_0x0202:
        r2 = r1;
        goto L_0x00c4;
    L_0x0205:
        r2 = r0;
        goto L_0x00e2;
    L_0x0208:
        r0 = 96;
        goto L_0x0115;
    L_0x020c:
        r2 = 6;
        goto L_0x019e;
    L_0x020e:
        r2 = 69;
        goto L_0x01a9;
    L_0x0211:
        r2 = r0;
        goto L_0x0183;
    L_0x0214:
        r2 = r1;
        goto L_0x009e;
    L_0x0217:
        r2 = 19;
        goto L_0x01c9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.cha.g.ॱ(java.lang.String):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: ˎ */
    private static boolean m3760(org.json.JSONObject r7) {
        /*
        r0 = 1;
        r1 = 0;
        r2 = 16;
        r3 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x00b6 }
        if (r2 <= r3) goto L_0x00a8;
    L_0x0008:
        r2 = 58;
    L_0x000a:
        switch(r2) {
            case 36: goto L_0x001a;
            default: goto L_0x000d;
        };
    L_0x000d:
        r1 = f3479;
        r1 = r1 + 43;
        r2 = r1 % 128;
        f3475 = r2;
        r1 = r1 % 2;
        if (r1 == 0) goto L_0x0019;
    L_0x0019:
        return r0;
    L_0x001a:
        r2 = -115; // 0xffffffffffffff8d float:NaN double:NaN;
        r3 = 1138247454; // 0x43d8471e float:432.5556 double:5.623689635E-315;
        r4 = -93;
        r5 = -565428020; // 0xffffffffde4c40cc float:-3.67949697E18 double:NaN;
        r2 = com.moat.analytics.mobile.cha.C1496g.m3758(r2, r3, r4, r5);	 Catch:{ Exception -> 0x00b6 }
        r2 = r2.intern();	 Catch:{ Exception -> 0x00b6 }
        r2 = r7.has(r2);	 Catch:{ Exception -> 0x00b6 }
        if (r2 == 0) goto L_0x00ac;
    L_0x0032:
        r2 = r1;
    L_0x0033:
        switch(r2) {
            case 1: goto L_0x0088;
            default: goto L_0x0036;
        };
    L_0x0036:
        r2 = f3475;
        r2 = r2 + 71;
        r3 = r2 % 128;
        f3479 = r3;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x00b3;
    L_0x0042:
        r2 = 96;
    L_0x0044:
        switch(r2) {
            case 96: goto L_0x008a;
            default: goto L_0x0047;
        };
    L_0x0047:
        r2 = -115; // 0xffffffffffffff8d float:NaN double:NaN;
        r3 = 1138247454; // 0x43d8471e float:432.5556 double:5.623689635E-315;
        r4 = -93;
        r5 = -565428020; // 0xffffffffde4c40cc float:-3.67949697E18 double:NaN;
        r2 = com.moat.analytics.mobile.cha.C1496g.m3758(r2, r3, r4, r5);	 Catch:{ Exception -> 0x00b6 }
        r2 = r2.intern();	 Catch:{ Exception -> 0x00b6 }
        r3 = r7.getJSONArray(r2);	 Catch:{ Exception -> 0x00b6 }
        r2 = r3.length();	 Catch:{ Exception -> 0x00b6 }
        r4 = r1;
        r5 = r3;
        r3 = r2;
    L_0x0064:
        if (r4 >= r3) goto L_0x00ae;
    L_0x0066:
        r2 = 41;
    L_0x0068:
        switch(r2) {
            case 24: goto L_0x0088;
            default: goto L_0x006b;
        };	 Catch:{ Exception -> 0x00b6 }
    L_0x006b:
        r2 = r5.getInt(r4);	 Catch:{ Exception -> 0x00b6 }
        r6 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x00b6 }
        if (r2 != r6) goto L_0x00b1;
    L_0x0073:
        r2 = r1;
    L_0x0074:
        switch(r2) {
            case 1: goto L_0x0084;
            default: goto L_0x0077;
        };
    L_0x0077:
        r1 = f3475;
        r1 = r1 + 65;
        r2 = r1 % 128;
        f3479 = r2;
        r1 = r1 % 2;
        if (r1 != 0) goto L_0x0019;
    L_0x0083:
        goto L_0x0019;
    L_0x0084:
        r2 = r4 + 1;
        r4 = r2;
        goto L_0x0064;
    L_0x0088:
        r0 = r1;
        goto L_0x0019;
    L_0x008a:
        r2 = -115; // 0xffffffffffffff8d float:NaN double:NaN;
        r3 = 1138247454; // 0x43d8471e float:432.5556 double:5.623689635E-315;
        r4 = -93;
        r5 = -565428020; // 0xffffffffde4c40cc float:-3.67949697E18 double:NaN;
        r2 = com.moat.analytics.mobile.cha.C1496g.m3758(r2, r3, r4, r5);	 Catch:{ Exception -> 0x00b6 }
        r2 = r2.intern();	 Catch:{ Exception -> 0x00b6 }
        r3 = r7.getJSONArray(r2);	 Catch:{ Exception -> 0x00b6 }
        r2 = r3.length();	 Catch:{ Exception -> 0x00b6 }
        r4 = r1;
        r5 = r3;
        r3 = r2;
        goto L_0x0064;
    L_0x00a8:
        r2 = 36;
        goto L_0x000a;
    L_0x00ac:
        r2 = r0;
        goto L_0x0033;
    L_0x00ae:
        r2 = 24;
        goto L_0x0068;
    L_0x00b1:
        r2 = r0;
        goto L_0x0074;
    L_0x00b3:
        r2 = 56;
        goto L_0x0044;
    L_0x00b6:
        r1 = move-exception;
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.cha.g.ˎ(org.json.JSONObject):boolean");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: ˋ */
    private static boolean m3759(org.json.JSONObject r7) {
        /*
        r0 = 1;
        r1 = 0;
        r2 = -115; // 0xffffffffffffff8d float:NaN double:NaN;
        r3 = 1138247440; // 0x43d84710 float:432.55518 double:5.623689566E-315;
        r4 = -24;
        r5 = -565428019; // 0xffffffffde4c40cd float:-3.67949725E18 double:NaN;
        r2 = com.moat.analytics.mobile.cha.C1496g.m3758(r2, r3, r4, r5);	 Catch:{ Exception -> 0x0095 }
        r2 = r2.intern();	 Catch:{ Exception -> 0x0095 }
        r2 = r7.has(r2);	 Catch:{ Exception -> 0x0095 }
        if (r2 == 0) goto L_0x00ac;
    L_0x001a:
        r2 = 14;
    L_0x001c:
        switch(r2) {
            case 18: goto L_0x0099;
            default: goto L_0x001f;
        };
    L_0x001f:
        r2 = f3475;
        r2 = r2 + 113;
        r3 = r2 % 128;
        f3479 = r3;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x00b5;
    L_0x002b:
        r2 = r0;
    L_0x002c:
        switch(r2) {
            case 0: goto L_0x006e;
            default: goto L_0x002f;
        };
    L_0x002f:
        r2 = com.moat.analytics.mobile.cha.C1526r.m3860();	 Catch:{ Exception -> 0x0095 }
        r3 = r2.m3854();	 Catch:{ Exception -> 0x0095 }
        r2 = -115; // 0xffffffffffffff8d float:NaN double:NaN;
        r4 = 1138247440; // 0x43d84710 float:432.55518 double:5.623689566E-315;
        r5 = -24;
        r6 = -565428019; // 0xffffffffde4c40cd float:-3.67949725E18 double:NaN;
        r2 = com.moat.analytics.mobile.cha.C1496g.m3758(r2, r4, r5, r6);	 Catch:{ Exception -> 0x0095 }
        r2 = r2.intern();	 Catch:{ Exception -> 0x0095 }
        r4 = r7.getJSONArray(r2);	 Catch:{ Exception -> 0x0095 }
        r2 = r4.length();	 Catch:{ Exception -> 0x0095 }
        r5 = r3;
        r6 = r4;
        r3 = r2;
        r4 = r1;
    L_0x0055:
        if (r4 >= r3) goto L_0x00b0;
    L_0x0057:
        r2 = r1;
    L_0x0058:
        switch(r2) {
            case 1: goto L_0x0099;
            default: goto L_0x005b;
        };	 Catch:{ Exception -> 0x0095 }
    L_0x005b:
        r2 = r6.getString(r4);	 Catch:{ Exception -> 0x0095 }
        r2 = r2.contentEquals(r5);	 Catch:{ Exception -> 0x0095 }
        if (r2 == 0) goto L_0x00b2;
    L_0x0065:
        r2 = 23;
    L_0x0067:
        switch(r2) {
            case 23: goto L_0x009b;
            default: goto L_0x006a;
        };	 Catch:{ Exception -> 0x0095 }
    L_0x006a:
        r2 = r4 + 1;
        r4 = r2;
        goto L_0x0055;
    L_0x006e:
        r2 = com.moat.analytics.mobile.cha.C1526r.m3860();	 Catch:{ Exception -> 0x0095 }
        r3 = r2.m3854();	 Catch:{ Exception -> 0x0095 }
        r2 = -115; // 0xffffffffffffff8d float:NaN double:NaN;
        r4 = 1138247440; // 0x43d84710 float:432.55518 double:5.623689566E-315;
        r5 = -24;
        r6 = -565428019; // 0xffffffffde4c40cd float:-3.67949725E18 double:NaN;
        r2 = com.moat.analytics.mobile.cha.C1496g.m3758(r2, r4, r5, r6);	 Catch:{ Exception -> 0x0095 }
        r2 = r2.intern();	 Catch:{ Exception -> 0x0095 }
        r4 = r7.getJSONArray(r2);	 Catch:{ Exception -> 0x0095 }
        r2 = r4.length();	 Catch:{ Exception -> 0x0095 }
        r5 = r3;
        r6 = r4;
        r3 = r2;
        r4 = r1;
        goto L_0x0055;
    L_0x0095:
        r0 = move-exception;
        com.moat.analytics.mobile.cha.C1518o.m3840(r0);
    L_0x0099:
        r0 = r1;
    L_0x009a:
        return r0;
    L_0x009b:
        r1 = f3479;
        r1 = r1 + 71;
        r2 = r1 % 128;
        f3475 = r2;
        r1 = r1 % 2;
        if (r1 == 0) goto L_0x00b8;
    L_0x00a7:
        r1 = 4;
    L_0x00a8:
        switch(r1) {
            case 79: goto L_0x009a;
            default: goto L_0x00ab;
        };
    L_0x00ab:
        goto L_0x009a;
    L_0x00ac:
        r2 = 18;
        goto L_0x001c;
    L_0x00b0:
        r2 = r0;
        goto L_0x0058;
    L_0x00b2:
        r2 = 88;
        goto L_0x0067;
    L_0x00b5:
        r2 = r1;
        goto L_0x002c;
    L_0x00b8:
        r1 = 79;
        goto L_0x00a8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.cha.g.ˋ(org.json.JSONObject):boolean");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: ॱ */
    private static boolean m3762(org.json.JSONObject r8) {
        /*
        r0 = 1;
        r1 = 0;
        r2 = -115; // 0xffffffffffffff8d float:NaN double:NaN;
        r3 = 1138247440; // 0x43d84710 float:432.55518 double:5.623689566E-315;
        r4 = 1;
        r5 = -565428018; // 0xffffffffde4c40ce float:-3.67949752E18 double:NaN;
        r2 = com.moat.analytics.mobile.cha.C1496g.m3758(r2, r3, r4, r5);	 Catch:{ Exception -> 0x0079 }
        r2 = r2.intern();	 Catch:{ Exception -> 0x0079 }
        r2 = r8.has(r2);	 Catch:{ Exception -> 0x0079 }
        if (r2 == 0) goto L_0x008e;
    L_0x0019:
        r2 = 56;
    L_0x001b:
        switch(r2) {
            case 56: goto L_0x0020;
            default: goto L_0x001e;
        };
    L_0x001e:
        r0 = r1;
    L_0x001f:
        return r0;
    L_0x0020:
        r2 = f3475;
        r2 = r2 + 99;
        r3 = r2 % 128;
        f3479 = r3;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x002c;
    L_0x002c:
        r2 = com.moat.analytics.mobile.cha.C1526r.m3860();	 Catch:{ Exception -> 0x0079 }
        r4 = r2.m3854();	 Catch:{ Exception -> 0x0079 }
        r2 = -115; // 0xffffffffffffff8d float:NaN double:NaN;
        r3 = 1138247440; // 0x43d84710 float:432.55518 double:5.623689566E-315;
        r5 = 1;
        r6 = -565428018; // 0xffffffffde4c40ce float:-3.67949752E18 double:NaN;
        r2 = com.moat.analytics.mobile.cha.C1496g.m3758(r2, r3, r5, r6);	 Catch:{ Exception -> 0x0079 }
        r2 = r2.intern();	 Catch:{ Exception -> 0x0079 }
        r5 = r8.getJSONArray(r2);	 Catch:{ Exception -> 0x0079 }
        r6 = r5.length();	 Catch:{ Exception -> 0x0079 }
        r3 = r1;
    L_0x004e:
        if (r3 >= r6) goto L_0x0091;
    L_0x0050:
        r2 = r1;
    L_0x0051:
        switch(r2) {
            case 0: goto L_0x0055;
            default: goto L_0x0054;
        };
    L_0x0054:
        goto L_0x001e;
    L_0x0055:
        r2 = f3475;
        r2 = r2 + 49;
        r7 = r2 % 128;
        f3479 = r7;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x0096;
    L_0x0061:
        r2 = r1;
    L_0x0062:
        switch(r2) {
            case 0: goto L_0x007e;
            default: goto L_0x0065;
        };
    L_0x0065:
        r2 = r5.getString(r3);	 Catch:{ Exception -> 0x0079 }
        r2 = r2.contentEquals(r4);	 Catch:{ Exception -> 0x0079 }
        if (r2 == 0) goto L_0x0093;
    L_0x006f:
        r2 = 11;
    L_0x0071:
        switch(r2) {
            case 75: goto L_0x0075;
            default: goto L_0x0074;
        };
    L_0x0074:
        goto L_0x001f;
    L_0x0075:
        r2 = r3 + 1;
        r3 = r2;
        goto L_0x004e;
    L_0x0079:
        r0 = move-exception;
        com.moat.analytics.mobile.cha.C1518o.m3840(r0);
        goto L_0x001e;
    L_0x007e:
        r2 = r5.getString(r3);	 Catch:{ Exception -> 0x0079 }
        r2 = r2.contentEquals(r4);	 Catch:{ Exception -> 0x0079 }
        if (r2 == 0) goto L_0x0099;
    L_0x0088:
        r2 = 62;
    L_0x008a:
        switch(r2) {
            case 62: goto L_0x001f;
            default: goto L_0x008d;
        };
    L_0x008d:
        goto L_0x0075;
    L_0x008e:
        r2 = 19;
        goto L_0x001b;
    L_0x0091:
        r2 = r0;
        goto L_0x0051;
    L_0x0093:
        r2 = 75;
        goto L_0x0071;
    L_0x0096:
        r2 = 88;
        goto L_0x0062;
    L_0x0099:
        r2 = 73;
        goto L_0x008a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.cha.g.ॱ(org.json.JSONObject):boolean");
    }

    /* renamed from: ˊ */
    final boolean m3763() {
        return this.f3483;
    }

    /* renamed from: ॱ */
    final boolean m3767() {
        return this.f3481;
    }

    /* renamed from: ˏ */
    final int m3766() {
        return this.f3485;
    }

    /* renamed from: ˎ */
    final int m3765() {
        return this.f3482;
    }

    /* renamed from: ˋ */
    final int m3764() {
        return this.f3484 ? C1532a.f3592 : C1532a.f3593;
    }

    /* renamed from: ˋ */
    private static String m3758(int i, int i2, byte b, int i3) {
        int i4;
        int i5;
        int i6;
        int i7 = 0;
        StringBuilder stringBuilder = new StringBuilder();
        int i8 = i + f3478;
        if (i8 == -1) {
            i4 = 0;
        } else {
            i4 = 1;
        }
        switch (i4) {
            case 1:
                i5 = 0;
                break;
            default:
                i5 = 1;
                break;
        }
        if (i5 != 0) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        switch (i4) {
            case 0:
                i6 = i8;
                break;
            default:
                if (f3480 != null) {
                    i4 = 0;
                } else {
                    i4 = 1;
                }
                switch (i4) {
                    case 0:
                        i4 = f3479 + 87;
                        f3475 = i4 % 128;
                        byte b2;
                        switch (i4 % 2 != 0 ? 0 : 1) {
                            case 1:
                                b2 = (byte) (f3480[f3476 + i3] + f3478);
                                break;
                            default:
                                b2 = (byte) (f3480[f3476 + i3] + f3478);
                                break;
                        }
                    default:
                        i6 = (short) (null[f3476 + i3] + f3478);
                        break;
                }
        }
        switch (i6 > 0 ? 61 : 24) {
            case 24:
                break;
            default:
                i8 = f3476 + ((i3 + i6) - 2);
                switch (i5 != 0 ? 81 : 86) {
                    case 81:
                        i7 = 1;
                        break;
                }
                i4 = i8 + i7;
                char c = (char) (f3477 + i2);
                stringBuilder.append(c);
                i8 = c;
                i5 = i4;
                i4 = 1;
                while (true) {
                    switch (i4 < i6 ? 85 : 44) {
                        case 44:
                            break;
                        default:
                            int i9;
                            i7 = f3475 + 1;
                            f3479 = i7 % 128;
                            if (i7 % 2 == 0) {
                            }
                            switch (f3480 != null ? 66 : 69) {
                                case 66:
                                    i9 = i5 - 1;
                                    c = (char) ((((byte) f3480[i5]) ^ b) + i8);
                                    break;
                                default:
                                    i9 = i5 - 1;
                                    c = (char) ((((short) null[i5]) ^ b) + i8);
                                    break;
                            }
                            stringBuilder.append(c);
                            i4++;
                            char c2 = c;
                            i5 = i9;
                    }
                }
        }
        return stringBuilder.toString();
    }
}
