// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzaby implements Runnable
{
    private final /* synthetic */ zzaoj zzcaj;
    private final /* synthetic */ String zzcak;
    private final /* synthetic */ zzabv zzcal;
    
    zzaby(final zzabv zzcal, final zzaoj zzcaj, final String zzcak) {
        this.zzcal = zzcal;
        this.zzcaj = zzcaj;
        this.zzcak = zzcak;
    }
    
    @Override
    public final void run() {
        this.zzcaj.set(this.zzcal.zzbzz.zzdv().get((Object)this.zzcak));
    }
}
