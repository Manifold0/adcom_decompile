// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.sdk;

import com.ironsource.mediationsdk.IronSourceBannerLayout;
import org.json.JSONObject;

public interface BannerAdapterApi
{
    void destroyBanner(final JSONObject p0);
    
    void loadBanner(final IronSourceBannerLayout p0, final JSONObject p1, final BannerSmashListener p2);
    
    void reloadBanner(final JSONObject p0);
}
