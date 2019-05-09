// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.banners.properties;

import android.view.View;

public class BannerProperties
{
    private static View bannerParent;
    
    public static View getBannerParent() {
        return BannerProperties.bannerParent;
    }
    
    public static void setBannerParent(final View bannerParent) {
        BannerProperties.bannerParent = bannerParent;
    }
}
