// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.mediation;

import java.util.Map;
import com.google.android.gms.ads.formats.NativeAdOptions;

public interface NativeMediationAdRequest extends MediationAdRequest
{
    float getAdVolume();
    
    NativeAdOptions getNativeAdOptions();
    
    boolean isAdMuted();
    
    boolean isAppInstallAdRequested();
    
    boolean isContentAdRequested();
    
    boolean isUnifiedNativeAdRequested();
    
    boolean zzna();
    
    Map<String, Boolean> zznb();
}
