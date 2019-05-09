// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;

final class zzaqf implements Runnable
{
    private final /* synthetic */ zzaqe zzdax;
    
    zzaqf(final zzaqe zzdax) {
        this.zzdax = zzdax;
    }
    
    @Override
    public final void run() {
        zzbv.zzff().zzb(this.zzdax);
    }
}
