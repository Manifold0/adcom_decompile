// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

public final class zzdt implements Callable
{
    private final zzcz zzps;
    private final zzba zztq;
    
    public zzdt(final zzcz zzps, final zzba zztq) {
        this.zzps = zzps;
        this.zztq = zztq;
    }
    
    private final Void zzat() throws Exception {
        if (this.zzps.zzak() != null) {
            this.zzps.zzak().get();
        }
        final zzba zzaj = this.zzps.zzaj();
        if (zzaj != null) {
            try {
                synchronized (this.zztq) {
                    zzbfi.zza((zzbfi)this.zztq, zzbfi.zzb((zzbfi)zzaj));
                }
            }
            catch (zzbfh zzbfh) {}
        }
        return null;
    }
}
