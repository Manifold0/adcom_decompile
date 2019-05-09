// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.net.MalformedURLException;
import org.json.JSONException;
import java.util.Iterator;
import com.applovin.nativeAds.AppLovinNativeAd;
import java.util.List;
import com.applovin.sdk.AppLovinSdk;
import java.util.ArrayList;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Map;
import org.json.JSONObject;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;

class fq extends dx
{
    private final AppLovinNativeAdLoadListener a;
    private final JSONObject b;
    
    fq(final JSONObject b, final AppLovinSdkImpl appLovinSdkImpl, final AppLovinNativeAdLoadListener a) {
        super("TaskRenderNativeAd", appLovinSdkImpl);
        this.a = a;
        this.b = b;
    }
    
    private String a(final Map<String, String> map, final String s) {
        final String s2 = map.get("simp_url");
        if (!AppLovinSdkUtils.isValidString(s2)) {
            throw new IllegalArgumentException("No impression URL available");
        }
        return s2.replace("{CLCODE}", s);
    }
    
    private String a(final Map<String, String> map, final String s, final String s2) {
        final String s3 = map.get("click_url");
        if (!AppLovinSdkUtils.isValidString(s3)) {
            throw new IllegalArgumentException("No impression URL available");
        }
        String s4;
        if ((s4 = s2) == null) {
            s4 = "";
        }
        return s3.replace("{CLCODE}", s).replace("{EVENT_ID}", s4);
    }
    
    private void a(final JSONObject jsonObject) throws JSONException, MalformedURLException {
        final List a = bu.a(jsonObject.getJSONArray("native_ads"));
        final Map<String, String> a2 = bu.a(jsonObject.getJSONObject("native_settings"));
        final ArrayList list = new ArrayList<NativeAdImpl>(a.size());
        for (final Map<K, String> map : a) {
            final String s = map.get("clcode");
            final String a3 = bu.a(jsonObject, "zone_id", (String)null, this.d);
            final n b = n.b(a3, this.d);
            final String s2 = map.get("resource_cache_prefix");
            List<String> list2;
            if (s2 != null) {
                list2 = aa.a(s2);
            }
            else {
                list2 = this.d.getAsList(ea.M);
            }
            final NativeAdImpl a4 = new dc().a(b).e(a3).f(map.get("title")).g(map.get("description")).h(map.get("caption")).q(map.get("cta")).a(map.get("icon_url")).b(map.get("image_url")).d(map.get("video_url")).c(map.get("star_rating_url")).i(map.get("icon_url")).j(map.get("image_url")).k(map.get("video_url")).a(Float.parseFloat(map.get("star_rating"))).p(s).l(this.a(a2, s)).m(this.a(a2, s, map.get("event_id"))).n(this.b(a2, s)).o(this.c(a2, s)).a(Long.parseLong(map.get("ad_id"))).a(list2).a(this.d).a();
            list.add(a4);
            this.d.getLogger().d("TaskRenderNativeAd", "Prepared native ad: " + a4.getAdId());
        }
        if (this.a != null) {
            this.a.onNativeAdsLoaded((List<AppLovinNativeAd>)list);
        }
    }
    
    private String b(final Map<String, String> map, final String s) {
        final String s2 = map.get("video_start_url");
        if (s2 != null) {
            return s2.replace("{CLCODE}", s);
        }
        return null;
    }
    
    private String c(final Map<String, String> map, final String s) {
        final String s2 = map.get("video_end_url");
        if (s2 != null) {
            return s2.replace("{CLCODE}", s);
        }
        return null;
    }
    
    void a(final int n) {
        try {
            if (this.a != null) {
                this.a.onNativeAdsFailedToLoad(n);
            }
        }
        catch (Exception ex) {
            this.d.getLogger().e("TaskRenderNativeAd", "Unable to notify listener about failure.", ex);
        }
    }
    
    @Override
    public void run() {
        try {
            if (this.b == null || this.b.length() == 0) {
                this.a(-700);
                return;
            }
            this.a(this.b);
        }
        catch (Exception ex) {
            this.d.getLogger().e("TaskRenderNativeAd", "Unable to render widget.", ex);
            this.a(-200);
        }
    }
}
