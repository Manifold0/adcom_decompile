// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.banners;

import android.view.View;

public interface IUnityBannerListener
{
    void onUnityBannerClick(final String p0);
    
    void onUnityBannerError(final String p0);
    
    void onUnityBannerHide(final String p0);
    
    void onUnityBannerLoaded(final String p0, final View p1);
    
    void onUnityBannerShow(final String p0);
    
    void onUnityBannerUnloaded(final String p0);
}
