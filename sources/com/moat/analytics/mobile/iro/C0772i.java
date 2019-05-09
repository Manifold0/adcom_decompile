package com.moat.analytics.mobile.iro;

import android.os.Build.VERSION;
import com.moat.analytics.mobile.iro.C0803t.C0800c;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.iro.i */
final class C0772i {
    /* renamed from: ʻ */
    private static char[] f1209 = new char[]{'s', 'a', '2', 'b', 'c', '3', '4', '1', '8', '9', 'f', '0', '6', 'd', 'e', '5', 'o', 'n', 'i', 'p', 'l', 't', 'u', 'v', 'w'};
    /* renamed from: ʽ */
    private static char f1210 = '\u0005';
    /* renamed from: ˊॱ */
    private static int f1211 = 1;
    /* renamed from: ᐝ */
    private static int f1212 = 0;
    /* renamed from: ˊ */
    private boolean f1213 = false;
    /* renamed from: ˋ */
    private int f1214 = 10;
    /* renamed from: ˎ */
    private int f1215 = 200;
    /* renamed from: ˏ */
    private boolean f1216 = false;
    /* renamed from: ॱ */
    private boolean f1217 = false;

    C0772i(String str) {
        m1308(str);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: ˊ */
    private void m1308(java.lang.String r10) {
        /*
        r9 = this;
        r1 = 0;
        r0 = 1;
        if (r10 != 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r3 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0117 }
        r3.<init>(r10);	 Catch:{ Exception -> 0x0117 }
        r2 = 2;
        r4 = "\u0001\u0002";
        r5 = 59;
        r2 = com.moat.analytics.mobile.iro.C0772i.m1310(r2, r4, r5);	 Catch:{ Exception -> 0x0117 }
        r2 = r2.intern();	 Catch:{ Exception -> 0x0117 }
        r2 = r3.getString(r2);	 Catch:{ Exception -> 0x0117 }
        r4 = 40;
        r5 = "\u0003\u0004\u0000\t\u0007\b\r\b\u0005\u0006\u000b\f\f\u0011\u0007\r\u000e\u0000\b\u0012\t\f\u0004\r\u0004\u0000\u0001\u000e\u0007\u000b\u0005\u0007\n\u0011\b\t\u0007\f\u0004\u0003";
        r6 = 66;
        r4 = com.moat.analytics.mobile.iro.C0772i.m1310(r4, r5, r6);	 Catch:{ Exception -> 0x0117 }
        r4 = r4.intern();	 Catch:{ Exception -> 0x0117 }
        r4 = r2.equals(r4);	 Catch:{ Exception -> 0x0117 }
        r5 = 40;
        r6 = "\u0005\r\b\f\r\u0006\u0002\u0003\u000e\r\u0007\u0005\u0011\f\u0005\b\u0000\u0010\r\u0004\u0004\u000b\u0005\r\n\u0010\u0006\u000e\u000f\u0014\b\u0006\f\u0004\u0011\f\u0005\u0007\t\u0005";
        r7 = 43;
        r5 = com.moat.analytics.mobile.iro.C0772i.m1310(r5, r6, r7);	 Catch:{ Exception -> 0x0117 }
        r5 = r5.intern();	 Catch:{ Exception -> 0x0117 }
        r5 = r2.equals(r5);	 Catch:{ Exception -> 0x0117 }
        r6 = 2;
        r7 = "\u0011\u0012";
        r8 = 121; // 0x79 float:1.7E-43 double:6.0E-322;
        r6 = com.moat.analytics.mobile.iro.C0772i.m1310(r6, r7, r8);	 Catch:{ Exception -> 0x0117 }
        r6 = r6.intern();	 Catch:{ Exception -> 0x0117 }
        r2 = r2.equals(r6);	 Catch:{ Exception -> 0x0117 }
        if (r2 != 0) goto L_0x0174;
    L_0x0052:
        r2 = r1;
    L_0x0053:
        switch(r2) {
            case 0: goto L_0x0125;
            default: goto L_0x0056;
        };	 Catch:{ Exception -> 0x0117 }
    L_0x0056:
        r2 = com.moat.analytics.mobile.iro.C0772i.m1309(r3);	 Catch:{ Exception -> 0x0117 }
        if (r2 != 0) goto L_0x017c;
    L_0x005c:
        r2 = r0;
    L_0x005d:
        switch(r2) {
            case 0: goto L_0x0089;
            default: goto L_0x0060;
        };	 Catch:{ Exception -> 0x0117 }
    L_0x0060:
        r2 = com.moat.analytics.mobile.iro.C0772i.m1312(r3);	 Catch:{ Exception -> 0x0117 }
        if (r2 != 0) goto L_0x017f;
    L_0x0066:
        r2 = 48;
    L_0x0068:
        switch(r2) {
            case 74: goto L_0x0089;
            default: goto L_0x006b;
        };	 Catch:{ Exception -> 0x0117 }
    L_0x006b:
        r2 = 1;
        r9.f1213 = r2;	 Catch:{ Exception -> 0x0117 }
        r9.f1216 = r4;	 Catch:{ Exception -> 0x0117 }
        r9.f1217 = r5;	 Catch:{ Exception -> 0x0117 }
        r2 = r9.f1217;	 Catch:{ Exception -> 0x0117 }
        if (r2 == 0) goto L_0x0183;
    L_0x0076:
        r2 = r1;
    L_0x0077:
        switch(r2) {
            case 1: goto L_0x0089;
            default: goto L_0x007a;
        };
    L_0x007a:
        r2 = f1211;
        r2 = r2 + 51;
        r4 = r2 % 128;
        f1212 = r4;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x0086;
    L_0x0086:
        r2 = 1;
        r9.f1216 = r2;	 Catch:{ Exception -> 0x0117 }
    L_0x0089:
        r2 = 2;
        r4 = "\u0013\u0012";
        r5 = 25;
        r2 = com.moat.analytics.mobile.iro.C0772i.m1310(r2, r4, r5);	 Catch:{ Exception -> 0x0117 }
        r2 = r2.intern();	 Catch:{ Exception -> 0x0117 }
        r2 = r3.has(r2);	 Catch:{ Exception -> 0x0117 }
        if (r2 == 0) goto L_0x0186;
    L_0x009c:
        r2 = r0;
    L_0x009d:
        switch(r2) {
            case 0: goto L_0x00ba;
            default: goto L_0x00a0;
        };	 Catch:{ Exception -> 0x0117 }
    L_0x00a0:
        r2 = 2;
        r4 = "\u0013\u0012";
        r5 = 25;
        r2 = com.moat.analytics.mobile.iro.C0772i.m1310(r2, r4, r5);	 Catch:{ Exception -> 0x0117 }
        r2 = r2.intern();	 Catch:{ Exception -> 0x0117 }
        r4 = r3.getInt(r2);	 Catch:{ Exception -> 0x0117 }
        r2 = 100;
        if (r4 < r2) goto L_0x0189;
    L_0x00b5:
        r2 = 56;
    L_0x00b7:
        switch(r2) {
            case 56: goto L_0x0142;
            default: goto L_0x00ba;
        };	 Catch:{ Exception -> 0x0117 }
    L_0x00ba:
        r2 = 2;
        r4 = "\n\u0004";
        r5 = 24;
        r2 = com.moat.analytics.mobile.iro.C0772i.m1310(r2, r4, r5);	 Catch:{ Exception -> 0x0117 }
        r2 = r2.intern();	 Catch:{ Exception -> 0x0117 }
        r2 = r3.has(r2);	 Catch:{ Exception -> 0x0117 }
        if (r2 == 0) goto L_0x0190;
    L_0x00cd:
        r2 = r1;
    L_0x00ce:
        switch(r2) {
            case 1: goto L_0x00f0;
            default: goto L_0x00d1;
        };
    L_0x00d1:
        r2 = f1212;
        r2 = r2 + 55;
        r4 = r2 % 128;
        f1211 = r4;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x00dd;
    L_0x00dd:
        r2 = 2;
        r4 = "\n\u0004";
        r5 = 24;
        r2 = com.moat.analytics.mobile.iro.C0772i.m1310(r2, r4, r5);	 Catch:{ Exception -> 0x0117 }
        r2 = r2.intern();	 Catch:{ Exception -> 0x0117 }
        r2 = r3.getInt(r2);	 Catch:{ Exception -> 0x0117 }
        r9.f1214 = r2;	 Catch:{ Exception -> 0x0117 }
    L_0x00f0:
        r2 = com.moat.analytics.mobile.iro.C0772i.m1311(r3);	 Catch:{ Exception -> 0x0117 }
        if (r2 == 0) goto L_0x0193;
    L_0x00f6:
        r2 = 71;
    L_0x00f8:
        switch(r2) {
            case 71: goto L_0x00fd;
            default: goto L_0x00fb;
        };
    L_0x00fb:
        goto L_0x0004;
    L_0x00fd:
        r2 = f1212;
        r2 = r2 + 69;
        r3 = r2 % 128;
        f1211 = r3;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x0196;
    L_0x0109:
        switch(r0) {
            case 1: goto L_0x0169;
            default: goto L_0x010c;
        };
    L_0x010c:
        r0 = com.moat.analytics.mobile.iro.MoatAnalytics.getInstance();	 Catch:{ Exception -> 0x0117 }
        r0 = (com.moat.analytics.mobile.iro.C0774j) r0;	 Catch:{ Exception -> 0x0117 }
        r2 = 1;
        r0.f1223 = r2;	 Catch:{ Exception -> 0x0117 }
        goto L_0x0004;
    L_0x0117:
        r0 = move-exception;
        r9.f1213 = r1;
        r9.f1216 = r1;
        r1 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r9.f1215 = r1;
        com.moat.analytics.mobile.iro.C0785o.m1351(r0);
        goto L_0x0004;
    L_0x0125:
        r2 = f1212;
        r2 = r2 + 91;
        r6 = r2 % 128;
        f1211 = r6;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x0131;
    L_0x0131:
        if (r4 != 0) goto L_0x0177;
    L_0x0133:
        r2 = r0;
    L_0x0134:
        switch(r2) {
            case 1: goto L_0x0139;
            default: goto L_0x0137;
        };
    L_0x0137:
        goto L_0x0056;
    L_0x0139:
        if (r5 == 0) goto L_0x0179;
    L_0x013b:
        r2 = 34;
    L_0x013d:
        switch(r2) {
            case 20: goto L_0x0089;
            default: goto L_0x0140;
        };
    L_0x0140:
        goto L_0x0056;
    L_0x0142:
        r2 = f1212;
        r2 = r2 + 117;
        r5 = r2 % 128;
        f1211 = r5;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x014e;
    L_0x014e:
        r2 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        if (r4 > r2) goto L_0x018d;
    L_0x0152:
        r2 = 75;
    L_0x0154:
        switch(r2) {
            case 75: goto L_0x0159;
            default: goto L_0x0157;
        };
    L_0x0157:
        goto L_0x00ba;
    L_0x0159:
        r2 = f1211;
        r2 = r2 + 59;
        r5 = r2 % 128;
        f1212 = r5;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x0165;
    L_0x0165:
        r9.f1215 = r4;	 Catch:{ Exception -> 0x0117 }
        goto L_0x00ba;
    L_0x0169:
        r0 = com.moat.analytics.mobile.iro.MoatAnalytics.getInstance();	 Catch:{ Exception -> 0x0117 }
        r0 = (com.moat.analytics.mobile.iro.C0774j) r0;	 Catch:{ Exception -> 0x0117 }
        r2 = 1;
        r0.f1223 = r2;	 Catch:{ Exception -> 0x0117 }
        goto L_0x0004;
    L_0x0174:
        r2 = r0;
        goto L_0x0053;
    L_0x0177:
        r2 = r1;
        goto L_0x0134;
    L_0x0179:
        r2 = 20;
        goto L_0x013d;
    L_0x017c:
        r2 = r1;
        goto L_0x005d;
    L_0x017f:
        r2 = 74;
        goto L_0x0068;
    L_0x0183:
        r2 = r0;
        goto L_0x0077;
    L_0x0186:
        r2 = r1;
        goto L_0x009d;
    L_0x0189:
        r2 = 66;
        goto L_0x00b7;
    L_0x018d:
        r2 = 52;
        goto L_0x0154;
    L_0x0190:
        r2 = r0;
        goto L_0x00ce;
    L_0x0193:
        r2 = r0;
        goto L_0x00f8;
    L_0x0196:
        r0 = r1;
        goto L_0x0109;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.iro.i.ˊ(java.lang.String):void");
    }

    /* renamed from: ˊ */
    private static boolean m1309(JSONObject jSONObject) {
        try {
            boolean z;
            if (16 > VERSION.SDK_INT) {
                z = true;
            } else {
                z = false;
            }
            switch (z) {
                case false:
                    Object obj;
                    if (jSONObject.has(C0772i.m1310(2, "\u0012\u0001", (byte) 62).intern())) {
                        obj = 77;
                    } else {
                        obj = 86;
                    }
                    switch (obj) {
                        case 86:
                            break;
                        default:
                            JSONArray jSONArray = jSONObject.getJSONArray(C0772i.m1310(2, "\u0012\u0001", (byte) 62).intern());
                            int length = jSONArray.length();
                            int i = 0;
                            while (true) {
                                if (i < length) {
                                    obj = 60;
                                } else {
                                    obj = 71;
                                }
                                switch (obj) {
                                    case 60:
                                        if (jSONArray.getInt(i) == VERSION.SDK_INT) {
                                            obj = 14;
                                        } else {
                                            obj = 26;
                                        }
                                        switch (obj) {
                                            case 14:
                                                return true;
                                            default:
                                                i++;
                                        }
                                    default:
                                        break;
                                }
                            }
                    }
                    return false;
                default:
                    Object obj2;
                    int i2 = f1212 + 69;
                    f1211 = i2 % 128;
                    if (i2 % 2 == 0) {
                        obj2 = 63;
                    } else {
                        obj2 = 39;
                    }
                    switch (obj2) {
                    }
                    return true;
            }
        } catch (Exception e) {
            return true;
        }
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: ˏ */
    private static boolean m1312(org.json.JSONObject r8) {
        /*
        r0 = 1;
        r1 = 0;
        r2 = 2;
        r3 = "\u0004\u0010";
        r4 = 47;
        r2 = com.moat.analytics.mobile.iro.C0772i.m1310(r2, r3, r4);	 Catch:{ Exception -> 0x006c }
        r2 = r2.intern();	 Catch:{ Exception -> 0x006c }
        r2 = r8.has(r2);	 Catch:{ Exception -> 0x006c }
        if (r2 == 0) goto L_0x0071;
    L_0x0015:
        r2 = r0;
    L_0x0016:
        switch(r2) {
            case 1: goto L_0x001b;
            default: goto L_0x0019;
        };	 Catch:{ Exception -> 0x006c }
    L_0x0019:
        r0 = r1;
    L_0x001a:
        return r0;
    L_0x001b:
        r2 = com.moat.analytics.mobile.iro.C0789p.m1362();	 Catch:{ Exception -> 0x006c }
        r4 = r2.m1353();	 Catch:{ Exception -> 0x006c }
        r2 = 2;
        r3 = "\u0004\u0010";
        r5 = 47;
        r2 = com.moat.analytics.mobile.iro.C0772i.m1310(r2, r3, r5);	 Catch:{ Exception -> 0x006c }
        r2 = r2.intern();	 Catch:{ Exception -> 0x006c }
        r5 = r8.getJSONArray(r2);	 Catch:{ Exception -> 0x006c }
        r6 = r5.length();	 Catch:{ Exception -> 0x006c }
        r3 = r1;
    L_0x0039:
        if (r3 >= r6) goto L_0x0073;
    L_0x003b:
        r2 = 34;
    L_0x003d:
        switch(r2) {
            case 34: goto L_0x0041;
            default: goto L_0x0040;
        };
    L_0x0040:
        goto L_0x0019;
    L_0x0041:
        r2 = f1211;
        r2 = r2 + 115;
        r7 = r2 % 128;
        f1212 = r7;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x004d;
    L_0x004d:
        r2 = r5.getString(r3);	 Catch:{ Exception -> 0x006c }
        r2 = r2.contentEquals(r4);	 Catch:{ Exception -> 0x006c }
        if (r2 == 0) goto L_0x0076;
    L_0x0057:
        r2 = r0;
    L_0x0058:
        switch(r2) {
            case 0: goto L_0x0068;
            default: goto L_0x005b;
        };
    L_0x005b:
        r1 = f1211;
        r1 = r1 + 47;
        r2 = r1 % 128;
        f1212 = r2;
        r1 = r1 % 2;
        if (r1 == 0) goto L_0x001a;
    L_0x0067:
        goto L_0x001a;
    L_0x0068:
        r2 = r3 + 1;
        r3 = r2;
        goto L_0x0039;
    L_0x006c:
        r0 = move-exception;
        com.moat.analytics.mobile.iro.C0785o.m1351(r0);
        goto L_0x0019;
    L_0x0071:
        r2 = r1;
        goto L_0x0016;
    L_0x0073:
        r2 = 56;
        goto L_0x003d;
    L_0x0076:
        r2 = r1;
        goto L_0x0058;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.iro.i.ˏ(org.json.JSONObject):boolean");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: ˎ */
    private static boolean m1311(org.json.JSONObject r7) {
        /*
        r0 = 1;
        r1 = 0;
        r2 = 2;
        r3 = "\u0000\u0015";
        r4 = 3;
        r2 = com.moat.analytics.mobile.iro.C0772i.m1310(r2, r3, r4);	 Catch:{ Exception -> 0x005c }
        r2 = r2.intern();	 Catch:{ Exception -> 0x005c }
        r2 = r7.has(r2);	 Catch:{ Exception -> 0x005c }
        if (r2 == 0) goto L_0x0061;
    L_0x0014:
        r2 = 23;
    L_0x0016:
        switch(r2) {
            case 23: goto L_0x001b;
            default: goto L_0x0019;
        };
    L_0x0019:
        r0 = r1;
    L_0x001a:
        return r0;
    L_0x001b:
        r2 = f1212;
        r2 = r2 + 73;
        r3 = r2 % 128;
        f1211 = r3;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x0027;
    L_0x0027:
        r2 = com.moat.analytics.mobile.iro.C0789p.m1362();	 Catch:{ Exception -> 0x005c }
        r4 = r2.m1353();	 Catch:{ Exception -> 0x005c }
        r2 = 2;
        r3 = "\u0000\u0015";
        r5 = 3;
        r2 = com.moat.analytics.mobile.iro.C0772i.m1310(r2, r3, r5);	 Catch:{ Exception -> 0x005c }
        r2 = r2.intern();	 Catch:{ Exception -> 0x005c }
        r5 = r7.getJSONArray(r2);	 Catch:{ Exception -> 0x005c }
        r6 = r5.length();	 Catch:{ Exception -> 0x005c }
        r3 = r1;
    L_0x0044:
        if (r3 >= r6) goto L_0x0064;
    L_0x0046:
        r2 = r1;
    L_0x0047:
        switch(r2) {
            case 1: goto L_0x0019;
            default: goto L_0x004a;
        };	 Catch:{ Exception -> 0x005c }
    L_0x004a:
        r2 = r5.getString(r3);	 Catch:{ Exception -> 0x005c }
        r2 = r2.contentEquals(r4);	 Catch:{ Exception -> 0x005c }
        if (r2 == 0) goto L_0x0066;
    L_0x0054:
        r2 = r0;
    L_0x0055:
        switch(r2) {
            case 1: goto L_0x001a;
            default: goto L_0x0058;
        };
    L_0x0058:
        r2 = r3 + 1;
        r3 = r2;
        goto L_0x0044;
    L_0x005c:
        r0 = move-exception;
        com.moat.analytics.mobile.iro.C0785o.m1351(r0);
        goto L_0x0019;
    L_0x0061:
        r2 = 55;
        goto L_0x0016;
    L_0x0064:
        r2 = r0;
        goto L_0x0047;
    L_0x0066:
        r2 = r1;
        goto L_0x0055;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.iro.i.ˎ(org.json.JSONObject):boolean");
    }

    /* renamed from: ˊ */
    final boolean m1313() {
        return this.f1216;
    }

    /* renamed from: ˎ */
    final boolean m1315() {
        return this.f1217;
    }

    /* renamed from: ˋ */
    final int m1314() {
        return this.f1215;
    }

    /* renamed from: ॱ */
    final int m1317() {
        return this.f1214;
    }

    /* renamed from: ˏ */
    final int m1316() {
        return this.f1213 ? C0800c.f1286 : C0800c.f1285;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: ˎ */
    private static java.lang.String m1310(int r12, java.lang.String r13, byte r14) {
        /*
        r3 = 1;
        r2 = 0;
        r0 = r13.toCharArray();
        r0 = (char[]) r0;
        r5 = f1209;
        r6 = f1210;
        r7 = new char[r12];
        r1 = r12 % 2;
        if (r1 == 0) goto L_0x00d5;
    L_0x0012:
        r1 = r2;
    L_0x0013:
        switch(r1) {
            case 1: goto L_0x002a;
            default: goto L_0x0016;
        };
    L_0x0016:
        r1 = f1211;
        r1 = r1 + 99;
        r4 = r1 % 128;
        f1212 = r4;
        r1 = r1 % 2;
        if (r1 == 0) goto L_0x0022;
    L_0x0022:
        r12 = r12 + -1;
        r1 = r0[r12];
        r1 = r1 - r14;
        r1 = (char) r1;
        r7[r12] = r1;
    L_0x002a:
        if (r12 <= r3) goto L_0x00d8;
    L_0x002c:
        r1 = r2;
    L_0x002d:
        switch(r1) {
            case 0: goto L_0x00c1;
            default: goto L_0x0030;
        };
    L_0x0030:
        r0 = new java.lang.String;
        r0.<init>(r7);
        return r0;
    L_0x0036:
        r4 = r2;
    L_0x0037:
        if (r4 >= r12) goto L_0x00db;
    L_0x0039:
        r1 = 99;
    L_0x003b:
        switch(r1) {
            case 85: goto L_0x0030;
            default: goto L_0x003e;
        };
    L_0x003e:
        r8 = r0[r4];
        r1 = r4 + 1;
        r9 = r0[r1];
        if (r8 != r9) goto L_0x00df;
    L_0x0046:
        r1 = 24;
    L_0x0048:
        switch(r1) {
            case 35: goto L_0x005b;
            default: goto L_0x004b;
        };
    L_0x004b:
        r1 = r8 - r14;
        r1 = (char) r1;
        r7[r4] = r1;
        r1 = r4 + 1;
        r8 = r9 - r14;
        r8 = (char) r8;
        r7[r1] = r8;
    L_0x0057:
        r1 = r4 + 2;
        r4 = r1;
        goto L_0x0037;
    L_0x005b:
        r10 = com.p011c.p012c.C0670b.m1205(r8, r6);
        r8 = com.p011c.p012c.C0670b.m1206(r8, r6);
        r11 = com.p011c.p012c.C0670b.m1205(r9, r6);
        r9 = com.p011c.p012c.C0670b.m1206(r9, r6);
        if (r8 != r9) goto L_0x00e3;
    L_0x006d:
        r1 = r2;
    L_0x006e:
        switch(r1) {
            case 1: goto L_0x008c;
            default: goto L_0x0071;
        };
    L_0x0071:
        r1 = com.p011c.p012c.C0670b.m1204(r10, r6);
        r10 = com.p011c.p012c.C0670b.m1204(r11, r6);
        r1 = com.p011c.p012c.C0670b.m1207(r1, r8, r6);
        r8 = com.p011c.p012c.C0670b.m1207(r10, r9, r6);
        r1 = r5[r1];
        r7[r4] = r1;
        r1 = r4 + 1;
        r8 = r5[r8];
        r7[r1] = r8;
        goto L_0x0057;
    L_0x008c:
        if (r10 != r11) goto L_0x00e5;
    L_0x008e:
        r1 = 46;
    L_0x0090:
        switch(r1) {
            case 49: goto L_0x00ae;
            default: goto L_0x0093;
        };
    L_0x0093:
        r1 = com.p011c.p012c.C0670b.m1204(r8, r6);
        r8 = com.p011c.p012c.C0670b.m1204(r9, r6);
        r1 = com.p011c.p012c.C0670b.m1207(r10, r1, r6);
        r8 = com.p011c.p012c.C0670b.m1207(r11, r8, r6);
        r1 = r5[r1];
        r7[r4] = r1;
        r1 = r4 + 1;
        r8 = r5[r8];
        r7[r1] = r8;
        goto L_0x0057;
    L_0x00ae:
        r1 = com.p011c.p012c.C0670b.m1207(r10, r9, r6);
        r8 = com.p011c.p012c.C0670b.m1207(r11, r8, r6);
        r1 = r5[r1];
        r7[r4] = r1;
        r1 = r4 + 1;
        r8 = r5[r8];
        r7[r1] = r8;
        goto L_0x0057;
    L_0x00c1:
        r1 = f1211;
        r1 = r1 + 125;
        r4 = r1 % 128;
        f1212 = r4;
        r1 = r1 % 2;
        if (r1 == 0) goto L_0x00e8;
    L_0x00cd:
        r1 = 82;
    L_0x00cf:
        switch(r1) {
            case 34: goto L_0x0036;
            default: goto L_0x00d2;
        };
    L_0x00d2:
        r4 = r2;
        goto L_0x0037;
    L_0x00d5:
        r1 = r3;
        goto L_0x0013;
    L_0x00d8:
        r1 = r3;
        goto L_0x002d;
    L_0x00db:
        r1 = 85;
        goto L_0x003b;
    L_0x00df:
        r1 = 35;
        goto L_0x0048;
    L_0x00e3:
        r1 = r3;
        goto L_0x006e;
    L_0x00e5:
        r1 = 49;
        goto L_0x0090;
    L_0x00e8:
        r1 = 34;
        goto L_0x00cf;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.iro.i.ˎ(int, java.lang.String, byte):java.lang.String");
    }
}
