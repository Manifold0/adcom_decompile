// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import org.json.JSONObject;

final class zzafc implements Runnable
{
    private final /* synthetic */ zzafa zzcgj;
    final /* synthetic */ JSONObject zzcgk;
    final /* synthetic */ String zzcgl;
    
    zzafc(final zzafa zzcgj, final JSONObject zzcgk, final String zzcgl) {
        this.zzcgj = zzcgj;
        this.zzcgk = zzcgk;
        this.zzcgl = zzcgl;
    }
    
    @Override
    public final void run() {
        this.zzcgj.zzcgi = zzafa.zzcge.zzb((zzci)null);
        this.zzcgj.zzcgi.zza(new zzafd(this), new zzafe(this));
    }
}
