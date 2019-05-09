// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;

final class zzzx implements Runnable
{
    private final /* synthetic */ zzzv zzbvr;
    private final /* synthetic */ AdOverlayInfoParcel zzzc;
    
    zzzx(final zzzv zzbvr, final AdOverlayInfoParcel zzzc) {
        this.zzbvr = zzbvr;
        this.zzzc = zzzc;
    }
    
    @Override
    public final void run() {
        zzbv.zzei();
        zzl.zza((Context)this.zzbvr.zzbvp, this.zzzc, true);
    }
}
