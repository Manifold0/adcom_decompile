// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzvp implements zzaom
{
    private final /* synthetic */ zzvf zzbqk;
    private final /* synthetic */ zzvw zzbqn;
    
    zzvp(final zzvf zzbqk, final zzvw zzbqn) {
        this.zzbqk = zzbqk;
        this.zzbqn = zzbqn;
    }
    
    @Override
    public final void run() {
        synchronized (this.zzbqk.mLock) {
            this.zzbqk.zzbqb = 1;
            zzakb.v("Failed loading new engine. Marking new engine destroyable.");
            this.zzbqn.zzmb();
        }
    }
}
