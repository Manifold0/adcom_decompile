package com.moat.analytics.mobile.cha;

import android.graphics.Rect;
import android.location.Location;
import android.support.annotation.VisibleForTesting;
import android.util.DisplayMetrics;
import android.view.View;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.cha.u */
final class C1541u {
    /* renamed from: ʼ */
    private static int f3625 = 0;
    /* renamed from: ˋॱ */
    private static int f3626 = 1;
    /* renamed from: ʻ */
    private Location f3627;
    /* renamed from: ʽ */
    private JSONObject f3628;
    /* renamed from: ˊ */
    private Rect f3629;
    /* renamed from: ˊॱ */
    private JSONObject f3630;
    /* renamed from: ˋ */
    private JSONObject f3631;
    /* renamed from: ˎ */
    private Rect f3632;
    /* renamed from: ˏ */
    private C1540c f3633 = new C1540c();
    /* renamed from: ॱ */
    String f3634 = "{}";
    /* renamed from: ᐝ */
    private Map<String, Object> f3635 = new HashMap();

    /* renamed from: com.moat.analytics.mobile.cha.u$1 */
    static class C15371 implements Comparator<Rect> {
        C15371() {
        }

        public final /* synthetic */ int compare(Object obj, Object obj2) {
            return Integer.valueOf(((Rect) obj).top).compareTo(Integer.valueOf(((Rect) obj2).top));
        }
    }

    /* renamed from: com.moat.analytics.mobile.cha.u$a */
    static class C1538a {
        /* renamed from: ˎ */
        final Rect f3617;
        /* renamed from: ˏ */
        final View f3618;

        C1538a(View view, C1538a c1538a) {
            this.f3618 = view;
            if (c1538a != null) {
                int i = c1538a.f3617.left;
                i += view.getLeft();
                int top = c1538a.f3617.top + view.getTop();
                this.f3617 = new Rect(i, top, view.getWidth() + i, view.getHeight() + top);
                return;
            }
            this.f3617 = C1541u.m3902(view);
        }
    }

    /* renamed from: com.moat.analytics.mobile.cha.u$b */
    static class C1539b {
        /* renamed from: ˊ */
        boolean f3619 = false;
        /* renamed from: ˋ */
        final Set<Rect> f3620 = new HashSet();
        /* renamed from: ˎ */
        int f3621 = 0;

        C1539b() {
        }
    }

    /* renamed from: com.moat.analytics.mobile.cha.u$c */
    static class C1540c {
        /* renamed from: ˊ */
        double f3622 = 0.0d;
        /* renamed from: ˋ */
        Rect f3623 = new Rect(0, 0, 0, 0);
        /* renamed from: ॱ */
        double f3624 = 0.0d;

        C1540c() {
        }
    }

