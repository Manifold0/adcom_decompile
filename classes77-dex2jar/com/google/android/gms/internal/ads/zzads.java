// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzads implements Runnable
{
    private final /* synthetic */ zzadk zzccn;
    
    zzads(final zzadk zzccn) {
        this.zzccn = zzccn;
    }
    
    @Override
    public final void run() {
        synchronized (this.zzccn.zzbzh) {
            if (this.zzccn.zzccj == null) {
                return;
            }
            this.zzccn.onStop();
            this.zzccn.zzc(2, "Timed out waiting for ad response.");
        }
    }
}
