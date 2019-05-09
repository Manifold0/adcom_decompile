// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.u;

import com.facebook.ads.internal.f.a;
import java.util.UUID;
import com.facebook.ads.internal.w.b.v;
import com.facebook.ads.internal.w.b.z;
import java.util.HashMap;
import com.facebook.ads.internal.n.d;
import com.facebook.ads.internal.protocol.g;
import java.util.Map;
import com.facebook.ads.internal.w.b.m;
import com.facebook.ads.internal.protocol.e;
import android.content.Context;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.c;

public class b
{
    private String a;
    private c b;
    private final AdPlacementType c;
    private final String d;
    private Context e;
    private e f;
    private boolean g;
    private boolean h;
    private int i;
    private m j;
    private final Map<String, String> k;
    private final g l;
    private String m;
    private String n;
    
    public b(final Context e, final d d, final String a, final m j, final e f, final String d2, final int i, final boolean g, final boolean h, final g l, final String m, final String n) {
        this.e = e;
        this.k = d.b();
        this.a = a;
        this.j = j;
        this.f = f;
        this.d = d2;
        this.i = i;
        this.g = g;
        this.h = h;
        this.l = l;
        this.b = com.facebook.ads.internal.protocol.c.a(f);
        this.c = this.b.a();
        this.m = m;
        this.n = n;
    }
    
    private void a(final Map<String, String> map, final String s, final String s2) {
        map.put(s, s2);
    }
    
    public e a() {
        return this.f;
    }
    
    public String b() {
        return this.a;
    }
    
    public c c() {
        return this.b;
    }
    
    public m d() {
        return this.j;
    }
    
    public int e() {
        return this.i;
    }
    
    public g f() {
        return this.l;
    }
    
    public Map<String, String> g() {
        final HashMap<String, String> hashMap = new HashMap<String, String>(this.k);
        this.a(hashMap, "IDFA", com.facebook.ads.internal.g.b.b);
        String s;
        if (com.facebook.ads.internal.g.b.c) {
            s = "0";
        }
        else {
            s = "1";
        }
        this.a(hashMap, "IDFA_FLAG", s);
        this.a(hashMap, "COPPA", String.valueOf(this.h));
        this.a(hashMap, "PLACEMENT_ID", this.a);
        if (this.c != AdPlacementType.UNKNOWN) {
            this.a(hashMap, "PLACEMENT_TYPE", this.c.toString().toLowerCase());
        }
        if (this.j != null) {
            this.a(hashMap, "WIDTH", String.valueOf(this.j.b()));
            this.a(hashMap, "HEIGHT", String.valueOf(this.j.a()));
        }
        if (this.f != null) {
            this.a(hashMap, "TEMPLATE_ID", String.valueOf(this.f.a()));
        }
        if (this.g) {
            this.a(hashMap, "TEST_MODE", "1");
        }
        if (this.d != null) {
            this.a(hashMap, "DEMO_AD_ID", this.d);
        }
        if (this.i != 0) {
            this.a(hashMap, "NUM_ADS_REQUESTED", String.valueOf(this.i));
        }
        this.a(hashMap, "CLIENT_EVENTS", com.facebook.ads.internal.o.b.a());
        this.a(hashMap, "KG_RESTRICTED", String.valueOf(z.a(this.e)));
        this.a(hashMap, "REQUEST_TIME", v.b(System.currentTimeMillis()));
        if (this.l.c()) {
            this.a(hashMap, "BID_ID", this.l.d());
        }
        if (this.m != null) {
            this.a(hashMap, "STACK_TRACE", this.m);
        }
        this.a(hashMap, "CLIENT_REQUEST_ID", UUID.randomUUID().toString());
        this.a(hashMap, "AD_REPORTING_CONFIG_LAST_UPDATE_TIME", v.a(com.facebook.ads.internal.f.a.a(this.e)));
        if (this.n != null) {
            this.a(hashMap, "EXTRA_HINTS", this.n);
        }
        return hashMap;
    }
}
