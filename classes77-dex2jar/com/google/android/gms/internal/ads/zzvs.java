// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzvs extends zzaop<zzwb>
{
    private final Object mLock;
    private final zzvw zzbqq;
    private boolean zzbqr;
    
    public zzvs(final zzvw zzbqq) {
        this.mLock = new Object();
        this.zzbqq = zzbqq;
    }
    
    public final void release() {
        synchronized (this.mLock) {
            if (this.zzbqr) {
                return;
            }
            this.zzbqr = true;
            this.zza(new zzvt(this), new zzaon());
            this.zza(new zzvu(this), new zzvv(this));
        }
    }
}
