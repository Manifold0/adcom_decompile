// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.content.Context;

final class zzmc implements Runnable
{
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzmb zzath;
    
    zzmc(final zzmb zzath, final Context val$context) {
        this.zzath = zzath;
        this.val$context = val$context;
    }
    
    @Override
    public final void run() {
        this.zzath.getRewardedVideoAdInstance(this.val$context);
    }
}
