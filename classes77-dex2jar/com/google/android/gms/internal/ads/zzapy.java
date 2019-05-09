// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

@zzadh
final class zzapy implements Runnable
{
    private boolean zzahs;
    private zzapi zzdap;
    
    zzapy(final zzapi zzdap) {
        this.zzahs = false;
        this.zzdap = zzdap;
    }
    
    private final void zztv() {
        zzakk.zzcrm.removeCallbacks((Runnable)this);
        zzakk.zzcrm.postDelayed((Runnable)this, 250L);
    }
    
    public final void pause() {
        this.zzahs = true;
    }
    
    public final void resume() {
        this.zzahs = false;
        this.zztv();
    }
    
    @Override
    public final void run() {
        if (!this.zzahs) {
            this.zzdap.zzte();
            this.zztv();
        }
    }
}
