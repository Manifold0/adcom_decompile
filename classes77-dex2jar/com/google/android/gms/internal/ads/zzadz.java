// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.ads.internal.zzbv;

@zzadh
public abstract class zzadz implements zzadx, zzalc<Void>
{
    private final Object mLock;
    private final zzaol<zzaef> zzccp;
    private final zzadx zzccq;
    
    public zzadz(final zzaol<zzaef> zzccp, final zzadx zzccq) {
        this.mLock = new Object();
        this.zzccp = zzccp;
        this.zzccq = zzccq;
    }
    
    @Override
    public final void cancel() {
        this.zznz();
    }
    
    @Override
    public final void zza(final zzaej zzaej) {
        synchronized (this.mLock) {
            this.zzccq.zza(zzaej);
            this.zznz();
        }
    }
    
    @VisibleForTesting
    final boolean zza(final zzaen zzaen, final zzaef zzaef) {
        try {
            zzaen.zza(zzaef, new zzaei(this));
            return true;
        }
        catch (Throwable t) {
            zzakb.zzc("Could not fetch ad response from ad request service due to an Exception.", t);
            zzbv.zzeo().zza(t, "AdRequestClientTask.getAdResponseFromService");
            this.zzccq.zza(new zzaej(0));
            return false;
        }
    }
    
    public abstract void zznz();
    
    public abstract zzaen zzoa();
}
