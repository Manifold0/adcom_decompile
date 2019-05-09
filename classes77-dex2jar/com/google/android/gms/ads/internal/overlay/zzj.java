// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal.overlay;

import android.graphics.Bitmap;
import android.content.Context;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzajx;

@zzadh
final class zzj extends zzajx
{
    final /* synthetic */ zzd zzbyg;
    
    private zzj(final zzd zzbyg) {
        this.zzbyg = zzbyg;
    }
    
    @Override
    public final void onStop() {
    }
    
    @Override
    public final void zzdn() {
        final Bitmap zza = zzbv.zzfe().zza(this.zzbyg.zzbxn.zzbyw.zzzj);
        if (zza != null) {
            zzakk.zzcrm.post((Runnable)new zzk(this, zzbv.zzem().zza((Context)this.zzbyg.mActivity, zza, this.zzbyg.zzbxn.zzbyw.zzzh, this.zzbyg.zzbxn.zzbyw.zzzi)));
        }
    }
}
