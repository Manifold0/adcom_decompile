// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.mediation;

public interface MediationBannerListener
{
    void onAdClicked(final MediationBannerAdapter p0);
    
    void onAdClosed(final MediationBannerAdapter p0);
    
    void onAdFailedToLoad(final MediationBannerAdapter p0, final int p1);
    
    void onAdLeftApplication(final MediationBannerAdapter p0);
    
    void onAdLoaded(final MediationBannerAdapter p0);
    
    void onAdOpened(final MediationBannerAdapter p0);
    
    void zza(final MediationBannerAdapter p0, final String p1, final String p2);
}
