// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal.overlay;

import android.graphics.drawable.Drawable;

final class zzk implements Runnable
{
    private final /* synthetic */ Drawable zzbyj;
    private final /* synthetic */ zzj zzbyk;
    
    zzk(final zzj zzbyk, final Drawable zzbyj) {
        this.zzbyk = zzbyk;
        this.zzbyj = zzbyj;
    }
    
    @Override
    public final void run() {
        this.zzbyk.zzbyg.mActivity.getWindow().setBackgroundDrawable(this.zzbyj);
    }
}
