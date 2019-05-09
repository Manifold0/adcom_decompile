// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

final class zzvm implements Runnable
{
    private final /* synthetic */ zzvw zzbqi;
    private final /* synthetic */ zzuu zzbqj;
    private final /* synthetic */ zzvf zzbqk;
    
    zzvm(final zzvf zzbqk, final zzvw zzbqi, final zzuu zzbqj) {
        this.zzbqk = zzbqk;
        this.zzbqi = zzbqi;
        this.zzbqj = zzbqj;
    }
    
    @Override
    public final void run() {
        synchronized (this.zzbqk.mLock) {
            if (this.zzbqi.getStatus() == -1 || this.zzbqi.getStatus() == 1) {
                return;
            }
            this.zzbqi.reject();
            final Executor zzcvy = zzaoe.zzcvy;
            final zzuu zzbqj = this.zzbqj;
            zzbqj.getClass();
            zzcvy.execute(zzvn.zza(zzbqj));
            zzakb.v("Could not receive loaded message in a timely manner. Rejecting.");
        }
    }
}
