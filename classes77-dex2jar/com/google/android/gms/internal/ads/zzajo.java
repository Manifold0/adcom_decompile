// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;

final class zzajo extends zzajx
{
    private final /* synthetic */ zzajm zzcpw;
    
    zzajo(final zzajm zzcpw) {
        this.zzcpw = zzcpw;
    }
    
    @Override
    public final void onStop() {
    }
    
    @Override
    public final void zzdn() {
        final zznm zznm = new zznm(this.zzcpw.mContext, this.zzcpw.zzyf.zzcw);
        synchronized (this.zzcpw.mLock) {
            try {
                zzbv.zzet();
                zznp.zza(this.zzcpw.zzcpn, zznm);
            }
            catch (IllegalArgumentException ex) {
                zzakb.zzc("Cannot config CSI reporter.", (Throwable)ex);
            }
        }
    }
}
