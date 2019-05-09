package com.facebook.ads.internal.p028u;

import android.content.Context;
import com.facebook.ads.internal.p021o.C2059b;
import com.facebook.ads.internal.p025w.p026b.C2583m;
import com.facebook.ads.internal.p025w.p026b.C2597v;
import com.facebook.ads.internal.p025w.p026b.C2601z;
import com.facebook.ads.internal.p037f.C1993a;
import com.facebook.ads.internal.p038g.C2002b;
import com.facebook.ads.internal.p045n.C2057d;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.C2068c;
import com.facebook.ads.internal.protocol.C2070e;
import com.facebook.ads.internal.protocol.C2074g;
import com.facebook.appevents.AppEventsConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* renamed from: com.facebook.ads.internal.u.b */
public class C2122b {
    /* renamed from: a */
    private String f4904a;
    /* renamed from: b */
    private C2068c f4905b;
    /* renamed from: c */
    private final AdPlacementType f4906c = this.f4905b.m5043a();
    /* renamed from: d */
    private final String f4907d;
    /* renamed from: e */
    private Context f4908e;
    /* renamed from: f */
    private C2070e f4909f;
    /* renamed from: g */
    private boolean f4910g;
    /* renamed from: h */
    private boolean f4911h;
    /* renamed from: i */
    private int f4912i;
    /* renamed from: j */
    private C2583m f4913j;
    /* renamed from: k */
    private final Map<String, String> f4914k;
    /* renamed from: l */
    private final C2074g f4915l;
    /* renamed from: m */
    private String f4916m;
    /* renamed from: n */
    private String f4917n;

    public C2122b(Context context, C2057d c2057d, String str, C2583m c2583m, C2070e c2070e, String str2, int i, boolean z, boolean z2, C2074g c2074g, String str3, String str4) {
        this.f4908e = context;
        this.f4914k = c2057d.m5019b();
        this.f4904a = str;
        this.f4913j = c2583m;
        this.f4909f = c2070e;
        this.f4907d = str2;
        this.f4912i = i;
        this.f4910g = z;
        this.f4911h = z2;
        this.f4915l = c2074g;
        this.f4905b = C2068c.m5042a(c2070e);
        this.f4916m = str3;
        this.f4917n = str4;
    }

    /* renamed from: a */
    private void m5390a(Map<String, String> map, String str, String str2) {
        map.put(str, str2);
    }

    /* renamed from: a */
    public C2070e m5391a() {
        return this.f4909f;
    }

    /* renamed from: b */
    public String m5392b() {
        return this.f4904a;
    }

    /* renamed from: c */
    public C2068c m5393c() {
        return this.f4905b;
    }

    /* renamed from: d */
    public C2583m m5394d() {
        return this.f4913j;
    }

    /* renamed from: e */
    public int m5395e() {
        return this.f4912i;
    }

    /* renamed from: f */
    public C2074g m5396f() {
        return this.f4915l;
    }

    /* renamed from: g */
    public Map<String, String> m5397g() {
        Map<String, String> hashMap = new HashMap(this.f4914k);
        m5390a(hashMap, "IDFA", C2002b.f4431b);
        m5390a(hashMap, "IDFA_FLAG", C2002b.f4432c ? AppEventsConstants.EVENT_PARAM_VALUE_NO : "1");
        m5390a(hashMap, "COPPA", String.valueOf(this.f4911h));
        m5390a(hashMap, "PLACEMENT_ID", this.f4904a);
        if (this.f4906c != AdPlacementType.UNKNOWN) {
            m5390a(hashMap, "PLACEMENT_TYPE", this.f4906c.toString().toLowerCase());
        }
        if (this.f4913j != null) {
            m5390a(hashMap, "WIDTH", String.valueOf(this.f4913j.m6649b()));
            m5390a(hashMap, "HEIGHT", String.valueOf(this.f4913j.m6648a()));
        }
        if (this.f4909f != null) {
            m5390a(hashMap, "TEMPLATE_ID", String.valueOf(this.f4909f.m5047a()));
        }
        if (this.f4910g) {
            m5390a(hashMap, "TEST_MODE", "1");
        }
        if (this.f4907d != null) {
            m5390a(hashMap, "DEMO_AD_ID", this.f4907d);
        }
        if (this.f4912i != 0) {
            m5390a(hashMap, "NUM_ADS_REQUESTED", String.valueOf(this.f4912i));
        }
        m5390a(hashMap, "CLIENT_EVENTS", C2059b.m5022a());
        m5390a(hashMap, "KG_RESTRICTED", String.valueOf(C2601z.m6691a(this.f4908e)));
        m5390a(hashMap, "REQUEST_TIME", C2597v.m6669b(System.currentTimeMillis()));
        if (this.f4915l.m5054c()) {
            m5390a(hashMap, "BID_ID", this.f4915l.m5055d());
        }
        if (this.f4916m != null) {
            m5390a(hashMap, "STACK_TRACE", this.f4916m);
        }
        m5390a(hashMap, "CLIENT_REQUEST_ID", UUID.randomUUID().toString());
        m5390a(hashMap, "AD_REPORTING_CONFIG_LAST_UPDATE_TIME", C2597v.m6668a(C1993a.m4782a(this.f4908e)));
        if (this.f4917n != null) {
            m5390a(hashMap, "EXTRA_HINTS", this.f4917n);
        }
        return hashMap;
    }
}
