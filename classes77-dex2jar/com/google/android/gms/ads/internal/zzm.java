// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzpb;
import com.google.android.gms.internal.ads.zzxa;
import com.google.android.gms.internal.ads.zzxq;
import com.google.android.gms.internal.ads.zzwx;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzajh;

final class zzm implements Runnable
{
    private final /* synthetic */ zzl zzwp;
    
    zzm(final zzl zzwp) {
        this.zzwp = zzwp;
    }
    
    @Override
    public final void run() {
        this.zzwp.zzwm.zzb(new zzajh(this.zzwp.zzwg, null, null, null, null, null, null, null));
    }
}
