package com.facebook.ads.internal.p043m;

import com.facebook.ads.internal.protocol.AdPlacementType;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.m.d */
public class C2047d {
    /* renamed from: c */
    private static final String f4577c = C2047d.class.getSimpleName();
    /* renamed from: d */
    private static final AdPlacementType f4578d = AdPlacementType.UNKNOWN;
    /* renamed from: a */
    public int f4579a = -1;
    /* renamed from: b */
    public int f4580b = -1;
    /* renamed from: e */
    private final long f4581e = System.currentTimeMillis();
    /* renamed from: f */
    private AdPlacementType f4582f = f4578d;
    /* renamed from: g */
    private int f4583g = 1;
    /* renamed from: h */
    private int f4584h = 0;
    /* renamed from: i */
    private int f4585i = 0;
    /* renamed from: j */
    private int f4586j = 20;
    /* renamed from: k */
    private int f4587k = 0;
    /* renamed from: l */
    private int f4588l = 1000;
    /* renamed from: m */
    private int f4589m = 10000;
    /* renamed from: n */
    private int f4590n = 200;
    /* renamed from: o */
    private int f4591o = 3600;
    /* renamed from: p */
    private boolean f4592p = false;
    /* renamed from: q */
    private List<C2045b> f4593q = null;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private C2047d(java.util.Map<java.lang.String, java.lang.String> r12) {
        /*
        r11 = this;
        r10 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r4 = 1;
        r3 = -1;
        r2 = 0;
        r11.<init>();
        r11.f4579a = r3;
        r11.f4580b = r3;
        r0 = f4578d;
        r11.f4582f = r0;
        r11.f4583g = r4;
        r11.f4584h = r2;
        r11.f4585i = r2;
        r0 = 20;
        r11.f4586j = r0;
        r11.f4587k = r2;
        r0 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r11.f4588l = r0;
        r0 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r11.f4589m = r0;
        r11.f4590n = r10;
        r0 = 3600; // 0xe10 float:5.045E-42 double:1.7786E-320;
        r11.f4591o = r0;
        r11.f4592p = r2;
        r0 = 0;
        r11.f4593q = r0;
        r0 = java.lang.System.currentTimeMillis();
        r11.f4581e = r0;
        r0 = r12.entrySet();
        r5 = r0.iterator();
    L_0x003d:
        r0 = r5.hasNext();
        if (r0 == 0) goto L_0x023c;
    L_0x0043:
        r0 = r5.next();
        r0 = (java.util.Map.Entry) r0;
        r1 = r0.getKey();
        r1 = (java.lang.String) r1;
        r6 = r1.hashCode();
        switch(r6) {
            case -1899431321: goto L_0x00e6;
            case -1561601017: goto L_0x0090;
            case -856794442: goto L_0x00ce;
            case -726276175: goto L_0x00da;
            case -634541425: goto L_0x009a;
            case -553208868: goto L_0x00a4;
            case 3575610: goto L_0x0068;
            case 700812481: goto L_0x0072;
            case 858630459: goto L_0x007c;
            case 986744879: goto L_0x00f2;
            case 1085444827: goto L_0x0086;
            case 1183549815: goto L_0x00c3;
            case 1503616961: goto L_0x00b8;
            case 2002133996: goto L_0x00ae;
            default: goto L_0x0056;
        };
    L_0x0056:
        r1 = r3;
    L_0x0057:
        switch(r1) {
            case 0: goto L_0x005b;
            case 1: goto L_0x00fe;
            case 2: goto L_0x010c;
            case 3: goto L_0x011a;
            case 4: goto L_0x0128;
            case 5: goto L_0x0136;
            case 6: goto L_0x0144;
            case 7: goto L_0x0156;
            case 8: goto L_0x0164;
            case 9: goto L_0x0172;
            case 10: goto L_0x0180;
            case 11: goto L_0x018e;
            case 12: goto L_0x019c;
            case 13: goto L_0x0229;
            default: goto L_0x005a;
        };
    L_0x005a:
        goto L_0x003d;
    L_0x005b:
        r0 = r0.getValue();
        r0 = (java.lang.String) r0;
        r0 = com.facebook.ads.internal.protocol.AdPlacementType.fromString(r0);
        r11.f4582f = r0;
        goto L_0x003d;
    L_0x0068:
        r6 = "type";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x0056;
    L_0x0070:
        r1 = r2;
        goto L_0x0057;
    L_0x0072:
        r6 = "min_viewability_percentage";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x0056;
    L_0x007a:
        r1 = r4;
        goto L_0x0057;
    L_0x007c:
        r6 = "viewability_check_ticker";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x0056;
    L_0x0084:
        r1 = 2;
        goto L_0x0057;
    L_0x0086:
        r6 = "refresh";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x0056;
    L_0x008e:
        r1 = 3;
        goto L_0x0057;
    L_0x0090:
        r6 = "refresh_threshold";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x0056;
    L_0x0098:
        r1 = 4;
        goto L_0x0057;
    L_0x009a:
        r6 = "invalidation_duration_in_seconds";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x0056;
    L_0x00a2:
        r1 = 5;
        goto L_0x0057;
    L_0x00a4:
        r6 = "cacheable";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x0056;
    L_0x00ac:
        r1 = 6;
        goto L_0x0057;
    L_0x00ae:
        r6 = "placement_width";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x0056;
    L_0x00b6:
        r1 = 7;
        goto L_0x0057;
    L_0x00b8:
        r6 = "placement_height";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x0056;
    L_0x00c0:
        r1 = 8;
        goto L_0x0057;
    L_0x00c3:
        r6 = "viewability_check_initial_delay";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x0056;
    L_0x00cb:
        r1 = 9;
        goto L_0x0057;
    L_0x00ce:
        r6 = "viewability_check_interval";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x0056;
    L_0x00d6:
        r1 = 10;
        goto L_0x0057;
    L_0x00da:
        r6 = "request_timeout";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x0056;
    L_0x00e2:
        r1 = 11;
        goto L_0x0057;
    L_0x00e6:
        r6 = "conv_tracking_data";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x0056;
    L_0x00ee:
        r1 = 12;
        goto L_0x0057;
    L_0x00f2:
        r6 = "video_time_polling_interval";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x0056;
    L_0x00fa:
        r1 = 13;
        goto L_0x0057;
    L_0x00fe:
        r0 = r0.getValue();
        r0 = (java.lang.String) r0;
        r0 = java.lang.Integer.parseInt(r0);
        r11.f4583g = r0;
        goto L_0x003d;
    L_0x010c:
        r0 = r0.getValue();
        r0 = (java.lang.String) r0;
        r0 = java.lang.Integer.parseInt(r0);
        r11.f4584h = r0;
        goto L_0x003d;
    L_0x011a:
        r0 = r0.getValue();
        r0 = (java.lang.String) r0;
        r0 = java.lang.Integer.parseInt(r0);
        r11.f4585i = r0;
        goto L_0x003d;
    L_0x0128:
        r0 = r0.getValue();
        r0 = (java.lang.String) r0;
        r0 = java.lang.Integer.parseInt(r0);
        r11.f4586j = r0;
        goto L_0x003d;
    L_0x0136:
        r0 = r0.getValue();
        r0 = (java.lang.String) r0;
        r0 = java.lang.Integer.parseInt(r0);
        r11.f4591o = r0;
        goto L_0x003d;
    L_0x0144:
        r0 = r0.getValue();
        r0 = (java.lang.String) r0;
        r0 = java.lang.Boolean.valueOf(r0);
        r0 = r0.booleanValue();
        r11.f4592p = r0;
        goto L_0x003d;
    L_0x0156:
        r0 = r0.getValue();
        r0 = (java.lang.String) r0;
        r0 = java.lang.Integer.parseInt(r0);
        r11.f4579a = r0;
        goto L_0x003d;
    L_0x0164:
        r0 = r0.getValue();
        r0 = (java.lang.String) r0;
        r0 = java.lang.Integer.parseInt(r0);
        r11.f4580b = r0;
        goto L_0x003d;
    L_0x0172:
        r0 = r0.getValue();
        r0 = (java.lang.String) r0;
        r0 = java.lang.Integer.parseInt(r0);
        r11.f4587k = r0;
        goto L_0x003d;
    L_0x0180:
        r0 = r0.getValue();
        r0 = (java.lang.String) r0;
        r0 = java.lang.Integer.parseInt(r0);
        r11.f4588l = r0;
        goto L_0x003d;
    L_0x018e:
        r0 = r0.getValue();
        r0 = (java.lang.String) r0;
        r0 = java.lang.Integer.parseInt(r0);
        r11.f4589m = r0;
        goto L_0x003d;
    L_0x019c:
        r0 = r0.getValue();
        r0 = (java.lang.String) r0;
        r0 = com.facebook.ads.internal.p043m.C2045b.m4967a(r0);
        r11.f4593q = r0;
        r1 = android.webkit.CookieManager.getInstance();	 Catch:{ Exception -> 0x020d }
        r6 = r1.acceptCookie();	 Catch:{ Exception -> 0x020d }
        r0 = 1;
        r1.setAcceptCookie(r0);	 Catch:{ Exception -> 0x020d }
        r0 = r11.f4593q;	 Catch:{ Exception -> 0x020d }
        r7 = r0.iterator();	 Catch:{ Exception -> 0x020d }
    L_0x01ba:
        r0 = r7.hasNext();	 Catch:{ Exception -> 0x020d }
        if (r0 == 0) goto L_0x0217;
    L_0x01c0:
        r0 = r7.next();	 Catch:{ Exception -> 0x020d }
        r0 = (com.facebook.ads.internal.p043m.C2045b) r0;	 Catch:{ Exception -> 0x020d }
        r8 = r0.m4969b();	 Catch:{ Exception -> 0x020d }
        if (r8 == 0) goto L_0x01ba;
    L_0x01cc:
        r8 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x020d }
        r8.<init>();	 Catch:{ Exception -> 0x020d }
        r9 = r0.f4568b;	 Catch:{ Exception -> 0x020d }
        r8 = r8.append(r9);	 Catch:{ Exception -> 0x020d }
        r9 = "=";
        r8 = r8.append(r9);	 Catch:{ Exception -> 0x020d }
        r9 = r0.f4569c;	 Catch:{ Exception -> 0x020d }
        r8 = r8.append(r9);	 Catch:{ Exception -> 0x020d }
        r9 = ";Domain=";
        r8 = r8.append(r9);	 Catch:{ Exception -> 0x020d }
        r9 = r0.f4567a;	 Catch:{ Exception -> 0x020d }
        r8 = r8.append(r9);	 Catch:{ Exception -> 0x020d }
        r9 = ";Expires=";
        r8 = r8.append(r9);	 Catch:{ Exception -> 0x020d }
        r9 = r0.m4968a();	 Catch:{ Exception -> 0x020d }
        r8 = r8.append(r9);	 Catch:{ Exception -> 0x020d }
        r9 = ";path=/";
        r8 = r8.append(r9);	 Catch:{ Exception -> 0x020d }
        r8 = r8.toString();	 Catch:{ Exception -> 0x020d }
        r0 = r0.f4567a;	 Catch:{ Exception -> 0x020d }
        r1.setCookie(r0, r8);	 Catch:{ Exception -> 0x020d }
        goto L_0x01ba;
    L_0x020d:
        r0 = move-exception;
        r1 = f4577c;
        r6 = "Failed to set cookie.";
        android.util.Log.w(r1, r6, r0);
        goto L_0x003d;
    L_0x0217:
        r0 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x020d }
        r7 = 21;
        if (r0 >= r7) goto L_0x0224;
    L_0x021d:
        r0 = android.webkit.CookieSyncManager.getInstance();	 Catch:{ Exception -> 0x020d }
        r0.startSync();	 Catch:{ Exception -> 0x020d }
    L_0x0224:
        r1.setAcceptCookie(r6);	 Catch:{ Exception -> 0x020d }
        goto L_0x003d;
    L_0x0229:
        r0 = r0.getValue();	 Catch:{ NumberFormatException -> 0x0237 }
        r0 = (java.lang.String) r0;	 Catch:{ NumberFormatException -> 0x0237 }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ NumberFormatException -> 0x0237 }
        r11.f4590n = r0;	 Catch:{ NumberFormatException -> 0x0237 }
        goto L_0x003d;
    L_0x0237:
        r0 = move-exception;
        r11.f4590n = r10;
        goto L_0x003d;
    L_0x023c:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.m.d.<init>(java.util.Map):void");
    }

    /* renamed from: a */
    public static C2047d m4979a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        Iterator keys = jSONObject.keys();
        Map hashMap = new HashMap();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            hashMap.put(str, String.valueOf(jSONObject.opt(str)));
        }
        return new C2047d(hashMap);
    }

    /* renamed from: a */
    public long m4980a() {
        return this.f4581e;
    }

    /* renamed from: b */
    public AdPlacementType m4981b() {
        return this.f4582f;
    }

    /* renamed from: c */
    public long m4982c() {
        return (long) (this.f4585i * 1000);
    }

    /* renamed from: d */
    public long m4983d() {
        return (long) (this.f4586j * 1000);
    }

    /* renamed from: e */
    public boolean m4984e() {
        return this.f4592p;
    }

    /* renamed from: f */
    public int m4985f() {
        return this.f4583g;
    }

    /* renamed from: g */
    public int m4986g() {
        return this.f4584h;
    }

    /* renamed from: h */
    public int m4987h() {
        return this.f4587k;
    }

    /* renamed from: i */
    public int m4988i() {
        return this.f4588l;
    }

    /* renamed from: j */
    public int m4989j() {
        return this.f4589m;
    }

    /* renamed from: k */
    public int m4990k() {
        return this.f4590n;
    }

    /* renamed from: l */
    public int m4991l() {
        return this.f4591o * 1000;
    }
}
