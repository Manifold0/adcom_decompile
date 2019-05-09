// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzxc implements Runnable
{
    private final /* synthetic */ zzxa zzbts;
    private final /* synthetic */ zzxb zzbtt;
    
    zzxc(final zzxb zzbtt, final zzxa zzbts) {
        this.zzbtt = zzbtt;
        this.zzbts = zzbts;
    }
    
    @Override
    public final void run() {
        synchronized (this.zzbtt.mLock) {
            if (this.zzbtt.zzbtq != -2) {
                return;
            }
            this.zzbtt.zzbtp = this.zzbtt.zzmj();
            if (this.zzbtt.zzbtp == null) {
                this.zzbtt.zzx(4);
                return;
            }
        }
        if (this.zzbtt.zzmk() && !zzxb.zza(this.zzbtt, 1)) {
            final String zzf = this.zzbtt.zzbth;
            zzakb.zzdk(new StringBuilder(String.valueOf(zzf).length() + 56).append("Ignoring adapter ").append(zzf).append(" as delayed impression is not supported").toString());
            this.zzbtt.zzx(2);
            // monitorexit(o)
            return;
        }
        this.zzbts.zza(this.zzbtt);
        this.zzbtt.zza(this.zzbts);
    }
    // monitorexit(o)
}
