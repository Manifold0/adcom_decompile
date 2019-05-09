// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.internal.overlay.zzn;

final class zzzw implements zzn
{
    private final /* synthetic */ zzzv zzbvr;
    
    zzzw(final zzzv zzbvr) {
        this.zzbvr = zzbvr;
    }
    
    @Override
    public final void onPause() {
        zzane.zzck("AdMobCustomTabsAdapter overlay is paused.");
    }
    
    @Override
    public final void onResume() {
        zzane.zzck("AdMobCustomTabsAdapter overlay is resumed.");
    }
    
    @Override
    public final void zzcb() {
        zzane.zzck("AdMobCustomTabsAdapter overlay is closed.");
        this.zzbvr.zzbvq.onAdClosed((MediationInterstitialAdapter)this.zzbvr);
    }
    
    @Override
    public final void zzcc() {
        zzane.zzck("Opening AdMobCustomTabsAdapter overlay.");
        this.zzbvr.zzbvq.onAdOpened((MediationInterstitialAdapter)this.zzbvr);
    }
}
