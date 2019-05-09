// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.c;

import com.facebook.ads.internal.r.a;
import java.lang.ref.WeakReference;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardData;
import android.support.annotation.Nullable;
import com.facebook.ads.RewardedVideoAdListener;
import android.content.Context;

public class j
{
    public final Context a;
    public final String b;
    @Nullable
    public RewardedVideoAdListener c;
    @Nullable
    public String d;
    @Nullable
    public RewardData e;
    @Nullable
    public String f;
    public boolean g;
    public int h;
    public long i;
    @Nullable
    private RewardedVideoAd j;
    private WeakReference<RewardedVideoAd> k;
    
    public j(final Context a, final String b, @Nullable final RewardedVideoAd j) {
        this.h = -1;
        this.a = a;
        this.b = b;
        this.j = j;
        this.k = new WeakReference<RewardedVideoAd>(j);
        this.i = -1L;
    }
    
    @Nullable
    RewardedVideoAd a() {
        if (this.j != null) {
            return this.j;
        }
        return this.k.get();
    }
    
    public void a(@Nullable final RewardedVideoAd j) {
        if (j == null && !com.facebook.ads.internal.r.a.Z(this.a)) {
            return;
        }
        this.j = j;
    }
}
