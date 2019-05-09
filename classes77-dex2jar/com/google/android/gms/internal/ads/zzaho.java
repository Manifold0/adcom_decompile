// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzaho implements Runnable
{
    private final /* synthetic */ zzxq zzclu;
    private final /* synthetic */ zzahn zzclv;
    private final /* synthetic */ zzjj zzyh;
    
    zzaho(final zzahn zzclv, final zzjj zzyh, final zzxq zzclu) {
        this.zzclv = zzclv;
        this.zzyh = zzyh;
        this.zzclu = zzclu;
    }
    
    @Override
    public final void run() {
        this.zzclv.zza(this.zzyh, this.zzclu);
    }
}
