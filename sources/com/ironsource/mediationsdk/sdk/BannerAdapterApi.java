package com.ironsource.mediationsdk.sdk;

import com.ironsource.mediationsdk.IronSourceBannerLayout;
import org.json.JSONObject;

public interface BannerAdapterApi {
    void destroyBanner(JSONObject jSONObject);

    void loadBanner(IronSourceBannerLayout ironSourceBannerLayout, JSONObject jSONObject, BannerSmashListener bannerSmashListener);

    void reloadBanner(JSONObject jSONObject);
}
