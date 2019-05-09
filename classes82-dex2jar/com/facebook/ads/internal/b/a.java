// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.b;

import com.facebook.ads.internal.w.b.q;
import com.facebook.ads.AdSettings;
import com.facebook.ads.internal.w.b.m;
import com.facebook.ads.internal.u.b;
import com.facebook.ads.internal.protocol.g;
import android.content.Context;
import com.facebook.ads.internal.protocol.AdPlacementType;
import android.support.annotation.Nullable;
import com.facebook.ads.CacheFlag;
import java.util.EnumSet;
import com.facebook.ads.internal.protocol.d;
import com.facebook.ads.internal.protocol.e;

public class a
{
    final String a;
    final e b;
    final d c;
    final EnumSet<CacheFlag> d;
    String e;
    boolean f;
    int g;
    @Nullable
    com.facebook.ads.internal.t.d h;
    private final AdPlacementType i;
    private final int j;
    
    public a(final String s, final e e, final AdPlacementType adPlacementType, final d d, final int n) {
        this(s, e, adPlacementType, d, n, EnumSet.of(CacheFlag.NONE));
    }
    
    public a(final String a, final e b, final AdPlacementType i, final d c, final int j, final EnumSet<CacheFlag> d) {
        this.a = a;
        this.i = i;
        this.c = c;
        this.j = j;
        this.d = d;
        this.b = b;
        this.g = -1;
    }
    
    AdPlacementType a() {
        if (this.i != null) {
            return this.i;
        }
        if (this.c == null) {
            return AdPlacementType.NATIVE;
        }
        if (this.c == com.facebook.ads.internal.protocol.d.b) {
            return AdPlacementType.INTERSTITIAL;
        }
        return AdPlacementType.BANNER;
    }
    
    b a(final Context context, final g g) {
        final com.facebook.ads.internal.n.d d = new com.facebook.ads.internal.n.d(context, false);
        final String a = this.a;
        m m;
        if (this.c != null) {
            m = new m(this.c.b(), this.c.a());
        }
        else {
            m = null;
        }
        final e b = this.b;
        String adTypeString;
        if (AdSettings.getTestAdType() != AdSettings.TestAdType.DEFAULT) {
            adTypeString = AdSettings.getTestAdType().getAdTypeString();
        }
        else {
            adTypeString = null;
        }
        return new b(context, d, a, m, b, adTypeString, this.j, AdSettings.isTestMode(context), AdSettings.isChildDirected(), g, q.a(com.facebook.ads.internal.r.a.G(context)), this.e);
    }
    
    public void a(final int g) {
        this.g = g;
    }
    
    public void a(@Nullable final com.facebook.ads.internal.t.d h) {
        this.h = h;
    }
    
    public void a(final String e) {
        this.e = e;
    }
    
    public void a(final boolean f) {
        this.f = f;
    }
}
