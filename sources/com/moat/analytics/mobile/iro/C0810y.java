package com.moat.analytics.mobile.iro;

import android.graphics.Rect;
import android.location.Location;
import android.support.annotation.VisibleForTesting;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.iro.y */
final class C0810y {
    /* renamed from: ˊॱ */
    private static int f1315 = 0;
    /* renamed from: ˏॱ */
    private static int f1316 = 1;
    /* renamed from: ʻ */
    private Location f1317;
    /* renamed from: ʼ */
    private Map<String, Object> f1318 = new HashMap();
    /* renamed from: ʽ */
    private JSONObject f1319;
    /* renamed from: ˊ */
    private C0808a f1320 = new C0808a();
    /* renamed from: ˋ */
    private Rect f1321;
    /* renamed from: ˎ */
    String f1322 = "{}";
    /* renamed from: ˏ */
    private JSONObject f1323;
    /* renamed from: ॱ */
    private Rect f1324;
    /* renamed from: ᐝ */
    private JSONObject f1325;

    /* renamed from: com.moat.analytics.mobile.iro.y$2 */
    static class C08072 implements Comparator<Rect> {
        C08072() {
        }

        public final /* synthetic */ int compare(Object obj, Object obj2) {
            return Integer.valueOf(((Rect) obj).top).compareTo(Integer.valueOf(((Rect) obj2).top));
        }
    }

    /* renamed from: com.moat.analytics.mobile.iro.y$a */
    static class C0808a {
        /* renamed from: ˊ */
        Rect f1309 = new Rect(0, 0, 0, 0);
        /* renamed from: ˋ */
        double f1310 = 0.0d;
        /* renamed from: ˎ */
        double f1311 = 0.0d;

        C0808a() {
        }
    }

    /* renamed from: com.moat.analytics.mobile.iro.y$b */
    static class C0809b {
        /* renamed from: ˊ */
        final View f1312;
        /* renamed from: ˋ */
        final boolean f1313;
        /* renamed from: ॱ */
        final int f1314;

        C0809b(View view, int i, boolean z) {
            this.f1312 = view;
            this.f1314 = i;
            this.f1313 = z;
        }
    }

