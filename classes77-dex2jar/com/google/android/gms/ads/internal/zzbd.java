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
import com.google.android.gms.internal.ads.zzaji;

final class zzbd implements Runnable
{
    private final /* synthetic */ zzbc zzaaf;
    private final /* synthetic */ zzaji zzwg;
    
    zzbd(final zzbc zzaaf, final zzaji zzwg) {
        this.zzaaf = zzaaf;
        this.zzwg = zzwg;
    }
    
    @Override
    public final void run() {
        this.zzaaf.zzb(new zzajh(this.zzwg, null, null, null, null, null, null, null));
    }
}
