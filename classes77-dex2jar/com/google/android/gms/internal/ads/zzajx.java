// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

@zzadh
public abstract class zzajx implements zzalc<zzanz>
{
    private volatile Thread zzcqr;
    private boolean zzcqs;
    private final Runnable zzy;
    
    public zzajx() {
        this.zzy = new zzajy(this);
        this.zzcqs = false;
    }
    
    public zzajx(final boolean b) {
        this.zzy = new zzajy(this);
        this.zzcqs = true;
    }
    
    @Override
    public final void cancel() {
        this.onStop();
        if (this.zzcqr != null) {
            this.zzcqr.interrupt();
        }
    }
    
    public abstract void onStop();
    
    public abstract void zzdn();
    
    public final zzanz zzqo() {
        if (this.zzcqs) {
            return zzaki.zzc(this.zzy);
        }
        return zzaki.zzb(this.zzy);
    }
}
