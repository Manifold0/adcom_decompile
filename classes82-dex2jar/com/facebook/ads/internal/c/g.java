// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.c;

import com.facebook.ads.internal.r.a;
import java.lang.ref.WeakReference;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.CacheFlag;
import java.util.EnumSet;
import android.support.annotation.Nullable;
import com.facebook.ads.InterstitialAdListener;
import android.content.Context;

public class g
{
    public final Context a;
    public final String b;
    @Nullable
    public InterstitialAdListener c;
    @Nullable
    public String d;
    public EnumSet<CacheFlag> e;
    public String f;
    public long g;
    @Nullable
    private InterstitialAd h;
    private WeakReference<InterstitialAd> i;
    
    public g(final Context a, @Nullable final InterstitialAd h, final String b) {
        this.a = a;
        this.b = b;
        this.h = h;
        this.i = new WeakReference<InterstitialAd>(h);
        this.g = -1L;
    }
    
    @Nullable
    InterstitialAd a() {
        if (this.h != null) {
            return this.h;
        }
        return this.i.get();
    }
    
    public void a(@Nullable final InterstitialAd h) {
        if (h == null && !com.facebook.ads.internal.r.a.Z(this.a)) {
            return;
        }
        this.h = h;
    }
}
