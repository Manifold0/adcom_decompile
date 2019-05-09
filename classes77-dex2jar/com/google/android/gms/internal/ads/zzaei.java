// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.lang.ref.WeakReference;

@zzadh
public final class zzaei extends zzaer
{
    private final WeakReference<zzadx> zzcen;
    
    public zzaei(final zzadx zzadx) {
        this.zzcen = new WeakReference<zzadx>(zzadx);
    }
    
    public final void zza(final zzaej zzaej) {
        final zzadx zzadx = this.zzcen.get();
        if (zzadx != null) {
            zzadx.zza(zzaej);
        }
    }
}
