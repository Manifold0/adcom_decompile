// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzk implements Runnable
{
    private final zzr zzw;
    private final zzx zzx;
    private final Runnable zzy;
    
    public zzk(final zzi zzi, final zzr zzw, final zzx zzx, final Runnable zzy) {
        this.zzw = zzw;
        this.zzx = zzx;
        this.zzy = zzy;
    }
    
    @Override
    public final void run() {
        this.zzw.isCanceled();
        int n;
        if (this.zzx.zzbh == null) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n != 0) {
            this.zzw.zza(this.zzx.result);
        }
        else {
            this.zzw.zzb(this.zzx.zzbh);
        }
        if (this.zzx.zzbi) {
            this.zzw.zzb("intermediate-response");
        }
        else {
            this.zzw.zzc("done");
        }
        if (this.zzy != null) {
            this.zzy.run();
        }
    }
}