    C0810y() {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: ˎ */
    final void m1419(java.lang.String r19, android.view.View r20) {
        /*
        r18 = this;
        r10 = new java.util.HashMap;
        r10.<init>();
        r3 = "{}";
        r4 = 0;
        if (r20 == 0) goto L_0x050a;
    L_0x000a:
        r2 = 78;
    L_0x000c:
        switch(r2) {
            case 78: goto L_0x0010;
            default: goto L_0x000f;
        };
    L_0x000f:
        return;
    L_0x0010:
        r2 = f1316;
        r2 = r2 + 31;
        r5 = r2 % 128;
        f1315 = r5;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x001c;
    L_0x001c:
        r2 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x0270 }
        r5 = 17;
        if (r2 < r5) goto L_0x050d;
    L_0x0022:
        r2 = 0;
    L_0x0023:
        switch(r2) {
            case 0: goto L_0x027a;
            default: goto L_0x0026;
        };	 Catch:{ Exception -> 0x0270 }
    L_0x0026:
        r2 = r20.getContext();	 Catch:{ Exception -> 0x0270 }
        r2 = r2.getResources();	 Catch:{ Exception -> 0x0270 }
        r2 = r2.getDisplayMetrics();	 Catch:{ Exception -> 0x0270 }
        r9 = r2;
    L_0x0033:
        r2 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x0270 }
        r5 = 19;
        if (r2 < r5) goto L_0x0517;
    L_0x0039:
        r2 = 0;
    L_0x003a:
        switch(r2) {
            case 0: goto L_0x02c0;
            default: goto L_0x003d;
        };	 Catch:{ Exception -> 0x0270 }
    L_0x003d:
        if (r20 == 0) goto L_0x0522;
    L_0x003f:
        r2 = 25;
    L_0x0041:
        switch(r2) {
            case 26: goto L_0x02db;
            default: goto L_0x0044;
        };	 Catch:{ Exception -> 0x0270 }
    L_0x0044:
        r2 = r20.getWindowToken();	 Catch:{ Exception -> 0x0270 }
        if (r2 == 0) goto L_0x0526;
    L_0x004a:
        r2 = 66;
    L_0x004c:
        switch(r2) {
            case 38: goto L_0x02db;
            default: goto L_0x004f;
        };
    L_0x004f:
        r2 = 1;
        r8 = r2;
    L_0x0051:
        if (r20 == 0) goto L_0x052a;
    L_0x0053:
        r2 = 1;
    L_0x0054:
        switch(r2) {
            case 1: goto L_0x02df;
            default: goto L_0x0057;
        };
    L_0x0057:
        r2 = 0;
        r7 = r2;
    L_0x0059:
        if (r20 == 0) goto L_0x0530;
    L_0x005b:
        r2 = 0;
    L_0x005c:
        switch(r2) {
            case 0: goto L_0x04af;
            default: goto L_0x005f;
        };
    L_0x005f:
        r2 = 1;
        r5 = r2;
    L_0x0061:
        if (r20 != 0) goto L_0x0537;
    L_0x0063:
        r2 = 48;
    L_0x0065:
        switch(r2) {
            case 37: goto L_0x0303;
            default: goto L_0x0068;
        };
    L_0x0068:
        r2 = f1316;
        r2 = r2 + 67;
        r6 = r2 % 128;
        f1315 = r6;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x0074;
    L_0x0074:
        r2 = 0;
        r6 = r2;
    L_0x0076:
        r2 = "dr";
        r11 = r9.density;	 Catch:{ Exception -> 0x0270 }
        r11 = java.lang.Float.valueOf(r11);	 Catch:{ Exception -> 0x0270 }
        r10.put(r2, r11);	 Catch:{ Exception -> 0x0270 }
        r2 = "dv";
        r12 = com.moat.analytics.mobile.iro.C0789p.m1366();	 Catch:{ Exception -> 0x0270 }
        r11 = java.lang.Double.valueOf(r12);	 Catch:{ Exception -> 0x0270 }
        r10.put(r2, r11);	 Catch:{ Exception -> 0x0270 }
        r2 = "adKey";
        r0 = r19;
        r10.put(r2, r0);	 Catch:{ Exception -> 0x0270 }
        r11 = "isAttached";
        if (r8 == 0) goto L_0x053b;
    L_0x0099:
        r2 = 1;
    L_0x009a:
        switch(r2) {
            case 1: goto L_0x030a;
            default: goto L_0x009d;
        };	 Catch:{ Exception -> 0x0270 }
    L_0x009d:
        r2 = 0;
    L_0x009e:
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ Exception -> 0x0270 }
        r10.put(r11, r2);	 Catch:{ Exception -> 0x0270 }
        r11 = "inFocus";
        if (r7 == 0) goto L_0x053e;
    L_0x00a9:
        r2 = 1;
    L_0x00aa:
        switch(r2) {
            case 1: goto L_0x0319;
            default: goto L_0x00ad;
        };	 Catch:{ Exception -> 0x0270 }
    L_0x00ad:
        r2 = 0;
    L_0x00ae:
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ Exception -> 0x0270 }
        r10.put(r11, r2);	 Catch:{ Exception -> 0x0270 }
        r11 = "isHidden";
        if (r5 == 0) goto L_0x0541;
    L_0x00b9:
        r2 = 1;
    L_0x00ba:
        switch(r2) {
            case 1: goto L_0x031c;
            default: goto L_0x00bd;
        };	 Catch:{ Exception -> 0x0270 }
    L_0x00bd:
        r2 = 0;
    L_0x00be:
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ Exception -> 0x0270 }
        r10.put(r11, r2);	 Catch:{ Exception -> 0x0270 }
        r2 = "opacity";
        r6 = java.lang.Float.valueOf(r6);	 Catch:{ Exception -> 0x0270 }
        r10.put(r2, r6);	 Catch:{ Exception -> 0x0270 }
        r2 = r9.widthPixels;	 Catch:{ Exception -> 0x0270 }
        r6 = r9.heightPixels;	 Catch:{ Exception -> 0x0270 }
        r11 = new android.graphics.Rect;	 Catch:{ Exception -> 0x0270 }
        r12 = 0;
        r13 = 0;
        r11.<init>(r12, r13, r2, r6);	 Catch:{ Exception -> 0x0270 }
        if (r20 == 0) goto L_0x0544;
    L_0x00db:
        r2 = 0;
    L_0x00dc:
        switch(r2) {
            case 1: goto L_0x031f;
            default: goto L_0x00df;
        };	 Catch:{ Exception -> 0x0270 }
    L_0x00df:
        r2 = com.moat.analytics.mobile.iro.C0810y.m1417(r20);	 Catch:{ Exception -> 0x0270 }
        r6 = r2;
    L_0x00e4:
        r12 = new com.moat.analytics.mobile.iro.y$a;	 Catch:{ Exception -> 0x0270 }
        r12.<init>();	 Catch:{ Exception -> 0x0270 }
        r2 = r6.width();	 Catch:{ Exception -> 0x0270 }
        r13 = r6.height();	 Catch:{ Exception -> 0x0270 }
        r13 = r13 * r2;
        if (r20 == 0) goto L_0x0547;
    L_0x00f4:
        r2 = 34;
    L_0x00f6:
        switch(r2) {
            case 55: goto L_0x014f;
            default: goto L_0x00f9;
        };
    L_0x00f9:
        r2 = f1315;
        r2 = r2 + 45;
        r14 = r2 % 128;
        f1316 = r14;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x05a0;
    L_0x0105:
        r2 = 1;
    L_0x0106:
        switch(r2) {
            case 0: goto L_0x032b;
            default: goto L_0x0109;
        };
    L_0x0109:
        if (r8 == 0) goto L_0x05a3;
    L_0x010b:
        r2 = 0;
    L_0x010c:
        switch(r2) {
            case 1: goto L_0x014f;
            default: goto L_0x010f;
        };
    L_0x010f:
        if (r7 == 0) goto L_0x054f;
    L_0x0111:
        r2 = 0;
    L_0x0112:
        switch(r2) {
            case 1: goto L_0x014f;
            default: goto L_0x0115;
        };
    L_0x0115:
        if (r5 != 0) goto L_0x0552;
    L_0x0117:
        r2 = 18;
    L_0x0119:
        switch(r2) {
            case 91: goto L_0x014f;
            default: goto L_0x011c;
        };
    L_0x011c:
        r2 = f1316;
        r2 = r2 + 17;
        r5 = r2 % 128;
        f1315 = r5;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x0128;
    L_0x0128:
        if (r13 <= 0) goto L_0x0556;
    L_0x012a:
        r2 = 0;
    L_0x012b:
        switch(r2) {
            case 1: goto L_0x014f;
            default: goto L_0x012e;
        };
    L_0x012e:
        r2 = f1316;
        r2 = r2 + 121;
        r5 = r2 % 128;
        f1315 = r5;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x013a;
    L_0x013a:
        r5 = new android.graphics.Rect;	 Catch:{ Exception -> 0x0270 }
        r2 = 0;
        r7 = 0;
        r8 = 0;
        r14 = 0;
        r5.<init>(r2, r7, r8, r14);	 Catch:{ Exception -> 0x0270 }
        r0 = r20;
        r2 = r0.getGlobalVisibleRect(r5);	 Catch:{ Exception -> 0x0270 }
        if (r2 == 0) goto L_0x0559;
    L_0x014b:
        r2 = 1;
    L_0x014c:
        switch(r2) {
            case 1: goto L_0x0334;
            default: goto L_0x014f;
        };	 Catch:{ Exception -> 0x0270 }
    L_0x014f:
        r0 = r18;
        r2 = r0.f1323;	 Catch:{ Exception -> 0x0270 }
        if (r2 == 0) goto L_0x056f;
    L_0x0155:
        r2 = 0;
    L_0x0156:
        switch(r2) {
            case 0: goto L_0x04cc;
            default: goto L_0x0159;
        };	 Catch:{ Exception -> 0x0270 }
    L_0x0159:
        r0 = r18;
        r0.f1320 = r12;	 Catch:{ Exception -> 0x0270 }
        r2 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0270 }
        r0 = r18;
        r4 = r0.f1320;	 Catch:{ Exception -> 0x0270 }
        r4 = r4.f1309;	 Catch:{ Exception -> 0x0270 }
        r4 = com.moat.analytics.mobile.iro.C0810y.m1413(r4, r9);	 Catch:{ Exception -> 0x0270 }
        r4 = com.moat.analytics.mobile.iro.C0810y.m1418(r4);	 Catch:{ Exception -> 0x0270 }
        r2.<init>(r4);	 Catch:{ Exception -> 0x0270 }
        r0 = r18;
        r0.f1323 = r2;	 Catch:{ Exception -> 0x0270 }
        r4 = 1;
    L_0x0175:
        r2 = "coveredPercent";
        r12 = r12.f1311;	 Catch:{ Exception -> 0x0270 }
        r5 = java.lang.Double.valueOf(r12);	 Catch:{ Exception -> 0x0270 }
        r10.put(r2, r5);	 Catch:{ Exception -> 0x0270 }
        r0 = r18;
        r2 = r0.f1319;	 Catch:{ Exception -> 0x0270 }
        if (r2 == 0) goto L_0x0579;
    L_0x0186:
        r2 = 1;
    L_0x0187:
        switch(r2) {
            case 0: goto L_0x01a4;
            default: goto L_0x018a;
        };
    L_0x018a:
        r2 = f1315;
        r2 = r2 + 9;
        r5 = r2 % 128;
        f1316 = r5;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x05a9;
    L_0x0196:
        r2 = 7;
    L_0x0197:
        switch(r2) {
            case 89: goto L_0x03fc;
            default: goto L_0x019a;
        };
    L_0x019a:
        r0 = r18;
        r2 = r0.f1324;	 Catch:{ Exception -> 0x0270 }
        r2 = r11.equals(r2);	 Catch:{ Exception -> 0x0270 }
        if (r2 != 0) goto L_0x01ba;
    L_0x01a4:
        r0 = r18;
        r0.f1324 = r11;	 Catch:{ Exception -> 0x0270 }
        r2 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0270 }
        r4 = com.moat.analytics.mobile.iro.C0810y.m1413(r11, r9);	 Catch:{ Exception -> 0x0270 }
        r4 = com.moat.analytics.mobile.iro.C0810y.m1418(r4);	 Catch:{ Exception -> 0x0270 }
        r2.<init>(r4);	 Catch:{ Exception -> 0x0270 }
        r0 = r18;
        r0.f1319 = r2;	 Catch:{ Exception -> 0x0270 }
        r4 = 1;
    L_0x01ba:
        r0 = r18;
        r2 = r0.f1325;	 Catch:{ Exception -> 0x0270 }
        if (r2 == 0) goto L_0x057c;
    L_0x01c0:
        r2 = 0;
    L_0x01c1:
        switch(r2) {
            case 0: goto L_0x0408;
            default: goto L_0x01c4;
        };	 Catch:{ Exception -> 0x0270 }
    L_0x01c4:
        r0 = r18;
        r0.f1321 = r6;	 Catch:{ Exception -> 0x0270 }
        r2 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0270 }
        r4 = com.moat.analytics.mobile.iro.C0810y.m1413(r6, r9);	 Catch:{ Exception -> 0x0270 }
        r4 = com.moat.analytics.mobile.iro.C0810y.m1418(r4);	 Catch:{ Exception -> 0x0270 }
        r2.<init>(r4);	 Catch:{ Exception -> 0x0270 }
        r0 = r18;
        r0.f1325 = r2;	 Catch:{ Exception -> 0x0270 }
        r4 = 1;
    L_0x01da:
        r0 = r18;
        r2 = r0.f1318;	 Catch:{ Exception -> 0x0270 }
        if (r2 == 0) goto L_0x057f;
    L_0x01e0:
        r2 = 0;
    L_0x01e1:
        switch(r2) {
            case 0: goto L_0x04f1;
            default: goto L_0x01e4;
        };	 Catch:{ Exception -> 0x0270 }
    L_0x01e4:
        r0 = r18;
        r0.f1318 = r10;	 Catch:{ Exception -> 0x0270 }
        r2 = 1;
    L_0x01e9:
        r4 = com.moat.analytics.mobile.iro.C0777k.m1332();	 Catch:{ Exception -> 0x0270 }
        r5 = r4.m1341();	 Catch:{ Exception -> 0x0270 }
        r0 = r18;
        r4 = r0.f1317;	 Catch:{ Exception -> 0x0270 }
        r4 = com.moat.analytics.mobile.iro.C0777k.m1333(r5, r4);	 Catch:{ Exception -> 0x0270 }
        if (r4 != 0) goto L_0x0585;
    L_0x01fb:
        r4 = 1;
    L_0x01fc:
        switch(r4) {
            case 0: goto L_0x0210;
            default: goto L_0x01ff;
        };
    L_0x01ff:
        r2 = f1316;
        r2 = r2 + 103;
        r4 = r2 % 128;
        f1315 = r4;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x0425;
    L_0x020b:
        r2 = 1;
        r0 = r18;
        r0.f1317 = r5;	 Catch:{ Exception -> 0x0270 }
    L_0x0210:
        if (r2 == 0) goto L_0x0588;
    L_0x0212:
        r2 = 0;
    L_0x0213:
        switch(r2) {
            case 0: goto L_0x0218;
            default: goto L_0x0216;
        };
    L_0x0216:
        goto L_0x000f;
    L_0x0218:
        r2 = f1315;
        r2 = r2 + 57;
        r4 = r2 % 128;
        f1316 = r4;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x0224;
    L_0x0224:
        r6 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0270 }
        r0 = r18;
        r2 = r0.f1318;	 Catch:{ Exception -> 0x0270 }
        r6.<init>(r2);	 Catch:{ Exception -> 0x0270 }
        r2 = "screen";
        r0 = r18;
        r4 = r0.f1319;	 Catch:{ Exception -> 0x0270 }
        r6.accumulate(r2, r4);	 Catch:{ Exception -> 0x0270 }
        r2 = "view";
        r0 = r18;
        r4 = r0.f1325;	 Catch:{ Exception -> 0x0270 }
        r6.accumulate(r2, r4);	 Catch:{ Exception -> 0x0270 }
        r2 = "visible";
        r0 = r18;
        r4 = r0.f1323;	 Catch:{ Exception -> 0x0270 }
        r6.accumulate(r2, r4);	 Catch:{ Exception -> 0x0270 }
        r2 = "maybe";
        r0 = r18;
        r4 = r0.f1323;	 Catch:{ Exception -> 0x0270 }
        r6.accumulate(r2, r4);	 Catch:{ Exception -> 0x0270 }
        r2 = "visiblePercent";
        r0 = r18;
        r4 = r0.f1320;	 Catch:{ Exception -> 0x0270 }
        r8 = r4.f1310;	 Catch:{ Exception -> 0x0270 }
        r4 = java.lang.Double.valueOf(r8);	 Catch:{ Exception -> 0x0270 }
        r6.accumulate(r2, r4);	 Catch:{ Exception -> 0x0270 }
        if (r5 == 0) goto L_0x058b;
    L_0x0262:
        r2 = 1;
    L_0x0263:
        switch(r2) {
            case 1: goto L_0x042c;
            default: goto L_0x0266;
        };	 Catch:{ Exception -> 0x0270 }
    L_0x0266:
        r3 = r6.toString();	 Catch:{ Exception -> 0x0270 }
        r0 = r18;
        r0.f1322 = r3;	 Catch:{ Exception -> 0x0270 }
        goto L_0x000f;
    L_0x0270:
        r2 = move-exception;
        com.moat.analytics.mobile.iro.C0785o.m1351(r2);
        r0 = r18;
        r0.f1322 = r3;
        goto L_0x000f;
    L_0x027a:
        r2 = f1315;
        r2 = r2 + 93;
        r5 = r2 % 128;
        f1316 = r5;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x0286;
    L_0x0286:
        r2 = com.moat.analytics.mobile.iro.C0752a.f1120;	 Catch:{ Exception -> 0x0270 }
        if (r2 == 0) goto L_0x0510;
    L_0x028a:
        r2 = 0;
    L_0x028b:
        switch(r2) {
            case 1: goto L_0x0026;
            default: goto L_0x028e;
        };
    L_0x028e:
        r2 = f1316;
        r2 = r2 + 85;
        r5 = r2 % 128;
        f1315 = r5;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x0595;
    L_0x029a:
        r2 = 1;
    L_0x029b:
        switch(r2) {
            case 1: goto L_0x049e;
            default: goto L_0x029e;
        };
    L_0x029e:
        r2 = com.moat.analytics.mobile.iro.C0752a.f1120;	 Catch:{ Exception -> 0x0270 }
        r2 = r2.get();	 Catch:{ Exception -> 0x0270 }
        r2 = (android.app.Activity) r2;	 Catch:{ Exception -> 0x0270 }
        if (r2 == 0) goto L_0x0513;
    L_0x02a8:
        r5 = 96;
    L_0x02aa:
        switch(r5) {
            case 59: goto L_0x0026;
            default: goto L_0x02ad;
        };	 Catch:{ Exception -> 0x0270 }
    L_0x02ad:
        r5 = new android.util.DisplayMetrics;	 Catch:{ Exception -> 0x0270 }
        r5.<init>();	 Catch:{ Exception -> 0x0270 }
        r2 = r2.getWindowManager();	 Catch:{ Exception -> 0x0270 }
        r2 = r2.getDefaultDisplay();	 Catch:{ Exception -> 0x0270 }
        r2.getRealMetrics(r5);	 Catch:{ Exception -> 0x0270 }
        r9 = r5;
        goto L_0x0033;
    L_0x02c0:
        if (r20 == 0) goto L_0x051a;
    L_0x02c2:
        r2 = 12;
    L_0x02c4:
        switch(r2) {
            case 12: goto L_0x02cb;
            default: goto L_0x02c7;
        };	 Catch:{ Exception -> 0x0270 }
    L_0x02c7:
        r2 = 0;
        r8 = r2;
        goto L_0x0051;
    L_0x02cb:
        r2 = r20.isAttachedToWindow();	 Catch:{ Exception -> 0x0270 }
        if (r2 == 0) goto L_0x051e;
    L_0x02d1:
        r2 = 33;
    L_0x02d3:
        switch(r2) {
            case 33: goto L_0x02d7;
            default: goto L_0x02d6;
        };	 Catch:{ Exception -> 0x0270 }
    L_0x02d6:
        goto L_0x02c7;
    L_0x02d7:
        r2 = 1;
        r8 = r2;
        goto L_0x0051;
    L_0x02db:
        r2 = 0;
        r8 = r2;
        goto L_0x0051;
    L_0x02df:
        r2 = r20.hasWindowFocus();	 Catch:{ Exception -> 0x0270 }
        if (r2 == 0) goto L_0x052d;
    L_0x02e5:
        r2 = 1;
    L_0x02e6:
        switch(r2) {
            case 1: goto L_0x02eb;
            default: goto L_0x02e9;
        };	 Catch:{ Exception -> 0x0270 }
    L_0x02e9:
        goto L_0x0057;
    L_0x02eb:
        r2 = 1;
        r7 = r2;
        goto L_0x0059;
    L_0x02ef:
        r2 = 46;
    L_0x02f1:
        switch(r2) {
            case 66: goto L_0x04bf;
            default: goto L_0x02f4;
        };	 Catch:{ Exception -> 0x0270 }
    L_0x02f4:
        r2 = r20.isShown();	 Catch:{ Exception -> 0x0270 }
        if (r2 != 0) goto L_0x0533;
    L_0x02fa:
        r2 = 69;
    L_0x02fc:
        switch(r2) {
            case 69: goto L_0x005f;
            default: goto L_0x02ff;
        };	 Catch:{ Exception -> 0x0270 }
    L_0x02ff:
        r2 = 0;
        r5 = r2;
        goto L_0x0061;
    L_0x0303:
        r2 = com.moat.analytics.mobile.iro.C0810y.m1411(r20);	 Catch:{ Exception -> 0x0270 }
        r6 = r2;
        goto L_0x0076;
    L_0x030a:
        r2 = f1315;
        r2 = r2 + 77;
        r12 = r2 % 128;
        f1316 = r12;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x0316;
    L_0x0316:
        r2 = 1;
        goto L_0x009e;
    L_0x0319:
        r2 = 1;
        goto L_0x00ae;
    L_0x031c:
        r2 = 1;
        goto L_0x00be;
    L_0x031f:
        r2 = new android.graphics.Rect;	 Catch:{ Exception -> 0x0270 }
        r6 = 0;
        r12 = 0;
        r13 = 0;
        r14 = 0;
        r2.<init>(r6, r12, r13, r14);	 Catch:{ Exception -> 0x0270 }
        r6 = r2;
        goto L_0x00e4;
    L_0x032b:
        if (r8 == 0) goto L_0x054b;
    L_0x032d:
        r2 = 72;
    L_0x032f:
        switch(r2) {
            case 90: goto L_0x014f;
            default: goto L_0x0332;
        };	 Catch:{ Exception -> 0x0270 }
    L_0x0332:
        goto L_0x010f;
    L_0x0334:
        r2 = r5.width();	 Catch:{ Exception -> 0x0270 }
        r7 = r5.height();	 Catch:{ Exception -> 0x0270 }
        r7 = r7 * r2;
        if (r7 >= r13) goto L_0x055c;
    L_0x033f:
        r2 = 0;
    L_0x0340:
        switch(r2) {
            case 0: goto L_0x0398;
            default: goto L_0x0343;
        };	 Catch:{ Exception -> 0x0270 }
    L_0x0343:
        r8 = new java.util.HashSet;	 Catch:{ Exception -> 0x0270 }
        r8.<init>();	 Catch:{ Exception -> 0x0270 }
        r2 = r20.getRootView();	 Catch:{ Exception -> 0x0270 }
        r2 = r2 instanceof android.view.ViewGroup;	 Catch:{ Exception -> 0x0270 }
        if (r2 == 0) goto L_0x055f;
    L_0x0350:
        r2 = 41;
    L_0x0352:
        switch(r2) {
            case 41: goto L_0x0357;
            default: goto L_0x0355;
        };
    L_0x0355:
        goto L_0x014f;
    L_0x0357:
        r2 = f1315;
        r2 = r2 + 49;
        r14 = r2 % 128;
        f1316 = r14;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x0363;
    L_0x0363:
        r12.f1309 = r5;	 Catch:{ Exception -> 0x0270 }
        r0 = r20;
        r14 = com.moat.analytics.mobile.iro.C0810y.m1416(r5, r0, r8);	 Catch:{ Exception -> 0x0270 }
        if (r14 == 0) goto L_0x0563;
    L_0x036d:
        r2 = 89;
    L_0x036f:
        switch(r2) {
            case 92: goto L_0x0378;
            default: goto L_0x0372;
        };	 Catch:{ Exception -> 0x0270 }
    L_0x0372:
        r16 = 4607182418800017408; // 0x3ff0000000000000 float:0.0 double:1.0;
        r0 = r16;
        r12.f1311 = r0;	 Catch:{ Exception -> 0x0270 }
    L_0x0378:
        if (r14 != 0) goto L_0x0567;
    L_0x037a:
        r2 = 21;
    L_0x037c:
        switch(r2) {
            case 21: goto L_0x0381;
            default: goto L_0x037f;
        };	 Catch:{ Exception -> 0x0270 }
    L_0x037f:
        goto L_0x014f;
    L_0x0381:
        r5 = com.moat.analytics.mobile.iro.C0810y.m1412(r5, r8);	 Catch:{ Exception -> 0x0270 }
        if (r5 <= 0) goto L_0x056b;
    L_0x0387:
        r2 = 93;
    L_0x0389:
        switch(r2) {
            case 93: goto L_0x03ad;
            default: goto L_0x038c;
        };	 Catch:{ Exception -> 0x0270 }
    L_0x038c:
        r2 = r7 - r5;
        r14 = (double) r2;	 Catch:{ Exception -> 0x0270 }
        r0 = (double) r13;	 Catch:{ Exception -> 0x0270 }
        r16 = r0;
        r14 = r14 / r16;
        r12.f1310 = r14;	 Catch:{ Exception -> 0x0270 }
        goto L_0x014f;
    L_0x0398:
        r2 = f1316;
        r2 = r2 + 39;
        r8 = r2 % 128;
        f1315 = r8;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x03a4;
    L_0x03a4:
        r2 = "VisibilityInfo";
        r8 = 0;
        r14 = "Ad is clipped";
        com.moat.analytics.mobile.iro.C0756b.m1235(r2, r8, r14);	 Catch:{ Exception -> 0x0270 }
        goto L_0x0343;
    L_0x03ad:
        r2 = f1315;
        r2 = r2 + 87;
        r8 = r2 % 128;
        f1316 = r8;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x03b9;
    L_0x03b9:
        r14 = (double) r5;
        r0 = (double) r7;
        r16 = r0;
        r14 = r14 / r16;
        r12.f1311 = r14;	 Catch:{ Exception -> 0x0270 }
        goto L_0x038c;
    L_0x03c2:
        r2 = 16;
    L_0x03c4:
        switch(r2) {
            case 66: goto L_0x04dc;
            default: goto L_0x03c7;
        };	 Catch:{ Exception -> 0x0270 }
    L_0x03c7:
        r14 = r12.f1310;	 Catch:{ Exception -> 0x0270 }
        r0 = r18;
        r2 = r0.f1320;	 Catch:{ Exception -> 0x0270 }
        r0 = r2.f1310;	 Catch:{ Exception -> 0x0270 }
        r16 = r0;
        r2 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1));
        if (r2 != 0) goto L_0x0572;
    L_0x03d5:
        r2 = 1;
    L_0x03d6:
        switch(r2) {
            case 0: goto L_0x0159;
            default: goto L_0x03d9;
        };	 Catch:{ Exception -> 0x0270 }
    L_0x03d9:
        r2 = r12.f1309;	 Catch:{ Exception -> 0x0270 }
        r0 = r18;
        r5 = r0.f1320;	 Catch:{ Exception -> 0x0270 }
        r5 = r5.f1309;	 Catch:{ Exception -> 0x0270 }
        r2 = r2.equals(r5);	 Catch:{ Exception -> 0x0270 }
        if (r2 == 0) goto L_0x0575;
    L_0x03e7:
        r2 = 24;
    L_0x03e9:
        switch(r2) {
            case 42: goto L_0x0159;
            default: goto L_0x03ec;
        };	 Catch:{ Exception -> 0x0270 }
    L_0x03ec:
        r14 = r12.f1311;	 Catch:{ Exception -> 0x0270 }
        r0 = r18;
        r2 = r0.f1320;	 Catch:{ Exception -> 0x0270 }
        r0 = r2.f1311;	 Catch:{ Exception -> 0x0270 }
        r16 = r0;
        r2 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1));
        if (r2 == 0) goto L_0x0175;
    L_0x03fa:
        goto L_0x0159;
    L_0x03fc:
        r0 = r18;
        r2 = r0.f1324;	 Catch:{ Exception -> 0x0270 }
        r2 = r11.equals(r2);	 Catch:{ Exception -> 0x0270 }
        if (r2 != 0) goto L_0x01ba;
    L_0x0406:
        goto L_0x01a4;
    L_0x0408:
        r0 = r18;
        r2 = r0.f1321;	 Catch:{ Exception -> 0x0270 }
        r2 = r6.equals(r2);	 Catch:{ Exception -> 0x0270 }
        if (r2 != 0) goto L_0x01da;
    L_0x0412:
        goto L_0x01c4;
    L_0x0414:
        r0 = r18;
        r2 = r0.f1318;	 Catch:{ Exception -> 0x0270 }
        r2 = r10.equals(r2);	 Catch:{ Exception -> 0x0270 }
        if (r2 != 0) goto L_0x0582;
    L_0x041e:
        r2 = 1;
    L_0x041f:
        switch(r2) {
            case 1: goto L_0x01e4;
            default: goto L_0x0422;
        };	 Catch:{ Exception -> 0x0270 }
    L_0x0422:
        r2 = r4;
        goto L_0x01e9;
    L_0x0425:
        r2 = 1;
        r0 = r18;
        r0.f1317 = r5;	 Catch:{ Exception -> 0x0270 }
        goto L_0x0210;
    L_0x042c:
        r7 = "location";
        if (r5 != 0) goto L_0x058e;
    L_0x0430:
        r2 = 66;
    L_0x0432:
        switch(r2) {
            case 88: goto L_0x045b;
            default: goto L_0x0435;
        };
    L_0x0435:
        r2 = f1316;
        r2 = r2 + 95;
        r4 = r2 % 128;
        f1315 = r4;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x0441;
    L_0x0441:
        r2 = 0;
        r4 = r2;
    L_0x0443:
        if (r4 != 0) goto L_0x0592;
    L_0x0445:
        r2 = 0;
    L_0x0446:
        switch(r2) {
            case 1: goto L_0x0498;
            default: goto L_0x0449;
        };
    L_0x0449:
        r2 = f1315;
        r2 = r2 + 1;
        r4 = r2 % 128;
        f1316 = r4;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x0496;
    L_0x0455:
        r2 = 0;
    L_0x0456:
        r6.accumulate(r7, r2);	 Catch:{ Exception -> 0x0270 }
        goto L_0x0266;
    L_0x045b:
        r2 = new java.util.HashMap;	 Catch:{ Exception -> 0x0270 }
        r2.<init>();	 Catch:{ Exception -> 0x0270 }
        r4 = "latitude";
        r8 = r5.getLatitude();	 Catch:{ Exception -> 0x0270 }
        r8 = java.lang.Double.toString(r8);	 Catch:{ Exception -> 0x0270 }
        r2.put(r4, r8);	 Catch:{ Exception -> 0x0270 }
        r4 = "longitude";
        r8 = r5.getLongitude();	 Catch:{ Exception -> 0x0270 }
        r8 = java.lang.Double.toString(r8);	 Catch:{ Exception -> 0x0270 }
        r2.put(r4, r8);	 Catch:{ Exception -> 0x0270 }
        r4 = "timestamp";
        r8 = r5.getTime();	 Catch:{ Exception -> 0x0270 }
        r8 = java.lang.Long.toString(r8);	 Catch:{ Exception -> 0x0270 }
        r2.put(r4, r8);	 Catch:{ Exception -> 0x0270 }
        r4 = "horizontalAccuracy";
        r5 = r5.getAccuracy();	 Catch:{ Exception -> 0x0270 }
        r5 = java.lang.Float.toString(r5);	 Catch:{ Exception -> 0x0270 }
        r2.put(r4, r5);	 Catch:{ Exception -> 0x0270 }
        r4 = r2;
        goto L_0x0443;
    L_0x0496:
        r2 = 0;
        goto L_0x0456;
    L_0x0498:
        r2 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0270 }
        r2.<init>(r4);	 Catch:{ Exception -> 0x0270 }
        goto L_0x0456;
    L_0x049e:
        r2 = com.moat.analytics.mobile.iro.C0752a.f1120;	 Catch:{ Exception -> 0x0270 }
        r2 = r2.get();	 Catch:{ Exception -> 0x0270 }
        r2 = (android.app.Activity) r2;	 Catch:{ Exception -> 0x0270 }
        if (r2 == 0) goto L_0x0598;
    L_0x04a8:
        r5 = 86;
    L_0x04aa:
        switch(r5) {
            case 86: goto L_0x02ad;
            default: goto L_0x04ad;
        };
    L_0x04ad:
        goto L_0x0026;
    L_0x04af:
        r2 = f1315;
        r2 = r2 + 69;
        r5 = r2 % 128;
        f1316 = r5;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x02ef;
    L_0x04bb:
        r2 = 66;
        goto L_0x02f1;
    L_0x04bf:
        r2 = r20.isShown();	 Catch:{ Exception -> 0x0270 }
        if (r2 != 0) goto L_0x059c;
    L_0x04c5:
        r2 = 87;
    L_0x04c7:
        switch(r2) {
            case 52: goto L_0x02ff;
            default: goto L_0x04ca;
        };
    L_0x04ca:
        goto L_0x005f;
    L_0x04cc:
        r2 = f1315;
        r2 = r2 + 99;
        r5 = r2 % 128;
        f1316 = r5;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x03c2;
    L_0x04d8:
        r2 = 66;
        goto L_0x03c4;
    L_0x04dc:
        r14 = r12.f1310;	 Catch:{ Exception -> 0x0270 }
        r0 = r18;
        r2 = r0.f1320;	 Catch:{ Exception -> 0x0270 }
        r0 = r2.f1310;	 Catch:{ Exception -> 0x0270 }
        r16 = r0;
        r2 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1));
        if (r2 != 0) goto L_0x05a6;
    L_0x04ea:
        r2 = 82;
    L_0x04ec:
        switch(r2) {
            case 82: goto L_0x03d9;
            default: goto L_0x04ef;
        };
    L_0x04ef:
        goto L_0x0159;
    L_0x04f1:
        r2 = f1316;
        r2 = r2 + 99;
        r5 = r2 % 128;
        f1315 = r5;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x0414;
    L_0x04fd:
        r0 = r18;
        r2 = r0.f1318;	 Catch:{ Exception -> 0x0270 }
        r2 = r10.equals(r2);	 Catch:{ Exception -> 0x0270 }
        if (r2 == 0) goto L_0x01e4;
    L_0x0507:
        r2 = r4;
        goto L_0x01e9;
    L_0x050a:
        r2 = 0;
        goto L_0x000c;
    L_0x050d:
        r2 = 1;
        goto L_0x0023;
    L_0x0510:
        r2 = 1;
        goto L_0x028b;
    L_0x0513:
        r5 = 59;
        goto L_0x02aa;
    L_0x0517:
        r2 = 1;
        goto L_0x003a;
    L_0x051a:
        r2 = 43;
        goto L_0x02c4;
    L_0x051e:
        r2 = 50;
        goto L_0x02d3;
    L_0x0522:
        r2 = 26;
        goto L_0x0041;
    L_0x0526:
        r2 = 38;
        goto L_0x004c;
    L_0x052a:
        r2 = 0;
        goto L_0x0054;
    L_0x052d:
        r2 = 0;
        goto L_0x02e6;
    L_0x0530:
        r2 = 1;
        goto L_0x005c;
    L_0x0533:
        r2 = 89;
        goto L_0x02fc;
    L_0x0537:
        r2 = 37;
        goto L_0x0065;
    L_0x053b:
        r2 = 0;
        goto L_0x009a;
    L_0x053e:
        r2 = 0;
        goto L_0x00aa;
    L_0x0541:
        r2 = 0;
        goto L_0x00ba;
    L_0x0544:
        r2 = 1;
        goto L_0x00dc;
    L_0x0547:
        r2 = 55;
        goto L_0x00f6;
    L_0x054b:
        r2 = 90;
        goto L_0x032f;
    L_0x054f:
        r2 = 1;
        goto L_0x0112;
    L_0x0552:
        r2 = 91;
        goto L_0x0119;
    L_0x0556:
        r2 = 1;
        goto L_0x012b;
    L_0x0559:
        r2 = 0;
        goto L_0x014c;
    L_0x055c:
        r2 = 1;
        goto L_0x0340;
    L_0x055f:
        r2 = 84;
        goto L_0x0352;
    L_0x0563:
        r2 = 92;
        goto L_0x036f;
    L_0x0567:
        r2 = 18;
        goto L_0x037c;
    L_0x056b:
        r2 = 26;
        goto L_0x0389;
    L_0x056f:
        r2 = 1;
        goto L_0x0156;
    L_0x0572:
        r2 = 0;
        goto L_0x03d6;
    L_0x0575:
        r2 = 42;
        goto L_0x03e9;
    L_0x0579:
        r2 = 0;
        goto L_0x0187;
    L_0x057c:
        r2 = 1;
        goto L_0x01c1;
    L_0x057f:
        r2 = 1;
        goto L_0x01e1;
    L_0x0582:
        r2 = 0;
        goto L_0x041f;
    L_0x0585:
        r4 = 0;
        goto L_0x01fc;
    L_0x0588:
        r2 = 1;
        goto L_0x0213;
    L_0x058b:
        r2 = 0;
        goto L_0x0263;
    L_0x058e:
        r2 = 88;
        goto L_0x0432;
    L_0x0592:
        r2 = 1;
        goto L_0x0446;
    L_0x0595:
        r2 = 0;
        goto L_0x029b;
    L_0x0598:
        r5 = 91;
        goto L_0x04aa;
    L_0x059c:
        r2 = 52;
        goto L_0x04c7;
    L_0x05a0:
        r2 = 0;
        goto L_0x0106;
    L_0x05a3:
        r2 = 1;
        goto L_0x010c;
    L_0x05a6:
        r2 = 3;
        goto L_0x04ec;
    L_0x05a9:
        r2 = 89;
        goto L_0x0197;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.iro.y.ˎ(java.lang.String, android.view.View):void");
    }

    /* renamed from: ˊ */
    private static float m1411(View view) {
        float alpha = view.getAlpha();
        while (true) {
            switch (view != null ? 83 : 99) {
                case 83:
                    switch (view.getParent() != null ? 13 : 18) {
                        case 18:
                            break;
                        default:
                            switch (((double) alpha) != 0.0d ? 68 : 5) {
                                case 5:
                                    break;
                                default:
                                    int i = f1315 + 121;
                                    f1316 = i % 128;
                                    if (i % 2 == 0) {
                                    }
                                    switch (view.getParent() instanceof View ? 72 : 1) {
                                        case 72:
                                            i = f1315 + 117;
                                            f1316 = i % 128;
                                            if (i % 2 == 0) {
                                                alpha *= ((View) view.getParent()).getAlpha();
                                                view = (View) view.getParent();
                                            } else {
                                                alpha *= ((View) view.getParent()).getAlpha();
                                                view = (View) view.getParent();
                                            }
                                        default:
                                            break;
                                    }
                            }
                    }
                default:
                    break;
            }
            return alpha;
        }
    }

    /* renamed from: ˏ */
    static Rect m1414(View view) {
        switch (view != null ? 10 : 90) {
            case 90:
                return new Rect(0, 0, 0, 0);
            default:
                return C0810y.m1417(view);
        }
    }

    /* renamed from: ˏ */
    private static void m1415(ArrayDeque<C0809b> arrayDeque, C0809b c0809b, boolean z) {
        switch (c0809b.f1312 instanceof ViewGroup ? 63 : 47) {
            case 47:
                return;
            default:
                int i = f1316 + 107;
                f1315 = i % 128;
                if (i % 2 != 0) {
                }
                switch (!(c0809b.f1312 instanceof ListView) ? 1 : null) {
                    case 1:
                        ViewGroup viewGroup = (ViewGroup) c0809b.f1312;
                        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                            arrayDeque.add(new C0809b(viewGroup.getChildAt(childCount), c0809b.f1314 + 1, z));
                        }
                        return;
                    default:
                        return;
                }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.VisibleForTesting
    /* renamed from: ˏ */
    private static boolean m1416(android.graphics.Rect r15, @android.support.annotation.NonNull android.view.View r16, java.util.Set<android.graphics.Rect> r17) {
        /*
        r4 = 0;
        r3 = 0;
        r5 = new java.util.ArrayList;	 Catch:{ Exception -> 0x02a6 }
        r5.<init>();	 Catch:{ Exception -> 0x02a6 }
        r2 = 0;
        r6 = r2;
        r7 = r16;
    L_0x000b:
        r2 = r7.getParent();	 Catch:{ Exception -> 0x02a6 }
        if (r2 != 0) goto L_0x032a;
    L_0x0011:
        r2 = 58;
    L_0x0013:
        switch(r2) {
            case 58: goto L_0x02ac;
            default: goto L_0x0016;
        };
    L_0x0016:
        r6 = r6 + 1;
        r2 = 50;
        if (r6 <= r2) goto L_0x0331;
    L_0x001c:
        r2 = 97;
    L_0x001e:
        switch(r2) {
            case 93: goto L_0x0064;
            default: goto L_0x0021;
        };
    L_0x0021:
        r2 = f1316;
        r2 = r2 + 115;
        r5 = r2 % 128;
        f1315 = r5;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x0385;
    L_0x002d:
        r2 = 20;
    L_0x002f:
        switch(r2) {
            case 20: goto L_0x02c8;
            default: goto L_0x0032;
        };
    L_0x0032:
        r2 = 0;
        r7 = r2;
    L_0x0034:
        if (r7 == 0) goto L_0x0339;
    L_0x0036:
        r2 = 9;
    L_0x0038:
        switch(r2) {
            case 77: goto L_0x0056;
            default: goto L_0x003b;
        };
    L_0x003b:
        r2 = f1315;
        r2 = r2 + 81;
        r5 = r2 % 128;
        f1316 = r5;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x038c;
    L_0x0047:
        r2 = 94;
    L_0x0049:
        switch(r2) {
            case 37: goto L_0x008e;
            default: goto L_0x004c;
        };
    L_0x004c:
        r2 = r7.isEmpty();	 Catch:{ Exception -> 0x02a6 }
        if (r2 == 0) goto L_0x0390;
    L_0x0052:
        r2 = 1;
    L_0x0053:
        switch(r2) {
            case 0: goto L_0x0098;
            default: goto L_0x0056;
        };	 Catch:{ Exception -> 0x02a6 }
    L_0x0056:
        r2 = 0;
    L_0x0057:
        return r2;
    L_0x0058:
        r2 = r16.getRootView();	 Catch:{ Exception -> 0x02a6 }
        if (r7 != r2) goto L_0x032e;
    L_0x005e:
        r2 = 1;
    L_0x005f:
        switch(r2) {
            case 1: goto L_0x0016;
            default: goto L_0x0062;
        };	 Catch:{ Exception -> 0x02a6 }
    L_0x0062:
        r7 = r5;
        goto L_0x0034;
    L_0x0064:
        r2 = 0;
        r5.add(r2, r7);	 Catch:{ Exception -> 0x02a6 }
        r2 = r7.getParent();	 Catch:{ Exception -> 0x02a6 }
        r2 = r2 instanceof android.view.View;	 Catch:{ Exception -> 0x02a6 }
        if (r2 == 0) goto L_0x0335;
    L_0x0070:
        r2 = 39;
    L_0x0072:
        switch(r2) {
            case 13: goto L_0x0062;
            default: goto L_0x0075;
        };
    L_0x0075:
        r2 = f1316;
        r2 = r2 + 67;
        r8 = r2 % 128;
        f1315 = r8;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x0389;
    L_0x0081:
        r2 = 0;
    L_0x0082:
        switch(r2) {
            case 0: goto L_0x02cc;
            default: goto L_0x0085;
        };
    L_0x0085:
        r2 = r7.getParent();	 Catch:{ Exception -> 0x02a6 }
        r2 = (android.view.View) r2;	 Catch:{ Exception -> 0x02a6 }
        r7 = r2;
        goto L_0x000b;
    L_0x008e:
        r2 = r7.isEmpty();	 Catch:{ Exception -> 0x02a6 }
        if (r2 == 0) goto L_0x033d;
    L_0x0094:
        r2 = 0;
    L_0x0095:
        switch(r2) {
            case 0: goto L_0x0056;
            default: goto L_0x0098;
        };	 Catch:{ Exception -> 0x02a6 }
    L_0x0098:
        r2 = r16.getRootView();	 Catch:{ Exception -> 0x02a6 }
        r8 = new java.util.ArrayDeque;	 Catch:{ Exception -> 0x02a6 }
        r8.<init>();	 Catch:{ Exception -> 0x02a6 }
        r5 = new com.moat.analytics.mobile.iro.y$b;	 Catch:{ Exception -> 0x02a6 }
        r6 = 0;
        r9 = 1;
        r5.<init>(r2, r6, r9);	 Catch:{ Exception -> 0x02a6 }
        r8.add(r5);	 Catch:{ Exception -> 0x02a6 }
        r2 = "VisibilityInfo";
        r5 = "starting covering rect search";
        r0 = r16;
        com.moat.analytics.mobile.iro.C0756b.m1235(r2, r0, r5);	 Catch:{ Exception -> 0x02a6 }
        r14 = r3;
        r3 = r4;
        r4 = r14;
    L_0x00b7:
        r2 = r8.isEmpty();	 Catch:{ Exception -> 0x02a6 }
        if (r2 != 0) goto L_0x0340;
    L_0x00bd:
        r2 = 40;
    L_0x00bf:
        switch(r2) {
            case 53: goto L_0x00d7;
            default: goto L_0x00c2;
        };
    L_0x00c2:
        r2 = f1316;
        r2 = r2 + 25;
        r5 = r2 % 128;
        f1315 = r5;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x00ce;
    L_0x00ce:
        r2 = 250; // 0xfa float:3.5E-43 double:1.235E-321;
        if (r3 >= r2) goto L_0x0344;
    L_0x00d2:
        r2 = 22;
    L_0x00d4:
        switch(r2) {
            case 22: goto L_0x00da;
            default: goto L_0x00d7;
        };
    L_0x00d7:
        r2 = 0;
        goto L_0x0057;
    L_0x00da:
        r5 = r3 + 1;
        r2 = r8.pollLast();	 Catch:{ Exception -> 0x02a6 }
        r0 = r2;
        r0 = (com.moat.analytics.mobile.iro.C0810y.C0809b) r0;	 Catch:{ Exception -> 0x02a6 }
        r3 = r0;
        r2 = r3.f1312;	 Catch:{ Exception -> 0x02a6 }
        r0 = r16;
        r2 = r2.equals(r0);	 Catch:{ Exception -> 0x02a6 }
        if (r2 == 0) goto L_0x0348;
    L_0x00ee:
        r2 = 64;
    L_0x00f0:
        switch(r2) {
            case 64: goto L_0x0160;
            default: goto L_0x00f3;
        };	 Catch:{ Exception -> 0x02a6 }
    L_0x00f3:
        r2 = r3.f1313;	 Catch:{ Exception -> 0x02a6 }
        if (r2 == 0) goto L_0x034c;
    L_0x00f7:
        r2 = 0;
    L_0x00f8:
        switch(r2) {
            case 0: goto L_0x0178;
            default: goto L_0x00fb;
        };	 Catch:{ Exception -> 0x02a6 }
    L_0x00fb:
        r2 = 0;
        com.moat.analytics.mobile.iro.C0810y.m1415(r8, r3, r2);	 Catch:{ Exception -> 0x02a6 }
        r2 = android.view.ViewGroup.class;
        r6 = r3.f1312;	 Catch:{ Exception -> 0x02a6 }
        r6 = r6.getClass();	 Catch:{ Exception -> 0x02a6 }
        r6 = r6.getSuperclass();	 Catch:{ Exception -> 0x02a6 }
        r2 = r2.equals(r6);	 Catch:{ Exception -> 0x02a6 }
        if (r2 == 0) goto L_0x036f;
    L_0x0111:
        r2 = 1;
    L_0x0112:
        switch(r2) {
            case 0: goto L_0x0256;
            default: goto L_0x0115;
        };
    L_0x0115:
        r2 = f1315;
        r2 = r2 + 17;
        r6 = r2 % 128;
        f1316 = r6;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x03a7;
    L_0x0121:
        r2 = 1;
    L_0x0122:
        switch(r2) {
            case 0: goto L_0x024a;
            default: goto L_0x0125;
        };
    L_0x0125:
        r2 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x02a6 }
        r6 = 19;
        if (r2 < r6) goto L_0x03aa;
    L_0x012b:
        r2 = 42;
    L_0x012d:
        switch(r2) {
            case 73: goto L_0x015d;
            default: goto L_0x0130;
        };	 Catch:{ Exception -> 0x02a6 }
    L_0x0130:
        r2 = r3.f1312;	 Catch:{ Exception -> 0x02a6 }
        r2 = r2.getBackground();	 Catch:{ Exception -> 0x02a6 }
        if (r2 == 0) goto L_0x0375;
    L_0x0138:
        r2 = 1;
    L_0x0139:
        switch(r2) {
            case 0: goto L_0x015d;
            default: goto L_0x013c;
        };
    L_0x013c:
        r2 = f1316;
        r2 = r2 + 71;
        r6 = r2 % 128;
        f1315 = r6;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x03ae;
    L_0x0148:
        r2 = 11;
    L_0x014a:
        switch(r2) {
            case 11: goto L_0x0303;
            default: goto L_0x014d;
        };
    L_0x014d:
        r2 = r3.f1312;	 Catch:{ Exception -> 0x02a6 }
        r2 = r2.getBackground();	 Catch:{ Exception -> 0x02a6 }
        r2 = r2.getAlpha();	 Catch:{ Exception -> 0x02a6 }
        if (r2 == 0) goto L_0x0378;
    L_0x0159:
        r2 = 1;
    L_0x015a:
        switch(r2) {
            case 1: goto L_0x0256;
            default: goto L_0x015d;
        };
    L_0x015d:
        r3 = r5;
        goto L_0x00b7;
    L_0x0160:
        r2 = f1316;
        r2 = r2 + 73;
        r3 = r2 % 128;
        f1315 = r3;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x016c;
    L_0x016c:
        r2 = 1;
        r3 = "VisibilityInfo";
        r4 = "found target";
        com.moat.analytics.mobile.iro.C0756b.m1235(r3, r15, r4);	 Catch:{ Exception -> 0x02a6 }
        r4 = r2;
        r3 = r5;
        goto L_0x00b7;
    L_0x0178:
        r2 = r7.size();	 Catch:{ Exception -> 0x02a6 }
        r6 = r3.f1314;	 Catch:{ Exception -> 0x02a6 }
        if (r2 <= r6) goto L_0x034f;
    L_0x0180:
        r2 = 1;
    L_0x0181:
        switch(r2) {
            case 0: goto L_0x019c;
            default: goto L_0x0184;
        };	 Catch:{ Exception -> 0x02a6 }
    L_0x0184:
        r2 = r3.f1314;	 Catch:{ Exception -> 0x02a6 }
        r2 = r7.get(r2);	 Catch:{ Exception -> 0x02a6 }
        r2 = (android.view.View) r2;	 Catch:{ Exception -> 0x02a6 }
        r6 = r2;
    L_0x018d:
        r2 = r3.f1312;	 Catch:{ Exception -> 0x02a6 }
        if (r2 != r6) goto L_0x0352;
    L_0x0191:
        r2 = 0;
    L_0x0192:
        switch(r2) {
            case 1: goto L_0x019f;
            default: goto L_0x0195;
        };	 Catch:{ Exception -> 0x02a6 }
    L_0x0195:
        r2 = 1;
        com.moat.analytics.mobile.iro.C0810y.m1415(r8, r3, r2);	 Catch:{ Exception -> 0x02a6 }
        r3 = r5;
        goto L_0x00b7;
    L_0x019c:
        r2 = 0;
        r6 = r2;
        goto L_0x018d;
    L_0x019f:
        r9 = r3.f1312;	 Catch:{ Exception -> 0x02a6 }
        r2 = r9.isShown();	 Catch:{ Exception -> 0x02a6 }
        if (r2 == 0) goto L_0x0355;
    L_0x01a7:
        r2 = 16;
    L_0x01a9:
        switch(r2) {
            case 16: goto L_0x02d5;
            default: goto L_0x01ac;
        };
    L_0x01ac:
        r2 = 0;
    L_0x01ad:
        if (r2 == 0) goto L_0x035d;
    L_0x01af:
        r2 = 84;
    L_0x01b1:
        switch(r2) {
            case 84: goto L_0x01b5;
            default: goto L_0x01b4;
        };
    L_0x01b4:
        goto L_0x015d;
    L_0x01b5:
        r2 = f1316;
        r2 = r2 + 43;
        r9 = r2 % 128;
        f1315 = r9;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x01c1;
    L_0x01c1:
        if (r4 == 0) goto L_0x0361;
    L_0x01c3:
        r2 = 82;
    L_0x01c5:
        switch(r2) {
            case 23: goto L_0x0207;
            default: goto L_0x01c8;
        };
    L_0x01c8:
        r2 = f1316;
        r2 = r2 + 9;
        r9 = r2 % 128;
        f1315 = r9;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x039a;
    L_0x01d4:
        r2 = 0;
    L_0x01d5:
        switch(r2) {
            case 0: goto L_0x02f7;
            default: goto L_0x01d8;
        };
    L_0x01d8:
        r2 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x02a6 }
        r9 = 21;
        if (r2 < r9) goto L_0x0365;
    L_0x01de:
        r2 = 73;
    L_0x01e0:
        switch(r2) {
            case 29: goto L_0x00fb;
            default: goto L_0x01e3;
        };	 Catch:{ Exception -> 0x02a6 }
    L_0x01e3:
        r2 = r3.f1312;	 Catch:{ Exception -> 0x02a6 }
        r2 = r2.getElevation();	 Catch:{ Exception -> 0x02a6 }
        r6 = r6.getElevation();	 Catch:{ Exception -> 0x02a6 }
        r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r2 >= 0) goto L_0x00fb;
    L_0x01f1:
        r3 = r5;
        goto L_0x00b7;
    L_0x01f4:
        r2 = r9.getAlpha();	 Catch:{ Exception -> 0x02a6 }
        r10 = (double) r2;	 Catch:{ Exception -> 0x02a6 }
        r12 = 0;
        r2 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1));
        if (r2 <= 0) goto L_0x0359;
    L_0x01ff:
        r2 = 62;
    L_0x0201:
        switch(r2) {
            case 62: goto L_0x0205;
            default: goto L_0x0204;
        };	 Catch:{ Exception -> 0x02a6 }
    L_0x0204:
        goto L_0x01ac;
    L_0x0205:
        r2 = 1;
        goto L_0x01ad;
    L_0x0207:
        r2 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x02a6 }
        r9 = 21;
        if (r2 < r9) goto L_0x0369;
    L_0x020d:
        r2 = 0;
    L_0x020e:
        switch(r2) {
            case 1: goto L_0x015d;
            default: goto L_0x0211;
        };
    L_0x0211:
        r2 = f1315;
        r2 = r2 + 13;
        r9 = r2 % 128;
        f1316 = r9;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x03a0;
    L_0x021d:
        r2 = 26;
    L_0x021f:
        switch(r2) {
            case 92: goto L_0x0236;
            default: goto L_0x0222;
        };
    L_0x0222:
        r2 = r3.f1312;	 Catch:{ Exception -> 0x02a6 }
        r2 = r2.getElevation();	 Catch:{ Exception -> 0x02a6 }
        r6 = r6.getElevation();	 Catch:{ Exception -> 0x02a6 }
        r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r2 <= 0) goto L_0x03a4;
    L_0x0230:
        r2 = 1;
    L_0x0231:
        switch(r2) {
            case 0: goto L_0x015d;
            default: goto L_0x0234;
        };	 Catch:{ Exception -> 0x02a6 }
    L_0x0234:
        goto L_0x00fb;
    L_0x0236:
        r2 = r3.f1312;	 Catch:{ Exception -> 0x02a6 }
        r2 = r2.getElevation();	 Catch:{ Exception -> 0x02a6 }
        r6 = r6.getElevation();	 Catch:{ Exception -> 0x02a6 }
        r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r2 <= 0) goto L_0x036c;
    L_0x0244:
        r2 = 0;
    L_0x0245:
        switch(r2) {
            case 0: goto L_0x00fb;
            default: goto L_0x0248;
        };	 Catch:{ Exception -> 0x02a6 }
    L_0x0248:
        goto L_0x015d;
    L_0x024a:
        r2 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x02a6 }
        r6 = 19;
        if (r2 < r6) goto L_0x0372;
    L_0x0250:
        r2 = 0;
    L_0x0251:
        switch(r2) {
            case 1: goto L_0x015d;
            default: goto L_0x0254;
        };	 Catch:{ Exception -> 0x02a6 }
    L_0x0254:
        goto L_0x0130;
    L_0x0256:
        r2 = r3.f1312;	 Catch:{ Exception -> 0x02a6 }
        r2 = com.moat.analytics.mobile.iro.C0810y.m1417(r2);	 Catch:{ Exception -> 0x02a6 }
        r6 = r2.setIntersect(r15, r2);	 Catch:{ Exception -> 0x02a6 }
        if (r6 == 0) goto L_0x02a0;
    L_0x0262:
        r6 = "VisibilityInfo";
        r9 = r3.f1312;	 Catch:{ Exception -> 0x02a6 }
        r10 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x02a6 }
        r11 = "Covered by ";
        r10.<init>(r11);	 Catch:{ Exception -> 0x02a6 }
        r3 = r3.f1312;	 Catch:{ Exception -> 0x02a6 }
        r3 = r3.getClass();	 Catch:{ Exception -> 0x02a6 }
        r3 = r3.getSimpleName();	 Catch:{ Exception -> 0x02a6 }
        r3 = r10.append(r3);	 Catch:{ Exception -> 0x02a6 }
        r10 = "-";
        r3 = r3.append(r10);	 Catch:{ Exception -> 0x02a6 }
        r10 = r2.toString();	 Catch:{ Exception -> 0x02a6 }
        r3 = r3.append(r10);	 Catch:{ Exception -> 0x02a6 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x02a6 }
        com.moat.analytics.mobile.iro.C0756b.m1235(r6, r9, r3);	 Catch:{ Exception -> 0x02a6 }
        r0 = r17;
        r0.add(r2);	 Catch:{ Exception -> 0x02a6 }
        r2 = r2.contains(r15);	 Catch:{ Exception -> 0x02a6 }
        if (r2 == 0) goto L_0x037b;
    L_0x029b:
        r2 = 40;
    L_0x029d:
        switch(r2) {
            case 40: goto L_0x0316;
            default: goto L_0x02a0;
        };
    L_0x02a0:
        r3 = r5;
        goto L_0x00b7;
    L_0x02a3:
        r2 = 1;
        goto L_0x0057;
    L_0x02a6:
        r2 = move-exception;
        com.moat.analytics.mobile.iro.C0785o.m1351(r2);
        goto L_0x00d7;
    L_0x02ac:
        r2 = f1316;
        r2 = r2 + 9;
        r8 = r2 % 128;
        f1315 = r8;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x037f;
    L_0x02b8:
        r2 = 0;
    L_0x02b9:
        switch(r2) {
            case 1: goto L_0x0058;
            default: goto L_0x02bc;
        };
    L_0x02bc:
        r2 = r16.getRootView();	 Catch:{ Exception -> 0x02a6 }
        if (r7 != r2) goto L_0x0382;
    L_0x02c2:
        r2 = 1;
    L_0x02c3:
        switch(r2) {
            case 1: goto L_0x0016;
            default: goto L_0x02c6;
        };	 Catch:{ Exception -> 0x02a6 }
    L_0x02c6:
        goto L_0x0062;
    L_0x02c8:
        r2 = 0;
        r7 = r2;
        goto L_0x0034;
    L_0x02cc:
        r2 = r7.getParent();	 Catch:{ Exception -> 0x02a6 }
        r2 = (android.view.View) r2;	 Catch:{ Exception -> 0x02a6 }
        r7 = r2;
        goto L_0x000b;
    L_0x02d5:
        r2 = f1316;
        r2 = r2 + 121;
        r10 = r2 % 128;
        f1315 = r10;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x0393;
    L_0x02e1:
        r2 = 18;
    L_0x02e3:
        switch(r2) {
            case 34: goto L_0x01f4;
            default: goto L_0x02e6;
        };
    L_0x02e6:
        r2 = r9.getAlpha();	 Catch:{ Exception -> 0x02a6 }
        r10 = (double) r2;	 Catch:{ Exception -> 0x02a6 }
        r12 = 0;
        r2 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1));
        if (r2 <= 0) goto L_0x0397;
    L_0x02f1:
        r2 = 0;
    L_0x02f2:
        switch(r2) {
            case 0: goto L_0x0205;
            default: goto L_0x02f5;
        };	 Catch:{ Exception -> 0x02a6 }
    L_0x02f5:
        goto L_0x01ac;
    L_0x02f7:
        r2 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x02a6 }
        r9 = 21;
        if (r2 < r9) goto L_0x039d;
    L_0x02fd:
        r2 = 1;
    L_0x02fe:
        switch(r2) {
            case 0: goto L_0x00fb;
            default: goto L_0x0301;
        };	 Catch:{ Exception -> 0x02a6 }
    L_0x0301:
        goto L_0x01e3;
    L_0x0303:
        r2 = r3.f1312;	 Catch:{ Exception -> 0x02a6 }
        r2 = r2.getBackground();	 Catch:{ Exception -> 0x02a6 }
        r2 = r2.getAlpha();	 Catch:{ Exception -> 0x02a6 }
        if (r2 == 0) goto L_0x03b2;
    L_0x030f:
        r2 = 37;
    L_0x0311:
        switch(r2) {
            case 37: goto L_0x0256;
            default: goto L_0x0314;
        };
    L_0x0314:
        goto L_0x015d;
    L_0x0316:
        r2 = f1316;
        r2 = r2 + 85;
        r3 = r2 % 128;
        f1315 = r3;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x03b6;
    L_0x0322:
        r2 = 36;
    L_0x0324:
        switch(r2) {
            case 69: goto L_0x02a3;
            default: goto L_0x0327;
        };
    L_0x0327:
        r2 = 1;
        goto L_0x0057;
    L_0x032a:
        r2 = 30;
        goto L_0x0013;
    L_0x032e:
        r2 = 0;
        goto L_0x005f;
    L_0x0331:
        r2 = 93;
        goto L_0x001e;
    L_0x0335:
        r2 = 13;
        goto L_0x0072;
    L_0x0339:
        r2 = 77;
        goto L_0x0038;
    L_0x033d:
        r2 = 1;
        goto L_0x0095;
    L_0x0340:
        r2 = 53;
        goto L_0x00bf;
    L_0x0344:
        r2 = 50;
        goto L_0x00d4;
    L_0x0348:
        r2 = 65;
        goto L_0x00f0;
    L_0x034c:
        r2 = 1;
        goto L_0x00f8;
    L_0x034f:
        r2 = 0;
        goto L_0x0181;
    L_0x0352:
        r2 = 1;
        goto L_0x0192;
    L_0x0355:
        r2 = 47;
        goto L_0x01a9;
    L_0x0359:
        r2 = 68;
        goto L_0x0201;
    L_0x035d:
        r2 = 36;
        goto L_0x01b1;
    L_0x0361:
        r2 = 23;
        goto L_0x01c5;
    L_0x0365:
        r2 = 29;
        goto L_0x01e0;
    L_0x0369:
        r2 = 1;
        goto L_0x020e;
    L_0x036c:
        r2 = 1;
        goto L_0x0245;
    L_0x036f:
        r2 = 0;
        goto L_0x0112;
    L_0x0372:
        r2 = 1;
        goto L_0x0251;
    L_0x0375:
        r2 = 0;
        goto L_0x0139;
    L_0x0378:
        r2 = 0;
        goto L_0x015a;
    L_0x037b:
        r2 = 68;
        goto L_0x029d;
    L_0x037f:
        r2 = 1;
        goto L_0x02b9;
    L_0x0382:
        r2 = 0;
        goto L_0x02c3;
    L_0x0385:
        r2 = 37;
        goto L_0x002f;
    L_0x0389:
        r2 = 1;
        goto L_0x0082;
    L_0x038c:
        r2 = 37;
        goto L_0x0049;
    L_0x0390:
        r2 = 0;
        goto L_0x0053;
    L_0x0393:
        r2 = 34;
        goto L_0x02e3;
    L_0x0397:
        r2 = 1;
        goto L_0x02f2;
    L_0x039a:
        r2 = 1;
        goto L_0x01d5;
    L_0x039d:
        r2 = 0;
        goto L_0x02fe;
    L_0x03a0:
        r2 = 92;
        goto L_0x021f;
    L_0x03a4:
        r2 = 0;
        goto L_0x0231;
    L_0x03a7:
        r2 = 0;
        goto L_0x0122;
    L_0x03aa:
        r2 = 73;
        goto L_0x012d;
    L_0x03ae:
        r2 = 37;
        goto L_0x014a;
    L_0x03b2:
        r2 = 89;
        goto L_0x0311;
    L_0x03b6:
        r2 = 69;
        goto L_0x0324;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.iro.y.ˏ(android.graphics.Rect, android.view.View, java.util.HashSet):boolean");
    }

    @VisibleForTesting
    /* renamed from: ˊ */
    private static int m1412(Rect rect, Set<Rect> set) {
        if (set.isEmpty()) {
            return 0;
        }
        List arrayList = new ArrayList();
        arrayList.addAll(set);
        Collections.sort(arrayList, new C08072());
        List arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (true) {
            int i;
            Rect rect2;
            switch (it.hasNext() ? 1 : null) {
                case 1:
                    i = f1315 + 101;
                    f1316 = i % 128;
                    if (i % 2 == 0) {
                        rect2 = (Rect) it.next();
                        arrayList2.add(Integer.valueOf(rect2.left));
                        arrayList2.add(Integer.valueOf(rect2.right));
                    } else {
                        rect2 = (Rect) it.next();
                        arrayList2.add(Integer.valueOf(rect2.left));
                        arrayList2.add(Integer.valueOf(rect2.right));
                    }
                default:
                    Collections.sort(arrayList2);
                    int i2 = 0;
                    int i3 = 0;
                    while (true) {
                        Object obj;
                        if (i2 < arrayList2.size() - 1) {
                            obj = null;
                        } else {
                            i = 1;
                        }
                        switch (obj) {
                            case null:
                                i = f1316 + 99;
                                f1315 = i % 128;
                                if (i % 2 != 0) {
                                }
                                switch (!((Integer) arrayList2.get(i2)).equals(arrayList2.get(i2 + 1)) ? 97 : 49) {
                                    case 49:
                                        break;
                                    default:
                                        Rect rect3 = new Rect(((Integer) arrayList2.get(i2)).intValue(), rect.top, ((Integer) arrayList2.get(i2 + 1)).intValue(), rect.bottom);
                                        i = rect.top;
                                        Iterator it2 = arrayList.iterator();
                                        int i4 = i3;
                                        i3 = i;
                                        while (true) {
                                            switch (it2.hasNext() ? 94 : 17) {
                                                case 17:
                                                    i3 = i4;
                                                    break;
                                                default:
                                                    rect2 = (Rect) it2.next();
                                                    switch (Rect.intersects(rect2, rect3) ? 1 : null) {
                                                        case 1:
                                                            switch (rect2.bottom > i3 ? 3 : 47) {
                                                                case 3:
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
                                }
                                i2++;
                            default:
                                return i3;
                        }
                    }
            }
        }
    }

    /* renamed from: ॱ */
    private static Map<String, String> m1418(Rect rect) {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("x", String.valueOf(rect.left));
        hashMap.put("y", String.valueOf(rect.top));
        hashMap.put("w", String.valueOf(rect.right - rect.left));
        hashMap.put("h", String.valueOf(rect.bottom - rect.top));
        return hashMap;
    }

    /* renamed from: ˎ */
    private static Rect m1413(Rect rect, DisplayMetrics displayMetrics) {
        float f = displayMetrics.density;
        switch (f == 0.0f ? null : 1) {
            case 1:
                return new Rect(Math.round(((float) rect.left) / f), Math.round(((float) rect.top) / f), Math.round(((float) rect.right) / f), Math.round(((float) rect.bottom) / f));
            default:
                return rect;
        }
    }

    /* renamed from: ॱ */
    private static Rect m1417(View view) {
        int[] iArr = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
        view.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return new Rect(i, i2, view.getWidth() + i, view.getHeight() + i2);
    }
}
