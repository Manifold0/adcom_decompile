// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.mediation;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd;

public interface MediationNativeListener
{
    void onAdClicked(final MediationNativeAdapter p0);
    
    void onAdClosed(final MediationNativeAdapter p0);
    
    void onAdFailedToLoad(final MediationNativeAdapter p0, final int p1);
    
    void onAdImpression(final MediationNativeAdapter p0);
    
    void onAdLeftApplication(final MediationNativeAdapter p0);
    
    void onAdLoaded(final MediationNativeAdapter p0, final NativeAdMapper p1);
    
    void onAdLoaded(final MediationNativeAdapter p0, final UnifiedNativeAdMapper p1);
    
    void onAdOpened(final MediationNativeAdapter p0);
    
    void onVideoEnd(final MediationNativeAdapter p0);
    
    void zza(final MediationNativeAdapter p0, final NativeCustomTemplateAd p1);
    
    void zza(final MediationNativeAdapter p0, final NativeCustomTemplateAd p1, final String p2);
}
