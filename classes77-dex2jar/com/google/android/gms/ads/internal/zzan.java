// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzaig;
import com.google.android.gms.internal.ads.zzahu;

final class zzan implements zzahu
{
    private final /* synthetic */ zzal zzza;
    
    zzan(final zzal zzza) {
        this.zzza = zzza;
    }
    
    @Override
    public final void onRewardedVideoAdClosed() {
        this.zzza.zzcb();
    }
    
    @Override
    public final void onRewardedVideoAdLeftApplication() {
        this.zzza.zzbo();
    }
    
    @Override
    public final void onRewardedVideoAdOpened() {
        this.zzza.zzcc();
    }
    
    @Override
    public final void onRewardedVideoCompleted() {
        this.zzza.zzdl();
    }
    
    @Override
    public final void onRewardedVideoStarted() {
        this.zzza.zzdk();
    }
    
    @Override
    public final void zzc(final zzaig zzaig) {
        this.zzza.zzb(zzaig);
    }
    
    @Override
    public final void zzdm() {
        this.zzza.onAdClicked();
    }
}