    C1541u() {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: ˋ */
    final void m3904(java.lang.String r19, android.view.View r20) {
        /*
        r18 = this;
        r10 = new java.util.HashMap;
        r10.<init>();
        r3 = "{}";
        r4 = 0;
        if (r20 == 0) goto L_0x04ea;
    L_0x000a:
        r2 = 1;
    L_0x000b:
        switch(r2) {
            case 0: goto L_0x0269;
            default: goto L_0x000e;
        };
    L_0x000e:
        r2 = f3625;
        r2 = r2 + 5;
        r5 = r2 % 128;
        f3626 = r5;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x001a;
    L_0x001a:
        r2 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x03c0 }
        r5 = 17;
        if (r2 < r5) goto L_0x04ed;
    L_0x0020:
        r2 = 0;
    L_0x0021:
        switch(r2) {
            case 1: goto L_0x0057;
            default: goto L_0x0024;
        };
    L_0x0024:
        r2 = f3626;
        r2 = r2 + 113;
        r5 = r2 % 128;
        f3625 = r5;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x0030;
    L_0x0030:
        r2 = com.moat.analytics.mobile.cha.C1492c.f3463;	 Catch:{ Exception -> 0x03c0 }
        if (r2 == 0) goto L_0x04f0;
    L_0x0034:
        r2 = 57;
    L_0x0036:
        switch(r2) {
            case 25: goto L_0x0057;
            default: goto L_0x0039;
        };
    L_0x0039:
        r2 = f3625;
        r2 = r2 + 57;
        r5 = r2 % 128;
        f3626 = r5;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x0577;
    L_0x0045:
        r2 = 1;
    L_0x0046:
        switch(r2) {
            case 0: goto L_0x026a;
            default: goto L_0x0049;
        };
    L_0x0049:
        r2 = com.moat.analytics.mobile.cha.C1492c.f3463;	 Catch:{ Exception -> 0x03c0 }
        r2 = r2.get();	 Catch:{ Exception -> 0x03c0 }
        r2 = (android.app.Activity) r2;	 Catch:{ Exception -> 0x03c0 }
        if (r2 == 0) goto L_0x057a;
    L_0x0053:
        r5 = 1;
    L_0x0054:
        switch(r5) {
            case 1: goto L_0x0279;
            default: goto L_0x0057;
        };	 Catch:{ Exception -> 0x03c0 }
    L_0x0057:
        r2 = r20.getContext();	 Catch:{ Exception -> 0x03c0 }
        r2 = r2.getResources();	 Catch:{ Exception -> 0x03c0 }
        r2 = r2.getDisplayMetrics();	 Catch:{ Exception -> 0x03c0 }
        r9 = r2;
    L_0x0064:
        r2 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x03c0 }
        r5 = 19;
        if (r2 < r5) goto L_0x04f8;
    L_0x006a:
        r2 = 0;
    L_0x006b:
        switch(r2) {
            case 0: goto L_0x028c;
            default: goto L_0x006e;
        };
    L_0x006e:
        if (r20 == 0) goto L_0x0501;
    L_0x0070:
        r2 = 57;
    L_0x0072:
        switch(r2) {
            case 57: goto L_0x0488;
            default: goto L_0x0075;
        };
    L_0x0075:
        r2 = 0;
        r8 = r2;
    L_0x0077:
        if (r20 == 0) goto L_0x0509;
    L_0x0079:
        r2 = 54;
    L_0x007b:
        switch(r2) {
            case 37: goto L_0x02d4;
            default: goto L_0x007e;
        };
    L_0x007e:
        r2 = f3626;
        r2 = r2 + 101;
        r5 = r2 % 128;
        f3625 = r5;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x0581;
    L_0x008a:
        r2 = 91;
    L_0x008c:
        switch(r2) {
            case 91: goto L_0x04a4;
            default: goto L_0x008f;
        };
    L_0x008f:
        r2 = r20.hasWindowFocus();	 Catch:{ Exception -> 0x03c0 }
        if (r2 == 0) goto L_0x050d;
    L_0x0095:
        r2 = 14;
    L_0x0097:
        switch(r2) {
            case 16: goto L_0x02d4;
            default: goto L_0x009a;
        };	 Catch:{ Exception -> 0x03c0 }
    L_0x009a:
        r2 = 1;
        r5 = r2;
    L_0x009c:
        if (r20 == 0) goto L_0x0511;
    L_0x009e:
        r2 = 82;
    L_0x00a0:
        switch(r2) {
            case 4: goto L_0x00ae;
            default: goto L_0x00a3;
        };	 Catch:{ Exception -> 0x03c0 }
    L_0x00a3:
        r2 = r20.isShown();	 Catch:{ Exception -> 0x03c0 }
        if (r2 != 0) goto L_0x0514;
    L_0x00a9:
        r2 = 64;
    L_0x00ab:
        switch(r2) {
            case 37: goto L_0x02d8;
            default: goto L_0x00ae;
        };	 Catch:{ Exception -> 0x03c0 }
    L_0x00ae:
        r2 = 1;
        r7 = r2;
    L_0x00b0:
        if (r20 != 0) goto L_0x0518;
    L_0x00b2:
        r2 = 1;
    L_0x00b3:
        switch(r2) {
            case 1: goto L_0x02dc;
            default: goto L_0x00b6;
        };	 Catch:{ Exception -> 0x03c0 }
    L_0x00b6:
        r2 = com.moat.analytics.mobile.cha.C1541u.m3894(r20);	 Catch:{ Exception -> 0x03c0 }
        r6 = r2;
    L_0x00bb:
        r2 = "dr";
        r11 = r9.density;	 Catch:{ Exception -> 0x03c0 }
        r11 = java.lang.Float.valueOf(r11);	 Catch:{ Exception -> 0x03c0 }
        r10.put(r2, r11);	 Catch:{ Exception -> 0x03c0 }
        r2 = "dv";
        r12 = com.moat.analytics.mobile.cha.C1526r.m3867();	 Catch:{ Exception -> 0x03c0 }
        r11 = java.lang.Double.valueOf(r12);	 Catch:{ Exception -> 0x03c0 }
        r10.put(r2, r11);	 Catch:{ Exception -> 0x03c0 }
        r2 = "adKey";
        r0 = r19;
        r10.put(r2, r0);	 Catch:{ Exception -> 0x03c0 }
        r11 = "isAttached";
        if (r8 == 0) goto L_0x051b;
    L_0x00de:
        r2 = 23;
    L_0x00e0:
        switch(r2) {
            case 23: goto L_0x04ad;
            default: goto L_0x00e3;
        };	 Catch:{ Exception -> 0x03c0 }
    L_0x00e3:
        r2 = 0;
    L_0x00e4:
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ Exception -> 0x03c0 }
        r10.put(r11, r2);	 Catch:{ Exception -> 0x03c0 }
        r11 = "inFocus";
        if (r5 == 0) goto L_0x051f;
    L_0x00ef:
        r2 = 85;
    L_0x00f1:
        switch(r2) {
            case 85: goto L_0x02e3;
            default: goto L_0x00f4;
        };	 Catch:{ Exception -> 0x03c0 }
    L_0x00f4:
        r2 = 0;
    L_0x00f5:
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ Exception -> 0x03c0 }
        r10.put(r11, r2);	 Catch:{ Exception -> 0x03c0 }
        r11 = "isHidden";
        if (r7 == 0) goto L_0x0522;
    L_0x0100:
        r2 = 48;
    L_0x0102:
        switch(r2) {
            case 48: goto L_0x04c1;
            default: goto L_0x0105;
        };	 Catch:{ Exception -> 0x03c0 }
    L_0x0105:
        r2 = 0;
    L_0x0106:
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ Exception -> 0x03c0 }
        r10.put(r11, r2);	 Catch:{ Exception -> 0x03c0 }
        r2 = "opacity";
        r6 = java.lang.Float.valueOf(r6);	 Catch:{ Exception -> 0x03c0 }
        r10.put(r2, r6);	 Catch:{ Exception -> 0x03c0 }
        r2 = r9.widthPixels;	 Catch:{ Exception -> 0x03c0 }
        r6 = r9.heightPixels;	 Catch:{ Exception -> 0x03c0 }
        r11 = new android.graphics.Rect;	 Catch:{ Exception -> 0x03c0 }
        r12 = 0;
        r13 = 0;
        r11.<init>(r12, r13, r2, r6);	 Catch:{ Exception -> 0x03c0 }
        if (r20 == 0) goto L_0x0526;
    L_0x0123:
        r2 = 88;
    L_0x0125:
        switch(r2) {
            case 88: goto L_0x02f9;
            default: goto L_0x0128;
        };	 Catch:{ Exception -> 0x03c0 }
    L_0x0128:
        r2 = new android.graphics.Rect;	 Catch:{ Exception -> 0x03c0 }
        r6 = 0;
        r12 = 0;
        r13 = 0;
        r14 = 0;
        r2.<init>(r6, r12, r13, r14);	 Catch:{ Exception -> 0x03c0 }
        r6 = r2;
    L_0x0132:
        r12 = new com.moat.analytics.mobile.cha.u$c;	 Catch:{ Exception -> 0x03c0 }
        r12.<init>();	 Catch:{ Exception -> 0x03c0 }
        r2 = r6.width();	 Catch:{ Exception -> 0x03c0 }
        r13 = r6.height();	 Catch:{ Exception -> 0x03c0 }
        r13 = r13 * r2;
        if (r20 == 0) goto L_0x052a;
    L_0x0142:
        r2 = 5;
    L_0x0143:
        switch(r2) {
            case 5: goto L_0x0300;
            default: goto L_0x0146;
        };	 Catch:{ Exception -> 0x03c0 }
    L_0x0146:
        r0 = r18;
        r2 = r0.f3631;	 Catch:{ Exception -> 0x03c0 }
        if (r2 == 0) goto L_0x054e;
    L_0x014c:
        r2 = 1;
    L_0x014d:
        switch(r2) {
            case 0: goto L_0x018a;
            default: goto L_0x0150;
        };
    L_0x0150:
        r2 = f3626;
        r2 = r2 + 39;
        r5 = r2 % 128;
        f3625 = r5;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x03d0;
    L_0x015c:
        r14 = r12.f3622;	 Catch:{ Exception -> 0x03c0 }
        r0 = r18;
        r2 = r0.f3633;	 Catch:{ Exception -> 0x03c0 }
        r0 = r2.f3622;	 Catch:{ Exception -> 0x03c0 }
        r16 = r0;
        r2 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1));
        if (r2 != 0) goto L_0x018a;
    L_0x016a:
        r2 = r12.f3623;	 Catch:{ Exception -> 0x03c0 }
        r0 = r18;
        r5 = r0.f3633;	 Catch:{ Exception -> 0x03c0 }
        r5 = r5.f3623;	 Catch:{ Exception -> 0x03c0 }
        r2 = r2.equals(r5);	 Catch:{ Exception -> 0x03c0 }
        if (r2 == 0) goto L_0x0555;
    L_0x0178:
        r2 = 1;
    L_0x0179:
        switch(r2) {
            case 0: goto L_0x018a;
            default: goto L_0x017c;
        };	 Catch:{ Exception -> 0x03c0 }
    L_0x017c:
        r14 = r12.f3624;	 Catch:{ Exception -> 0x03c0 }
        r0 = r18;
        r2 = r0.f3633;	 Catch:{ Exception -> 0x03c0 }
        r0 = r2.f3624;	 Catch:{ Exception -> 0x03c0 }
        r16 = r0;
        r2 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1));
        if (r2 == 0) goto L_0x01a6;
    L_0x018a:
        r0 = r18;
        r0.f3633 = r12;	 Catch:{ Exception -> 0x03c0 }
        r2 = new org.json.JSONObject;	 Catch:{ Exception -> 0x03c0 }
        r0 = r18;
        r4 = r0.f3633;	 Catch:{ Exception -> 0x03c0 }
        r4 = r4.f3623;	 Catch:{ Exception -> 0x03c0 }
        r4 = com.moat.analytics.mobile.cha.C1541u.m3896(r4, r9);	 Catch:{ Exception -> 0x03c0 }
        r4 = com.moat.analytics.mobile.cha.C1541u.m3901(r4);	 Catch:{ Exception -> 0x03c0 }
        r2.<init>(r4);	 Catch:{ Exception -> 0x03c0 }
        r0 = r18;
        r0.f3631 = r2;	 Catch:{ Exception -> 0x03c0 }
        r4 = 1;
    L_0x01a6:
        r2 = "coveredPercent";
        r12 = r12.f3624;	 Catch:{ Exception -> 0x03c0 }
        r5 = java.lang.Double.valueOf(r12);	 Catch:{ Exception -> 0x03c0 }
        r10.put(r2, r5);	 Catch:{ Exception -> 0x03c0 }
        r0 = r18;
        r2 = r0.f3630;	 Catch:{ Exception -> 0x03c0 }
        if (r2 == 0) goto L_0x0558;
    L_0x01b7:
        r2 = 1;
    L_0x01b8:
        switch(r2) {
            case 0: goto L_0x01d1;
            default: goto L_0x01bb;
        };
    L_0x01bb:
        r2 = f3626;
        r2 = r2 + 37;
        r5 = r2 % 128;
        f3625 = r5;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x01c7;
    L_0x01c7:
        r0 = r18;
        r2 = r0.f3632;	 Catch:{ Exception -> 0x03c0 }
        r2 = r11.equals(r2);	 Catch:{ Exception -> 0x03c0 }
        if (r2 != 0) goto L_0x01e7;
    L_0x01d1:
        r0 = r18;
        r0.f3632 = r11;	 Catch:{ Exception -> 0x03c0 }
        r2 = new org.json.JSONObject;	 Catch:{ Exception -> 0x03c0 }
        r4 = com.moat.analytics.mobile.cha.C1541u.m3896(r11, r9);	 Catch:{ Exception -> 0x03c0 }
        r4 = com.moat.analytics.mobile.cha.C1541u.m3901(r4);	 Catch:{ Exception -> 0x03c0 }
        r2.<init>(r4);	 Catch:{ Exception -> 0x03c0 }
        r0 = r18;
        r0.f3630 = r2;	 Catch:{ Exception -> 0x03c0 }
        r4 = 1;
    L_0x01e7:
        r0 = r18;
        r2 = r0.f3628;	 Catch:{ Exception -> 0x03c0 }
        if (r2 == 0) goto L_0x055b;
    L_0x01ed:
        r2 = 18;
    L_0x01ef:
        switch(r2) {
            case 71: goto L_0x01fc;
            default: goto L_0x01f2;
        };	 Catch:{ Exception -> 0x03c0 }
    L_0x01f2:
        r0 = r18;
        r2 = r0.f3629;	 Catch:{ Exception -> 0x03c0 }
        r2 = r6.equals(r2);	 Catch:{ Exception -> 0x03c0 }
        if (r2 != 0) goto L_0x0212;
    L_0x01fc:
        r0 = r18;
        r0.f3629 = r6;	 Catch:{ Exception -> 0x03c0 }
        r2 = new org.json.JSONObject;	 Catch:{ Exception -> 0x03c0 }
        r4 = com.moat.analytics.mobile.cha.C1541u.m3896(r6, r9);	 Catch:{ Exception -> 0x03c0 }
        r4 = com.moat.analytics.mobile.cha.C1541u.m3901(r4);	 Catch:{ Exception -> 0x03c0 }
        r2.<init>(r4);	 Catch:{ Exception -> 0x03c0 }
        r0 = r18;
        r0.f3628 = r2;	 Catch:{ Exception -> 0x03c0 }
        r4 = 1;
    L_0x0212:
        r0 = r18;
        r2 = r0.f3635;	 Catch:{ Exception -> 0x03c0 }
        if (r2 == 0) goto L_0x055f;
    L_0x0218:
        r2 = 0;
    L_0x0219:
        switch(r2) {
            case 1: goto L_0x0236;
            default: goto L_0x021c;
        };
    L_0x021c:
        r2 = f3625;
        r2 = r2 + 113;
        r5 = r2 % 128;
        f3626 = r5;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x0228;
    L_0x0228:
        r0 = r18;
        r2 = r0.f3635;	 Catch:{ Exception -> 0x03c0 }
        r2 = r10.equals(r2);	 Catch:{ Exception -> 0x03c0 }
        if (r2 != 0) goto L_0x0562;
    L_0x0232:
        r2 = 0;
    L_0x0233:
        switch(r2) {
            case 1: goto L_0x0590;
            default: goto L_0x0236;
        };	 Catch:{ Exception -> 0x03c0 }
    L_0x0236:
        r0 = r18;
        r0.f3635 = r10;	 Catch:{ Exception -> 0x03c0 }
        r2 = 1;
    L_0x023b:
        r4 = com.moat.analytics.mobile.cha.C1517n.m3826();	 Catch:{ Exception -> 0x03c0 }
        r5 = r4.m3835();	 Catch:{ Exception -> 0x03c0 }
        r0 = r18;
        r4 = r0.f3627;	 Catch:{ Exception -> 0x03c0 }
        r4 = com.moat.analytics.mobile.cha.C1517n.m3825(r5, r4);	 Catch:{ Exception -> 0x03c0 }
        if (r4 != 0) goto L_0x0565;
    L_0x024d:
        r4 = 21;
    L_0x024f:
        switch(r4) {
            case 93: goto L_0x0263;
            default: goto L_0x0252;
        };
    L_0x0252:
        r2 = f3626;
        r2 = r2 + 81;
        r4 = r2 % 128;
        f3625 = r4;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x025e;
    L_0x025e:
        r2 = 1;
        r0 = r18;
        r0.f3627 = r5;	 Catch:{ Exception -> 0x03c0 }
    L_0x0263:
        if (r2 == 0) goto L_0x0569;
    L_0x0265:
        r2 = 0;
    L_0x0266:
        switch(r2) {
            case 0: goto L_0x03e5;
            default: goto L_0x0269;
        };	 Catch:{ Exception -> 0x03c0 }
    L_0x0269:
        return;
    L_0x026a:
        r2 = com.moat.analytics.mobile.cha.C1492c.f3463;	 Catch:{ Exception -> 0x03c0 }
        r2 = r2.get();	 Catch:{ Exception -> 0x03c0 }
        r2 = (android.app.Activity) r2;	 Catch:{ Exception -> 0x03c0 }
        if (r2 == 0) goto L_0x04f4;
    L_0x0274:
        r5 = 60;
    L_0x0276:
        switch(r5) {
            case 48: goto L_0x0057;
            default: goto L_0x0279;
        };	 Catch:{ Exception -> 0x03c0 }
    L_0x0279:
        r5 = new android.util.DisplayMetrics;	 Catch:{ Exception -> 0x03c0 }
        r5.<init>();	 Catch:{ Exception -> 0x03c0 }
        r2 = r2.getWindowManager();	 Catch:{ Exception -> 0x03c0 }
        r2 = r2.getDefaultDisplay();	 Catch:{ Exception -> 0x03c0 }
        r2.getRealMetrics(r5);	 Catch:{ Exception -> 0x03c0 }
        r9 = r5;
        goto L_0x0064;
    L_0x028c:
        r2 = f3625;
        r2 = r2 + 51;
        r5 = r2 % 128;
        f3626 = r5;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x0298;
    L_0x0298:
        if (r20 == 0) goto L_0x04fb;
    L_0x029a:
        r2 = 1;
    L_0x029b:
        switch(r2) {
            case 0: goto L_0x02a8;
            default: goto L_0x029e;
        };
    L_0x029e:
        r2 = r20.isAttachedToWindow();	 Catch:{ Exception -> 0x03c0 }
        if (r2 == 0) goto L_0x04fe;
    L_0x02a4:
        r2 = 1;
    L_0x02a5:
        switch(r2) {
            case 1: goto L_0x02ac;
            default: goto L_0x02a8;
        };
    L_0x02a8:
        r2 = 0;
        r8 = r2;
        goto L_0x0077;
    L_0x02ac:
        r2 = f3626;
        r2 = r2 + 69;
        r5 = r2 % 128;
        f3625 = r5;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x02b8;
    L_0x02b8:
        r2 = 1;
        r8 = r2;
        goto L_0x0077;
    L_0x02bc:
        r2 = 65;
    L_0x02be:
        switch(r2) {
            case 1: goto L_0x0497;
            default: goto L_0x02c1;
        };
    L_0x02c1:
        r2 = r20.getWindowToken();	 Catch:{ Exception -> 0x03c0 }
        if (r2 == 0) goto L_0x0505;
    L_0x02c7:
        r2 = 91;
    L_0x02c9:
        switch(r2) {
            case 69: goto L_0x0075;
            default: goto L_0x02cc;
        };
    L_0x02cc:
        r2 = 1;
        r8 = r2;
        goto L_0x0077;
    L_0x02d0:
        r2 = 0;
    L_0x02d1:
        switch(r2) {
            case 1: goto L_0x009a;
            default: goto L_0x02d4;
        };
    L_0x02d4:
        r2 = 0;
        r5 = r2;
        goto L_0x009c;
    L_0x02d8:
        r2 = 0;
        r7 = r2;
        goto L_0x00b0;
    L_0x02dc:
        r2 = 0;
        r6 = r2;
        goto L_0x00bb;
    L_0x02e0:
        r2 = 1;
        goto L_0x00e4;
    L_0x02e3:
        r2 = f3625;
        r2 = r2 + 19;
        r12 = r2 % 128;
        f3626 = r12;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x02ef;
    L_0x02ef:
        r2 = 1;
        goto L_0x00f5;
    L_0x02f2:
        r2 = 0;
    L_0x02f3:
        switch(r2) {
            case 1: goto L_0x04d0;
            default: goto L_0x02f6;
        };
    L_0x02f6:
        r2 = 1;
        goto L_0x0106;
    L_0x02f9:
        r2 = com.moat.analytics.mobile.cha.C1541u.m3902(r20);	 Catch:{ Exception -> 0x03c0 }
        r6 = r2;
        goto L_0x0132;
    L_0x0300:
        r2 = f3626;
        r2 = r2 + 21;
        r14 = r2 % 128;
        f3625 = r14;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x030c;
    L_0x030c:
        if (r8 == 0) goto L_0x052e;
    L_0x030e:
        r2 = 67;
    L_0x0310:
        switch(r2) {
            case 86: goto L_0x0146;
            default: goto L_0x0313;
        };
    L_0x0313:
        if (r5 == 0) goto L_0x0532;
    L_0x0315:
        r2 = 10;
    L_0x0317:
        switch(r2) {
            case 40: goto L_0x0146;
            default: goto L_0x031a;
        };
    L_0x031a:
        r2 = f3625;
        r2 = r2 + 87;
        r5 = r2 % 128;
        f3626 = r5;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x0326;
    L_0x0326:
        if (r7 != 0) goto L_0x0536;
    L_0x0328:
        r2 = 41;
    L_0x032a:
        switch(r2) {
            case 78: goto L_0x0146;
            default: goto L_0x032d;
        };
    L_0x032d:
        if (r13 <= 0) goto L_0x053a;
    L_0x032f:
        r2 = 1;
    L_0x0330:
        switch(r2) {
            case 1: goto L_0x0335;
            default: goto L_0x0333;
        };
    L_0x0333:
        goto L_0x0146;
    L_0x0335:
        r2 = f3626;
        r2 = r2 + 101;
        r5 = r2 % 128;
        f3625 = r5;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x0589;
    L_0x0341:
        r2 = 38;
    L_0x0343:
        switch(r2) {
            case 38: goto L_0x04d3;
            default: goto L_0x0346;
        };
    L_0x0346:
        r5 = new android.graphics.Rect;	 Catch:{ Exception -> 0x03c0 }
        r2 = 0;
        r7 = 0;
        r8 = 0;
        r14 = 0;
        r5.<init>(r2, r7, r8, r14);	 Catch:{ Exception -> 0x03c0 }
        r0 = r20;
        r2 = com.moat.analytics.mobile.cha.C1541u.m3899(r0, r5);	 Catch:{ Exception -> 0x03c0 }
        if (r2 == 0) goto L_0x053e;
    L_0x0357:
        r2 = 9;
    L_0x0359:
        switch(r2) {
            case 9: goto L_0x035e;
            default: goto L_0x035c;
        };	 Catch:{ Exception -> 0x03c0 }
    L_0x035c:
        goto L_0x0146;
    L_0x035e:
        r2 = r5.width();	 Catch:{ Exception -> 0x03c0 }
        r7 = r5.height();	 Catch:{ Exception -> 0x03c0 }
        r7 = r7 * r2;
        if (r7 >= r13) goto L_0x0542;
    L_0x0369:
        r2 = 1;
    L_0x036a:
        switch(r2) {
            case 0: goto L_0x0375;
            default: goto L_0x036d;
        };	 Catch:{ Exception -> 0x03c0 }
    L_0x036d:
        r2 = "VisibilityInfo";
        r8 = 0;
        r14 = "Ad is clipped";
        com.moat.analytics.mobile.cha.C1487a.m3716(r2, r8, r14);	 Catch:{ Exception -> 0x03c0 }
    L_0x0375:
        r2 = r20.getRootView();	 Catch:{ Exception -> 0x03c0 }
        r2 = r2 instanceof android.view.ViewGroup;	 Catch:{ Exception -> 0x03c0 }
        if (r2 == 0) goto L_0x0545;
    L_0x037d:
        r2 = 1;
    L_0x037e:
        switch(r2) {
            case 1: goto L_0x0383;
            default: goto L_0x0381;
        };	 Catch:{ Exception -> 0x03c0 }
    L_0x0381:
        goto L_0x0146;
    L_0x0383:
        r12.f3623 = r5;	 Catch:{ Exception -> 0x03c0 }
        r0 = r20;
        r8 = com.moat.analytics.mobile.cha.C1541u.m3898(r5, r0);	 Catch:{ Exception -> 0x03c0 }
        r2 = r8.f3619;	 Catch:{ Exception -> 0x03c0 }
        if (r2 == 0) goto L_0x0548;
    L_0x038f:
        r2 = 1;
    L_0x0390:
        switch(r2) {
            case 1: goto L_0x03ca;
            default: goto L_0x0393;
        };	 Catch:{ Exception -> 0x03c0 }
    L_0x0393:
        r2 = r8.f3620;	 Catch:{ Exception -> 0x03c0 }
        r5 = com.moat.analytics.mobile.cha.C1541u.m3895(r5, r2);	 Catch:{ Exception -> 0x03c0 }
        if (r5 <= 0) goto L_0x054b;
    L_0x039b:
        r2 = 90;
    L_0x039d:
        switch(r2) {
            case 4: goto L_0x03b4;
            default: goto L_0x03a0;
        };
    L_0x03a0:
        r2 = f3626;
        r2 = r2 + 35;
        r8 = r2 % 128;
        f3625 = r8;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x03ac;
    L_0x03ac:
        r14 = (double) r5;
        r0 = (double) r7;
        r16 = r0;
        r14 = r14 / r16;
        r12.f3624 = r14;	 Catch:{ Exception -> 0x03c0 }
    L_0x03b4:
        r2 = r7 - r5;
        r14 = (double) r2;	 Catch:{ Exception -> 0x03c0 }
        r0 = (double) r13;	 Catch:{ Exception -> 0x03c0 }
        r16 = r0;
        r14 = r14 / r16;
        r12.f3622 = r14;	 Catch:{ Exception -> 0x03c0 }
        goto L_0x0146;
    L_0x03c0:
        r2 = move-exception;
        com.moat.analytics.mobile.cha.C1518o.m3840(r2);
        r0 = r18;
        r0.f3634 = r3;
        goto L_0x0269;
    L_0x03ca:
        r14 = 4607182418800017408; // 0x3ff0000000000000 float:0.0 double:1.0;
        r12.f3624 = r14;	 Catch:{ Exception -> 0x03c0 }
        goto L_0x0146;
    L_0x03d0:
        r14 = r12.f3622;	 Catch:{ Exception -> 0x03c0 }
        r0 = r18;
        r2 = r0.f3633;	 Catch:{ Exception -> 0x03c0 }
        r0 = r2.f3622;	 Catch:{ Exception -> 0x03c0 }
        r16 = r0;
        r2 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1));
        if (r2 != 0) goto L_0x0551;
    L_0x03de:
        r2 = 38;
    L_0x03e0:
        switch(r2) {
            case 71: goto L_0x018a;
            default: goto L_0x03e3;
        };	 Catch:{ Exception -> 0x03c0 }
    L_0x03e3:
        goto L_0x016a;
    L_0x03e5:
        r6 = new org.json.JSONObject;	 Catch:{ Exception -> 0x03c0 }
        r0 = r18;
        r2 = r0.f3635;	 Catch:{ Exception -> 0x03c0 }
        r6.<init>(r2);	 Catch:{ Exception -> 0x03c0 }
        r2 = "screen";
        r0 = r18;
        r4 = r0.f3630;	 Catch:{ Exception -> 0x03c0 }
        r6.accumulate(r2, r4);	 Catch:{ Exception -> 0x03c0 }
        r2 = "view";
        r0 = r18;
        r4 = r0.f3628;	 Catch:{ Exception -> 0x03c0 }
        r6.accumulate(r2, r4);	 Catch:{ Exception -> 0x03c0 }
        r2 = "visible";
        r0 = r18;
        r4 = r0.f3631;	 Catch:{ Exception -> 0x03c0 }
        r6.accumulate(r2, r4);	 Catch:{ Exception -> 0x03c0 }
        r2 = "maybe";
        r0 = r18;
        r4 = r0.f3631;	 Catch:{ Exception -> 0x03c0 }
        r6.accumulate(r2, r4);	 Catch:{ Exception -> 0x03c0 }
        r2 = "visiblePercent";
        r0 = r18;
        r4 = r0.f3633;	 Catch:{ Exception -> 0x03c0 }
        r8 = r4.f3622;	 Catch:{ Exception -> 0x03c0 }
        r4 = java.lang.Double.valueOf(r8);	 Catch:{ Exception -> 0x03c0 }
        r6.accumulate(r2, r4);	 Catch:{ Exception -> 0x03c0 }
        if (r5 == 0) goto L_0x056c;
    L_0x0423:
        r2 = 62;
    L_0x0425:
        switch(r2) {
            case 60: goto L_0x0441;
            default: goto L_0x0428;
        };	 Catch:{ Exception -> 0x03c0 }
    L_0x0428:
        r7 = "location";
        if (r5 != 0) goto L_0x0570;
    L_0x042c:
        r2 = 1;
    L_0x042d:
        switch(r2) {
            case 0: goto L_0x044b;
            default: goto L_0x0430;
        };	 Catch:{ Exception -> 0x03c0 }
    L_0x0430:
        r2 = 0;
        r4 = r2;
    L_0x0432:
        if (r4 != 0) goto L_0x0573;
    L_0x0434:
        r2 = 14;
    L_0x0436:
        switch(r2) {
            case 14: goto L_0x0486;
            default: goto L_0x0439;
        };	 Catch:{ Exception -> 0x03c0 }
    L_0x0439:
        r2 = new org.json.JSONObject;	 Catch:{ Exception -> 0x03c0 }
        r2.<init>(r4);	 Catch:{ Exception -> 0x03c0 }
    L_0x043e:
        r6.accumulate(r7, r2);	 Catch:{ Exception -> 0x03c0 }
    L_0x0441:
        r3 = r6.toString();	 Catch:{ Exception -> 0x03c0 }
        r0 = r18;
        r0.f3634 = r3;	 Catch:{ Exception -> 0x03c0 }
        goto L_0x0269;
    L_0x044b:
        r2 = new java.util.HashMap;	 Catch:{ Exception -> 0x03c0 }
        r2.<init>();	 Catch:{ Exception -> 0x03c0 }
        r4 = "latitude";
        r8 = r5.getLatitude();	 Catch:{ Exception -> 0x03c0 }
        r8 = java.lang.Double.toString(r8);	 Catch:{ Exception -> 0x03c0 }
        r2.put(r4, r8);	 Catch:{ Exception -> 0x03c0 }
        r4 = "longitude";
        r8 = r5.getLongitude();	 Catch:{ Exception -> 0x03c0 }
        r8 = java.lang.Double.toString(r8);	 Catch:{ Exception -> 0x03c0 }
        r2.put(r4, r8);	 Catch:{ Exception -> 0x03c0 }
        r4 = "timestamp";
        r8 = r5.getTime();	 Catch:{ Exception -> 0x03c0 }
        r8 = java.lang.Long.toString(r8);	 Catch:{ Exception -> 0x03c0 }
        r2.put(r4, r8);	 Catch:{ Exception -> 0x03c0 }
        r4 = "horizontalAccuracy";
        r5 = r5.getAccuracy();	 Catch:{ Exception -> 0x03c0 }
        r5 = java.lang.Float.toString(r5);	 Catch:{ Exception -> 0x03c0 }
        r2.put(r4, r5);	 Catch:{ Exception -> 0x03c0 }
        r4 = r2;
        goto L_0x0432;
    L_0x0486:
        r2 = 0;
        goto L_0x043e;
    L_0x0488:
        r2 = f3626;
        r2 = r2 + 45;
        r5 = r2 % 128;
        f3625 = r5;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x02bc;
    L_0x0494:
        r2 = 1;
        goto L_0x02be;
    L_0x0497:
        r2 = r20.getWindowToken();	 Catch:{ Exception -> 0x03c0 }
        if (r2 == 0) goto L_0x057d;
    L_0x049d:
        r2 = 90;
    L_0x049f:
        switch(r2) {
            case 90: goto L_0x02cc;
            default: goto L_0x04a2;
        };	 Catch:{ Exception -> 0x03c0 }
    L_0x04a2:
        goto L_0x0075;
    L_0x04a4:
        r2 = r20.hasWindowFocus();	 Catch:{ Exception -> 0x03c0 }
        if (r2 == 0) goto L_0x02d0;
    L_0x04aa:
        r2 = 1;
        goto L_0x02d1;
    L_0x04ad:
        r2 = f3626;
        r2 = r2 + 89;
        r12 = r2 % 128;
        f3625 = r12;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x0585;
    L_0x04b9:
        r2 = 46;
    L_0x04bb:
        switch(r2) {
            case 79: goto L_0x02e0;
            default: goto L_0x04be;
        };
    L_0x04be:
        r2 = 1;
        goto L_0x00e4;
    L_0x04c1:
        r2 = f3626;
        r2 = r2 + 27;
        r12 = r2 % 128;
        f3625 = r12;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x02f2;
    L_0x04cd:
        r2 = 1;
        goto L_0x02f3;
    L_0x04d0:
        r2 = 1;
        goto L_0x0106;
    L_0x04d3:
        r5 = new android.graphics.Rect;	 Catch:{ Exception -> 0x03c0 }
        r2 = 0;
        r7 = 0;
        r8 = 0;
        r14 = 0;
        r5.<init>(r2, r7, r8, r14);	 Catch:{ Exception -> 0x03c0 }
        r0 = r20;
        r2 = com.moat.analytics.mobile.cha.C1541u.m3899(r0, r5);	 Catch:{ Exception -> 0x03c0 }
        if (r2 == 0) goto L_0x058d;
    L_0x04e4:
        r2 = 1;
    L_0x04e5:
        switch(r2) {
            case 1: goto L_0x035e;
            default: goto L_0x04e8;
        };
    L_0x04e8:
        goto L_0x0146;
    L_0x04ea:
        r2 = 0;
        goto L_0x000b;
    L_0x04ed:
        r2 = 1;
        goto L_0x0021;
    L_0x04f0:
        r2 = 25;
        goto L_0x0036;
    L_0x04f4:
        r5 = 48;
        goto L_0x0276;
    L_0x04f8:
        r2 = 1;
        goto L_0x006b;
    L_0x04fb:
        r2 = 0;
        goto L_0x029b;
    L_0x04fe:
        r2 = 0;
        goto L_0x02a5;
    L_0x0501:
        r2 = 9;
        goto L_0x0072;
    L_0x0505:
        r2 = 69;
        goto L_0x02c9;
    L_0x0509:
        r2 = 37;
        goto L_0x007b;
    L_0x050d:
        r2 = 16;
        goto L_0x0097;
    L_0x0511:
        r2 = 4;
        goto L_0x00a0;
    L_0x0514:
        r2 = 37;
        goto L_0x00ab;
    L_0x0518:
        r2 = 0;
        goto L_0x00b3;
    L_0x051b:
        r2 = 30;
        goto L_0x00e0;
    L_0x051f:
        r2 = 1;
        goto L_0x00f1;
    L_0x0522:
        r2 = 42;
        goto L_0x0102;
    L_0x0526:
        r2 = 57;
        goto L_0x0125;
    L_0x052a:
        r2 = 74;
        goto L_0x0143;
    L_0x052e:
        r2 = 86;
        goto L_0x0310;
    L_0x0532:
        r2 = 40;
        goto L_0x0317;
    L_0x0536:
        r2 = 78;
        goto L_0x032a;
    L_0x053a:
        r2 = 13;
        goto L_0x0330;
    L_0x053e:
        r2 = 49;
        goto L_0x0359;
    L_0x0542:
        r2 = 0;
        goto L_0x036a;
    L_0x0545:
        r2 = 0;
        goto L_0x037e;
    L_0x0548:
        r2 = 0;
        goto L_0x0390;
    L_0x054b:
        r2 = 4;
        goto L_0x039d;
    L_0x054e:
        r2 = 0;
        goto L_0x014d;
    L_0x0551:
        r2 = 71;
        goto L_0x03e0;
    L_0x0555:
        r2 = 0;
        goto L_0x0179;
    L_0x0558:
        r2 = 0;
        goto L_0x01b8;
    L_0x055b:
        r2 = 71;
        goto L_0x01ef;
    L_0x055f:
        r2 = 1;
        goto L_0x0219;
    L_0x0562:
        r2 = 1;
        goto L_0x0233;
    L_0x0565:
        r4 = 93;
        goto L_0x024f;
    L_0x0569:
        r2 = 1;
        goto L_0x0266;
    L_0x056c:
        r2 = 60;
        goto L_0x0425;
    L_0x0570:
        r2 = 0;
        goto L_0x042d;
    L_0x0573:
        r2 = 85;
        goto L_0x0436;
    L_0x0577:
        r2 = 0;
        goto L_0x0046;
    L_0x057a:
        r5 = 0;
        goto L_0x0054;
    L_0x057d:
        r2 = 20;
        goto L_0x049f;
    L_0x0581:
        r2 = 31;
        goto L_0x008c;
    L_0x0585:
        r2 = 79;
        goto L_0x04bb;
    L_0x0589:
        r2 = 57;
        goto L_0x0343;
    L_0x058d:
        r2 = 0;
        goto L_0x04e5;
    L_0x0590:
        r2 = r4;
        goto L_0x023b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.cha.u.ˋ(java.lang.String, android.view.View):void");
    }

    /* renamed from: ˊ */
    private static float m3894(View view) {
        float alpha = view.getAlpha();
        while (true) {
            switch (view != null ? 25 : 69) {
                case 25:
                    Object obj;
                    if (view.getParent() != null) {
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    switch (obj) {
                        case 1:
                            if (((double) alpha) != 0.0d) {
                                obj = 1;
                            } else {
                                obj = null;
                            }
                            switch (obj) {
                                case 1:
                                    switch (view.getParent() instanceof View ? 27 : 93) {
                                        case 27:
                                            alpha *= ((View) view.getParent()).getAlpha();
                                            view = (View) view.getParent();
                                        default:
                                            break;
                                    }
                                default:
                                    break;
                            }
                        default:
                            break;
                    }
                default:
                    break;
            }
            return alpha;
        }
    }

    /* renamed from: ˋ */
    static Rect m3897(View view) {
        switch (view != null ? 43 : 62) {
            case 43:
                int i = f3626 + 39;
                f3625 = i % 128;
                switch (i % 2 != 0 ? 0 : 1) {
                    case 0:
                        return C1541u.m3902(view);
                    default:
                        return C1541u.m3902(view);
                }
            default:
                return new Rect(0, 0, 0, 0);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: ˏ */
    private static void m3903(com.moat.analytics.mobile.cha.C1541u.C1538a r10, android.graphics.Rect r11, com.moat.analytics.mobile.cha.C1541u.C1539b r12) {
        /*
        r5 = 90;
        r4 = 48;
        r1 = 19;
        r2 = 1;
        r3 = 0;
        r6 = r10.f3618;
        r0 = r6.isShown();
        if (r0 == 0) goto L_0x0191;
    L_0x0010:
        r0 = r1;
    L_0x0011:
        switch(r0) {
            case 74: goto L_0x0023;
            default: goto L_0x0014;
        };
    L_0x0014:
        r0 = r6.getAlpha();
        r6 = (double) r0;
        r8 = 0;
        r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r0 <= 0) goto L_0x0195;
    L_0x001f:
        r0 = r3;
    L_0x0020:
        switch(r0) {
            case 0: goto L_0x0027;
            default: goto L_0x0023;
        };
    L_0x0023:
        r0 = r3;
    L_0x0024:
        if (r0 != 0) goto L_0x0035;
    L_0x0026:
        return;
    L_0x0027:
        r0 = f3625;
        r0 = r0 + 3;
        r6 = r0 % 128;
        f3626 = r6;
        r0 = r0 % 2;
        if (r0 != 0) goto L_0x0033;
    L_0x0033:
        r0 = r2;
        goto L_0x0024;
    L_0x0035:
        r0 = r10.f3618;
        r0 = r0 instanceof android.view.ViewGroup;
        if (r0 == 0) goto L_0x0198;
    L_0x003b:
        r0 = r3;
    L_0x003c:
        switch(r0) {
            case 0: goto L_0x00bb;
            default: goto L_0x003f;
        };
    L_0x003f:
        r1 = r2;
    L_0x0040:
        if (r1 == 0) goto L_0x01ae;
    L_0x0042:
        r0 = 52;
    L_0x0044:
        switch(r0) {
            case 52: goto L_0x0048;
            default: goto L_0x0047;
        };
    L_0x0047:
        goto L_0x0026;
    L_0x0048:
        r0 = r10.f3617;
        r1 = r0.setIntersect(r11, r0);
        if (r1 == 0) goto L_0x0051;
    L_0x0050:
        r4 = r5;
    L_0x0051:
        switch(r4) {
            case 48: goto L_0x0026;
            default: goto L_0x0054;
        };
    L_0x0054:
        r1 = android.os.Build.VERSION.SDK_INT;
        r4 = 22;
        if (r1 < r4) goto L_0x01b2;
    L_0x005a:
        r1 = r3;
    L_0x005b:
        switch(r1) {
            case 0: goto L_0x0146;
            default: goto L_0x005e;
        };
    L_0x005e:
        r1 = com.moat.analytics.mobile.cha.C1536t.m3887();
        r1 = r1.f3608;
        if (r1 == 0) goto L_0x01b7;
    L_0x0066:
        r1 = r2;
    L_0x0067:
        switch(r1) {
            case 0: goto L_0x009b;
            default: goto L_0x006a;
        };
    L_0x006a:
        r1 = "VisibilityInfo";
        r4 = r10.f3618;
        r5 = java.util.Locale.ROOT;
        r6 = "Covered by %s-%s alpha=%f";
        r7 = 3;
        r7 = new java.lang.Object[r7];
        r8 = r10.f3618;
        r8 = r8.getClass();
        r8 = r8.getName();
        r7[r3] = r8;
        r3 = r0.toString();
        r7[r2] = r3;
        r3 = 2;
        r8 = r10.f3618;
        r8 = r8.getAlpha();
        r8 = java.lang.Float.valueOf(r8);
        r7[r3] = r8;
        r3 = java.lang.String.format(r5, r6, r7);
        com.moat.analytics.mobile.cha.C1487a.m3716(r1, r4, r3);
    L_0x009b:
        r1 = r12.f3620;
        r1.add(r0);
        r0 = r0.contains(r11);
        if (r0 == 0) goto L_0x01ba;
    L_0x00a6:
        r0 = 91;
    L_0x00a8:
        switch(r0) {
            case 25: goto L_0x0026;
            default: goto L_0x00ab;
        };
    L_0x00ab:
        r0 = f3626;
        r0 = r0 + 39;
        r1 = r0 % 128;
        f3625 = r1;
        r0 = r0 % 2;
        if (r0 == 0) goto L_0x00b7;
    L_0x00b7:
        r12.f3619 = r2;
        goto L_0x0026;
    L_0x00bb:
        r0 = f3626;
        r0 = r0 + 39;
        r6 = r0 % 128;
        f3625 = r6;
        r0 = r0 % 2;
        if (r0 == 0) goto L_0x00c7;
    L_0x00c7:
        r0 = android.view.ViewGroup.class;
        r6 = r10.f3618;
        r6 = r6.getClass();
        r6 = r6.getSuperclass();
        r6 = r0.equals(r6);
        r7 = r10.f3618;
        r0 = android.os.Build.VERSION.SDK_INT;
        if (r0 < r1) goto L_0x019b;
    L_0x00dd:
        r0 = 4;
    L_0x00de:
        switch(r0) {
            case 4: goto L_0x0163;
            default: goto L_0x00e1;
        };
    L_0x00e1:
        r1 = r2;
    L_0x00e2:
        if (r6 == 0) goto L_0x01a3;
    L_0x00e4:
        r0 = 70;
    L_0x00e6:
        switch(r0) {
            case 70: goto L_0x017a;
            default: goto L_0x00e9;
        };
    L_0x00e9:
        r1 = r2;
    L_0x00ea:
        r0 = r10.f3618;
        r0 = (android.view.ViewGroup) r0;
        r8 = r0.getChildCount();
        r7 = r3;
    L_0x00f3:
        if (r7 >= r8) goto L_0x01aa;
    L_0x00f5:
        r6 = r5;
    L_0x00f6:
        switch(r6) {
            case 99: goto L_0x0040;
            default: goto L_0x00f9;
        };
    L_0x00f9:
        r6 = r12.f3621;
        r6 = r6 + 1;
        r12.f3621 = r6;
        r9 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        if (r6 > r9) goto L_0x0026;
    L_0x0103:
        r6 = new com.moat.analytics.mobile.cha.u$a;
        r9 = r0.getChildAt(r7);
        r6.<init>(r9, r10);
        com.moat.analytics.mobile.cha.C1541u.m3903(r6, r11, r12);
        r6 = r12.f3619;
        if (r6 != 0) goto L_0x0026;
    L_0x0113:
        r6 = r7 + 1;
        r7 = r6;
        goto L_0x00f3;
    L_0x0117:
        r0 = r3;
    L_0x0118:
        switch(r0) {
            case 1: goto L_0x0171;
            default: goto L_0x011b;
        };
    L_0x011b:
        r0 = r7.getBackground();
        if (r0 == 0) goto L_0x019e;
    L_0x0121:
        r0 = 89;
    L_0x0123:
        switch(r0) {
            case 89: goto L_0x012c;
            default: goto L_0x0126;
        };
    L_0x0126:
        r1 = r2;
        goto L_0x00e2;
    L_0x0128:
        r0 = 6;
    L_0x0129:
        switch(r0) {
            case 6: goto L_0x0126;
            default: goto L_0x012c;
        };
    L_0x012c:
        r0 = r7.getBackground();
        r0 = r0.getAlpha();
        if (r0 != 0) goto L_0x01a0;
    L_0x0136:
        r0 = 31;
    L_0x0138:
        switch(r0) {
            case 31: goto L_0x0126;
            default: goto L_0x013b;
        };
    L_0x013b:
        r1 = r3;
        goto L_0x00e2;
    L_0x013d:
        if (r1 == 0) goto L_0x01a7;
    L_0x013f:
        r0 = 83;
    L_0x0141:
        switch(r0) {
            case 57: goto L_0x00e9;
            default: goto L_0x0144;
        };
    L_0x0144:
        r1 = r3;
        goto L_0x00ea;
    L_0x0146:
        r1 = new android.graphics.Rect;
        r1.<init>(r3, r3, r3, r3);
        r0 = r10.f3618;
        r0 = com.moat.analytics.mobile.cha.C1541u.m3899(r0, r1);
        if (r0 == 0) goto L_0x01b5;
    L_0x0153:
        r0 = r3;
    L_0x0154:
        switch(r0) {
            case 0: goto L_0x0159;
            default: goto L_0x0157;
        };
    L_0x0157:
        goto L_0x0026;
    L_0x0159:
        r0 = r10.f3617;
        r1 = r0.setIntersect(r1, r0);
        if (r1 != 0) goto L_0x005e;
    L_0x0161:
        goto L_0x0026;
    L_0x0163:
        r0 = f3626;
        r0 = r0 + 113;
        r1 = r0 % 128;
        f3625 = r1;
        r0 = r0 % 2;
        if (r0 == 0) goto L_0x0117;
    L_0x016f:
        r0 = r2;
        goto L_0x0118;
    L_0x0171:
        r0 = r7.getBackground();
        if (r0 == 0) goto L_0x0128;
    L_0x0177:
        r0 = 61;
        goto L_0x0129;
    L_0x017a:
        r0 = f3626;
        r0 = r0 + 75;
        r6 = r0 % 128;
        f3625 = r6;
        r0 = r0 % 2;
        if (r0 == 0) goto L_0x01be;
    L_0x0186:
        r0 = r3;
    L_0x0187:
        switch(r0) {
            case 1: goto L_0x013d;
            default: goto L_0x018a;
        };
    L_0x018a:
        if (r1 == 0) goto L_0x01c0;
    L_0x018c:
        r0 = r2;
    L_0x018d:
        switch(r0) {
            case 0: goto L_0x00e9;
            default: goto L_0x0190;
        };
    L_0x0190:
        goto L_0x0144;
    L_0x0191:
        r0 = 74;
        goto L_0x0011;
    L_0x0195:
        r0 = r2;
        goto L_0x0020;
    L_0x0198:
        r0 = r2;
        goto L_0x003c;
    L_0x019b:
        r0 = r2;
        goto L_0x00de;
    L_0x019e:
        r0 = r4;
        goto L_0x0123;
    L_0x01a0:
        r0 = 13;
        goto L_0x0138;
    L_0x01a3:
        r0 = 91;
        goto L_0x00e6;
    L_0x01a7:
        r0 = 57;
        goto L_0x0141;
    L_0x01aa:
        r6 = 99;
        goto L_0x00f6;
    L_0x01ae:
        r0 = 16;
        goto L_0x0044;
    L_0x01b2:
        r1 = r2;
        goto L_0x005b;
    L_0x01b5:
        r0 = r2;
        goto L_0x0154;
    L_0x01b7:
        r1 = r3;
        goto L_0x0067;
    L_0x01ba:
        r0 = 25;
        goto L_0x00a8;
    L_0x01be:
        r0 = r2;
        goto L_0x0187;
    L_0x01c0:
        r0 = r3;
        goto L_0x018d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.cha.u.ˏ(com.moat.analytics.mobile.cha.u$a, android.graphics.Rect, com.moat.analytics.mobile.cha.u$b):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.VisibleForTesting
    /* renamed from: ˋ */
    private static com.moat.analytics.mobile.cha.C1541u.C1539b m3898(android.graphics.Rect r12, @android.support.annotation.NonNull android.view.View r13) {
        /*
        r3 = new com.moat.analytics.mobile.cha.u$b;
        r3.<init>();
        r8 = new java.util.ArrayDeque;	 Catch:{ Exception -> 0x0174 }
        r8.<init>();	 Catch:{ Exception -> 0x0174 }
        r0 = 0;
        r1 = r13;
        r2 = r0;
    L_0x000d:
        r0 = r1.getParent();	 Catch:{ Exception -> 0x0174 }
        if (r0 != 0) goto L_0x01d1;
    L_0x0013:
        r0 = 95;
    L_0x0015:
        switch(r0) {
            case 95: goto L_0x017a;
            default: goto L_0x0018;
        };
    L_0x0018:
        r2 = r2 + 1;
        r0 = 50;
        if (r2 <= r0) goto L_0x01d8;
    L_0x001e:
        r0 = 53;
    L_0x0020:
        switch(r0) {
            case 92: goto L_0x0060;
            default: goto L_0x0023;
        };
    L_0x0023:
        r0 = f3625;
        r0 = r0 + 123;
        r1 = r0 % 128;
        f3626 = r1;
        r0 = r0 % 2;
        if (r0 != 0) goto L_0x002f;
    L_0x002f:
        r0 = 3;
        r1 = "VisibilityInfo";
        r2 = 0;
        r4 = "Short-circuiting chain retrieval, reached max";
        com.moat.analytics.mobile.cha.C1487a.m3715(r0, r1, r2, r4);	 Catch:{ Exception -> 0x0174 }
    L_0x0038:
        r0 = r8.isEmpty();	 Catch:{ Exception -> 0x0174 }
        if (r0 == 0) goto L_0x01e0;
    L_0x003e:
        r0 = 73;
    L_0x0040:
        switch(r0) {
            case 64: goto L_0x0078;
            default: goto L_0x0043;
        };
    L_0x0043:
        r0 = f3626;
        r0 = r0 + 125;
        r1 = r0 % 128;
        f3625 = r1;
        r0 = r0 % 2;
        if (r0 == 0) goto L_0x0216;
    L_0x004f:
        r0 = 0;
    L_0x0050:
        switch(r0) {
            case 0: goto L_0x0196;
            default: goto L_0x0053;
        };
    L_0x0053:
        r0 = r3;
    L_0x0054:
        return r0;
    L_0x0055:
        r0 = r13.getRootView();	 Catch:{ Exception -> 0x0174 }
        if (r1 != r0) goto L_0x01d5;
    L_0x005b:
        r0 = 1;
    L_0x005c:
        switch(r0) {
            case 0: goto L_0x0038;
            default: goto L_0x005f;
        };	 Catch:{ Exception -> 0x0174 }
    L_0x005f:
        goto L_0x0018;
    L_0x0060:
        r8.add(r1);	 Catch:{ Exception -> 0x0174 }
        r0 = r1.getParent();	 Catch:{ Exception -> 0x0174 }
        r0 = r0 instanceof android.view.View;	 Catch:{ Exception -> 0x0174 }
        if (r0 == 0) goto L_0x01dc;
    L_0x006b:
        r0 = 29;
    L_0x006d:
        switch(r0) {
            case 49: goto L_0x0038;
            default: goto L_0x0070;
        };	 Catch:{ Exception -> 0x0174 }
    L_0x0070:
        r0 = r1.getParent();	 Catch:{ Exception -> 0x0174 }
        r0 = (android.view.View) r0;	 Catch:{ Exception -> 0x0174 }
        r1 = r0;
        goto L_0x000d;
    L_0x0078:
        r0 = "VisibilityInfo";
        r1 = "starting covering rect search";
        com.moat.analytics.mobile.cha.C1487a.m3716(r0, r13, r1);	 Catch:{ Exception -> 0x0174 }
        r0 = 0;
        r5 = r0;
    L_0x0081:
        r0 = r8.isEmpty();	 Catch:{ Exception -> 0x0174 }
        if (r0 != 0) goto L_0x01e4;
    L_0x0087:
        r0 = 0;
    L_0x0088:
        switch(r0) {
            case 1: goto L_0x00d8;
            default: goto L_0x008b;
        };	 Catch:{ Exception -> 0x0174 }
    L_0x008b:
        r0 = r8.pollLast();	 Catch:{ Exception -> 0x0174 }
        r0 = (android.view.View) r0;	 Catch:{ Exception -> 0x0174 }
        r6 = new com.moat.analytics.mobile.cha.u$a;	 Catch:{ Exception -> 0x0174 }
        r6.<init>(r0, r5);	 Catch:{ Exception -> 0x0174 }
        r1 = r0.getParent();	 Catch:{ Exception -> 0x0174 }
        if (r1 == 0) goto L_0x01e7;
    L_0x009c:
        r1 = 0;
    L_0x009d:
        switch(r1) {
            case 0: goto L_0x0199;
            default: goto L_0x00a0;
        };	 Catch:{ Exception -> 0x0174 }
    L_0x00a0:
        r5 = r6;
        goto L_0x0081;
    L_0x00a2:
        r1 = r0.getParent();	 Catch:{ Exception -> 0x0174 }
        r1 = r1 instanceof android.view.ViewGroup;	 Catch:{ Exception -> 0x0174 }
        if (r1 == 0) goto L_0x01ea;
    L_0x00aa:
        r1 = 18;
    L_0x00ac:
        switch(r1) {
            case 83: goto L_0x00c3;
            default: goto L_0x00af;
        };	 Catch:{ Exception -> 0x0174 }
    L_0x00af:
        r1 = r0.getParent();	 Catch:{ Exception -> 0x0174 }
        r1 = (android.view.ViewGroup) r1;	 Catch:{ Exception -> 0x0174 }
        r9 = r1.getChildCount();	 Catch:{ Exception -> 0x0174 }
        r4 = 0;
        r2 = 0;
        r7 = r2;
        r2 = r4;
    L_0x00bd:
        if (r7 >= r9) goto L_0x01ee;
    L_0x00bf:
        r4 = 1;
    L_0x00c0:
        switch(r4) {
            case 1: goto L_0x01b8;
            default: goto L_0x00c3;
        };	 Catch:{ Exception -> 0x0174 }
    L_0x00c3:
        r5 = r6;
        goto L_0x0081;
    L_0x00c5:
        r4 = r3.f3621;	 Catch:{ Exception -> 0x0174 }
        r10 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        if (r4 < r10) goto L_0x01f1;
    L_0x00cb:
        r4 = 0;
    L_0x00cc:
        switch(r4) {
            case 1: goto L_0x00df;
            default: goto L_0x00cf;
        };	 Catch:{ Exception -> 0x0174 }
    L_0x00cf:
        r0 = 3;
        r1 = "VisibilityInfo";
        r2 = 0;
        r4 = "Short-circuiting cover retrieval, reached max";
        com.moat.analytics.mobile.cha.C1487a.m3715(r0, r1, r2, r4);	 Catch:{ Exception -> 0x0174 }
    L_0x00d8:
        r0 = r3;
        goto L_0x0054;
    L_0x00db:
        r4 = 0;
    L_0x00dc:
        switch(r4) {
            case 1: goto L_0x00cf;
            default: goto L_0x00df;
        };	 Catch:{ Exception -> 0x0174 }
    L_0x00df:
        r10 = r1.getChildAt(r7);	 Catch:{ Exception -> 0x0174 }
        if (r10 != r0) goto L_0x01f4;
    L_0x00e5:
        r4 = 1;
    L_0x00e6:
        switch(r4) {
            case 1: goto L_0x013d;
            default: goto L_0x00e9;
        };	 Catch:{ Exception -> 0x0174 }
    L_0x00e9:
        r4 = r3.f3621;	 Catch:{ Exception -> 0x0174 }
        r4 = r4 + 1;
        r3.f3621 = r4;	 Catch:{ Exception -> 0x0174 }
        if (r2 == 0) goto L_0x01f7;
    L_0x00f1:
        r4 = 0;
    L_0x00f2:
        switch(r4) {
            case 0: goto L_0x013f;
            default: goto L_0x00f5;
        };	 Catch:{ Exception -> 0x0174 }
    L_0x00f5:
        r4 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x0174 }
        r11 = 21;
        if (r4 < r11) goto L_0x0202;
    L_0x00fb:
        r4 = 77;
    L_0x00fd:
        switch(r4) {
            case 19: goto L_0x0121;
            default: goto L_0x0100;
        };
    L_0x0100:
        r4 = f3625;
        r4 = r4 + 115;
        r11 = r4 % 128;
        f3626 = r11;
        r4 = r4 % 2;
        if (r4 != 0) goto L_0x0220;
    L_0x010c:
        r4 = 36;
    L_0x010e:
        switch(r4) {
            case 72: goto L_0x015e;
            default: goto L_0x0111;
        };
    L_0x0111:
        r4 = r10.getZ();	 Catch:{ Exception -> 0x0174 }
        r11 = r0.getZ();	 Catch:{ Exception -> 0x0174 }
        r4 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1));
        if (r4 <= 0) goto L_0x0224;
    L_0x011d:
        r4 = 0;
    L_0x011e:
        switch(r4) {
            case 0: goto L_0x016f;
            default: goto L_0x0121;
        };	 Catch:{ Exception -> 0x0174 }
    L_0x0121:
        r4 = 0;
    L_0x0122:
        if (r4 == 0) goto L_0x0209;
    L_0x0124:
        r4 = 16;
    L_0x0126:
        switch(r4) {
            case 18: goto L_0x0139;
            default: goto L_0x0129;
        };	 Catch:{ Exception -> 0x0174 }
    L_0x0129:
        r4 = new com.moat.analytics.mobile.cha.u$a;	 Catch:{ Exception -> 0x0174 }
        r4.<init>(r10, r5);	 Catch:{ Exception -> 0x0174 }
        com.moat.analytics.mobile.cha.C1541u.m3903(r4, r12, r3);	 Catch:{ Exception -> 0x0174 }
        r4 = r3.f3619;	 Catch:{ Exception -> 0x0174 }
        if (r4 == 0) goto L_0x020d;
    L_0x0135:
        r4 = 0;
    L_0x0136:
        switch(r4) {
            case 0: goto L_0x0171;
            default: goto L_0x0139;
        };	 Catch:{ Exception -> 0x0174 }
    L_0x0139:
        r4 = r7 + 1;
        r7 = r4;
        goto L_0x00bd;
    L_0x013d:
        r2 = 1;
        goto L_0x0139;
    L_0x013f:
        r4 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x0174 }
        r11 = 21;
        if (r4 < r11) goto L_0x01fb;
    L_0x0145:
        r4 = 0;
    L_0x0146:
        switch(r4) {
            case 1: goto L_0x015c;
            default: goto L_0x0149;
        };	 Catch:{ Exception -> 0x0174 }
    L_0x0149:
        r4 = r10.getZ();	 Catch:{ Exception -> 0x0174 }
        r11 = r0.getZ();	 Catch:{ Exception -> 0x0174 }
        r4 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1));
        if (r4 < 0) goto L_0x01fe;
    L_0x0155:
        r4 = 53;
    L_0x0157:
        switch(r4) {
            case 53: goto L_0x015c;
            default: goto L_0x015a;
        };	 Catch:{ Exception -> 0x0174 }
    L_0x015a:
        r4 = 0;
        goto L_0x0122;
    L_0x015c:
        r4 = 1;
        goto L_0x0122;
    L_0x015e:
        r4 = r10.getZ();	 Catch:{ Exception -> 0x0174 }
        r11 = r0.getZ();	 Catch:{ Exception -> 0x0174 }
        r4 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1));
        if (r4 <= 0) goto L_0x0206;
    L_0x016a:
        r4 = 0;
    L_0x016b:
        switch(r4) {
            case 0: goto L_0x016f;
            default: goto L_0x016e;
        };
    L_0x016e:
        goto L_0x0121;
    L_0x016f:
        r4 = 1;
        goto L_0x0122;
    L_0x0171:
        r0 = r3;
        goto L_0x0054;
    L_0x0174:
        r0 = move-exception;
        com.moat.analytics.mobile.cha.C1518o.m3840(r0);
        goto L_0x00d8;
    L_0x017a:
        r0 = f3626;
        r0 = r0 + 81;
        r4 = r0 % 128;
        f3625 = r4;
        r0 = r0 % 2;
        if (r0 == 0) goto L_0x0210;
    L_0x0186:
        r0 = 0;
    L_0x0187:
        switch(r0) {
            case 1: goto L_0x0055;
            default: goto L_0x018a;
        };
    L_0x018a:
        r0 = r13.getRootView();	 Catch:{ Exception -> 0x0174 }
        if (r1 != r0) goto L_0x0213;
    L_0x0190:
        r0 = 1;
    L_0x0191:
        switch(r0) {
            case 1: goto L_0x0018;
            default: goto L_0x0194;
        };
    L_0x0194:
        goto L_0x0038;
    L_0x0196:
        r0 = r3;
        goto L_0x0054;
    L_0x0199:
        r1 = f3625;
        r1 = r1 + 19;
        r2 = r1 % 128;
        f3626 = r2;
        r1 = r1 % 2;
        if (r1 != 0) goto L_0x0219;
    L_0x01a5:
        r1 = 0;
    L_0x01a6:
        switch(r1) {
            case 1: goto L_0x00a2;
            default: goto L_0x01a9;
        };
    L_0x01a9:
        r1 = r0.getParent();	 Catch:{ Exception -> 0x0174 }
        r1 = r1 instanceof android.view.ViewGroup;	 Catch:{ Exception -> 0x0174 }
        if (r1 == 0) goto L_0x021b;
    L_0x01b1:
        r1 = 90;
    L_0x01b3:
        switch(r1) {
            case 71: goto L_0x00c3;
            default: goto L_0x01b6;
        };
    L_0x01b6:
        goto L_0x00af;
    L_0x01b8:
        r4 = f3625;
        r4 = r4 + 25;
        r10 = r4 % 128;
        f3626 = r10;
        r4 = r4 % 2;
        if (r4 != 0) goto L_0x021e;
    L_0x01c4:
        r4 = 0;
    L_0x01c5:
        switch(r4) {
            case 1: goto L_0x00c5;
            default: goto L_0x01c8;
        };
    L_0x01c8:
        r4 = r3.f3621;	 Catch:{ Exception -> 0x0174 }
        r10 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        if (r4 < r10) goto L_0x00db;
    L_0x01ce:
        r4 = 1;
        goto L_0x00dc;
    L_0x01d1:
        r0 = 73;
        goto L_0x0015;
    L_0x01d5:
        r0 = 0;
        goto L_0x005c;
    L_0x01d8:
        r0 = 92;
        goto L_0x0020;
    L_0x01dc:
        r0 = 49;
        goto L_0x006d;
    L_0x01e0:
        r0 = 64;
        goto L_0x0040;
    L_0x01e4:
        r0 = 1;
        goto L_0x0088;
    L_0x01e7:
        r1 = 1;
        goto L_0x009d;
    L_0x01ea:
        r1 = 83;
        goto L_0x00ac;
    L_0x01ee:
        r4 = 0;
        goto L_0x00c0;
    L_0x01f1:
        r4 = 1;
        goto L_0x00cc;
    L_0x01f4:
        r4 = 0;
        goto L_0x00e6;
    L_0x01f7:
        r4 = 49;
        goto L_0x00f2;
    L_0x01fb:
        r4 = 1;
        goto L_0x0146;
    L_0x01fe:
        r4 = 94;
        goto L_0x0157;
    L_0x0202:
        r4 = 19;
        goto L_0x00fd;
    L_0x0206:
        r4 = 1;
        goto L_0x016b;
    L_0x0209:
        r4 = 18;
        goto L_0x0126;
    L_0x020d:
        r4 = 1;
        goto L_0x0136;
    L_0x0210:
        r0 = 1;
        goto L_0x0187;
    L_0x0213:
        r0 = 0;
        goto L_0x0191;
    L_0x0216:
        r0 = 1;
        goto L_0x0050;
    L_0x0219:
        r1 = 1;
        goto L_0x01a6;
    L_0x021b:
        r1 = 71;
        goto L_0x01b3;
    L_0x021e:
        r4 = 1;
        goto L_0x01c5;
    L_0x0220:
        r4 = 72;
        goto L_0x010e;
    L_0x0224:
        r4 = 1;
        goto L_0x011e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.cha.u.ˋ(android.graphics.Rect, android.view.View):com.moat.analytics.mobile.cha.u$b");
    }

    @VisibleForTesting
    /* renamed from: ˋ */
    private static int m3895(Rect rect, Set<Rect> set) {
        if (!set.isEmpty()) {
            List arrayList = new ArrayList();
            arrayList.addAll(set);
            Collections.sort(arrayList, new C15371());
            List arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (true) {
                Object obj;
                if (it.hasNext()) {
                    obj = 1;
                } else {
                    obj = null;
                }
                int i;
                Rect rect2;
                switch (obj) {
                    case null:
                        Collections.sort(arrayList2);
                        int i2 = 0;
                        int i3 = 0;
                        while (true) {
                            switch (i2 < arrayList2.size() + -1 ? 38 : 85) {
                                case 85:
                                    return i3;
                                default:
                                    if (((Integer) arrayList2.get(i2)).equals(arrayList2.get(i2 + 1))) {
                                        i = 1;
                                    } else {
                                        obj = null;
                                    }
                                    switch (obj) {
                                        case null:
                                            Rect rect3 = new Rect(((Integer) arrayList2.get(i2)).intValue(), rect.top, ((Integer) arrayList2.get(i2 + 1)).intValue(), rect.bottom);
                                            i = rect.top;
                                            Iterator it2 = arrayList.iterator();
                                            int i4 = i3;
                                            i3 = i;
                                            while (true) {
                                                switch (it2.hasNext() ? 25 : 9) {
                                                    case 9:
                                                        i3 = i4;
                                                        break;
                                                    default:
                                                        i = f3626 + 23;
                                                        f3625 = i % 128;
                                                        if (i % 2 != 0) {
                                                            rect2 = (Rect) it2.next();
                                                        } else {
                                                            rect2 = (Rect) it2.next();
                                                        }
                                                        switch (Rect.intersects(rect2, rect3) ? 1 : null) {
                                                            case 1:
                                                                switch (rect2.bottom > i3 ? 16 : 65) {
                                                                    case 16:
                                                                        i3 = ((rect2.bottom - Math.max(i3, rect2.top)) * rect3.width()) + i4;
                                                                        i4 = rect2.bottom;
                                                                        break;
                                                                    default:
                                                                        int i5 = i3;
                                                                        i3 = i4;
                                                                        i4 = i5;
                                                                        break;
                                                                }
                                                                if (rect2.bottom != rect3.bottom) {
                                                                    i = i4;
                                                                    break;
                                                                }
                                                                continue;
                                                            default:
                                                                i = i3;
                                                                i3 = i4;
                                                                break;
                                                        }
                                                        i4 = i3;
                                                        i3 = i;
                                                }
                                            }
                                        default:
                                            break;
                                    }
                                    i2++;
                            }
                        }
                    default:
                        i = f3625 + 59;
                        f3626 = i % 128;
                        switch (i % 2 == 0 ? 1 : null) {
                            case 1:
                                rect2 = (Rect) it.next();
                                arrayList2.add(Integer.valueOf(rect2.left));
                                arrayList2.add(Integer.valueOf(rect2.right));
                                break;
                            default:
                                rect2 = (Rect) it.next();
                                arrayList2.add(Integer.valueOf(rect2.left));
                                arrayList2.add(Integer.valueOf(rect2.right));
                                break;
                        }
                }
            }
        }
        return 0;
    }

    /* renamed from: ˎ */
    private static Map<String, String> m3901(Rect rect) {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("x", String.valueOf(rect.left));
        hashMap.put("y", String.valueOf(rect.top));
        hashMap.put("w", String.valueOf(rect.right - rect.left));
        hashMap.put("h", String.valueOf(rect.bottom - rect.top));
        return hashMap;
    }

    /* renamed from: ˋ */
    private static Rect m3896(Rect rect, DisplayMetrics displayMetrics) {
        float f = displayMetrics.density;
        switch (f == 0.0f ? null : 1) {
            case 1:
                return new Rect(Math.round(((float) rect.left) / f), Math.round(((float) rect.top) / f), Math.round(((float) rect.right) / f), Math.round(((float) rect.bottom) / f));
            default:
                return rect;
        }
    }

    /* renamed from: ˋ */
    private static boolean m3899(View view, Rect rect) {
        boolean z;
        if (view.getGlobalVisibleRect(rect)) {
            z = true;
        } else {
            z = true;
        }
        switch (z) {
            case true:
                int[] iArr = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
                view.getLocationInWindow(iArr);
                int[] iArr2 = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
                view.getLocationOnScreen(iArr2);
                rect.offset(iArr2[0] - iArr[0], iArr2[1] - iArr[1]);
                return true;
            default:
                return false;
        }
    }

    /* renamed from: ˏ */
    private static Rect m3902(View view) {
        int[] iArr = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
        view.getLocationOnScreen(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return new Rect(i, i2, view.getWidth() + i, view.getHeight() + i2);
    }
}
