// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.mediation.customevent;

import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import com.google.android.gms.ads.mediation.NativeAdMapper;

public interface CustomEventNativeListener extends CustomEventListener
{
    void onAdImpression();
    
    void onAdLoaded(final NativeAdMapper p0);
    
    void onAdLoaded(final UnifiedNativeAdMapper p0);
}
