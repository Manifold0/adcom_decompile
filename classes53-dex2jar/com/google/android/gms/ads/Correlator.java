// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzkc;
import com.google.android.gms.internal.ads.zzadh;

@zzadh
public final class Correlator
{
    @VisibleForTesting
    zzkc zzuu;
    
    public Correlator() {
        this.zzuu = new zzkc();
    }
    
    public final void reset() {
        this.zzuu.zzil();
    }
    
    public final zzkc zzaz() {
        return this.zzuu;
    }
}
