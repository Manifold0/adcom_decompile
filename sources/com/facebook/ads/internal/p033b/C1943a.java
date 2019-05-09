package com.facebook.ads.internal.p033b;

import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSettings.TestAdType;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.internal.p017t.C2104d;
import com.facebook.ads.internal.p025w.p026b.C2583m;
import com.facebook.ads.internal.p025w.p026b.C2588q;
import com.facebook.ads.internal.p028u.C2122b;
import com.facebook.ads.internal.p045n.C2057d;
import com.facebook.ads.internal.p050r.C2078a;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.C2069d;
import com.facebook.ads.internal.protocol.C2070e;
import com.facebook.ads.internal.protocol.C2074g;
import java.util.EnumSet;

/* renamed from: com.facebook.ads.internal.b.a */
public class C1943a {
    /* renamed from: a */
    final String f4247a;
    /* renamed from: b */
    final C2070e f4248b;
    /* renamed from: c */
    final C2069d f4249c;
    /* renamed from: d */
    final EnumSet<CacheFlag> f4250d;
    /* renamed from: e */
    String f4251e;
    /* renamed from: f */
    boolean f4252f;
    /* renamed from: g */
    int f4253g;
    @Nullable
    /* renamed from: h */
    C2104d f4254h;
    /* renamed from: i */
    private final AdPlacementType f4255i;
    /* renamed from: j */
    private final int f4256j;

    public C1943a(String str, C2070e c2070e, AdPlacementType adPlacementType, C2069d c2069d, int i) {
        this(str, c2070e, adPlacementType, c2069d, i, EnumSet.of(CacheFlag.NONE));
    }

    public C1943a(String str, C2070e c2070e, AdPlacementType adPlacementType, C2069d c2069d, int i, EnumSet<CacheFlag> enumSet) {
        this.f4247a = str;
        this.f4255i = adPlacementType;
        this.f4249c = c2069d;
        this.f4256j = i;
        this.f4250d = enumSet;
        this.f4248b = c2070e;
        this.f4253g = -1;
    }

    /* renamed from: a */
    AdPlacementType m4598a() {
        return this.f4255i != null ? this.f4255i : this.f4249c == null ? AdPlacementType.NATIVE : this.f4249c == C2069d.INTERSTITIAL ? AdPlacementType.INTERSTITIAL : AdPlacementType.BANNER;
    }

    /* renamed from: a */
    C2122b m4599a(Context context, C2074g c2074g) {
        return new C2122b(context, new C2057d(context, false), this.f4247a, this.f4249c != null ? new C2583m(this.f4249c.m5046b(), this.f4249c.m5045a()) : null, this.f4248b, AdSettings.getTestAdType() != TestAdType.DEFAULT ? AdSettings.getTestAdType().getAdTypeString() : null, this.f4256j, AdSettings.isTestMode(context), AdSettings.isChildDirected(), c2074g, C2588q.m6654a(C2078a.m5068G(context)), this.f4251e);
    }

    /* renamed from: a */
    public void m4600a(int i) {
        this.f4253g = i;
    }

    /* renamed from: a */
    public void m4601a(@Nullable C2104d c2104d) {
        this.f4254h = c2104d;
    }

    /* renamed from: a */
    public void m4602a(String str) {
        this.f4251e = str;
    }

    /* renamed from: a */
    public void m4603a(boolean z) {
        this.f4252f = z;
    }
}
