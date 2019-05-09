// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzafb implements Runnable
{
    private final /* synthetic */ zzafa zzcgj;
    private final /* synthetic */ zzaji zzwg;
    
    zzafb(final zzafa zzcgj, final zzaji zzwg) {
        this.zzcgj = zzcgj;
        this.zzwg = zzwg;
    }
    
    @Override
    public final void run() {
        this.zzcgj.zzccf.zza(this.zzwg);
        if (this.zzcgj.zzcgi != null) {
            this.zzcgj.zzcgi.release();
            this.zzcgj.zzcgi = null;
        }
    }
}
