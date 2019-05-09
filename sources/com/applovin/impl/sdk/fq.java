package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinSdkUtils;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.facebook.share.internal.ShareConstants;
import com.tapjoy.TapjoyConstants;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

class fq extends dx {
    /* renamed from: a */
    private final AppLovinNativeAdLoadListener f2530a;
    /* renamed from: b */
    private final JSONObject f2531b;

    fq(JSONObject jSONObject, AppLovinSdkImpl appLovinSdkImpl, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        super("TaskRenderNativeAd", appLovinSdkImpl);
        this.f2530a = appLovinNativeAdLoadListener;
        this.f2531b = jSONObject;
    }

    /* renamed from: a */
    private String m2875a(Map<String, String> map, String str) {
        String str2 = (String) map.get("simp_url");
        if (AppLovinSdkUtils.isValidString(str2)) {
            return str2.replace("{CLCODE}", str);
        }
        throw new IllegalArgumentException("No impression URL available");
    }

    /* renamed from: a */
    private String m2876a(Map<String, String> map, String str, String str2) {
        String str3 = (String) map.get(TapjoyConstants.TJC_CLICK_URL);
        if (AppLovinSdkUtils.isValidString(str3)) {
            CharSequence charSequence;
            if (str2 == null) {
                charSequence = "";
            }
            return str3.replace("{CLCODE}", str).replace("{EVENT_ID}", charSequence);
        }
        throw new IllegalArgumentException("No impression URL available");
    }

    /* renamed from: a */
    private void m2877a(JSONObject jSONObject) throws JSONException, MalformedURLException {
        List<Map> a = bu.m2390a(jSONObject.getJSONArray("native_ads"));
        Map a2 = bu.m2391a(jSONObject.getJSONObject("native_settings"));
        List arrayList = new ArrayList(a.size());
        for (Map map : a) {
            String str = (String) map.get("clcode");
            String a3 = bu.m2389a(jSONObject, "zone_id", null, this.d);
            String str2 = (String) map.get("resource_cache_prefix");
            NativeAdImpl a4 = new dc().m2511a(C1287n.m3040b(a3, this.d)).m2517e(a3).m2518f((String) map.get("title")).m2519g((String) map.get("description")).m2520h((String) map.get(ShareConstants.FEED_CAPTION_PARAM)).m2529q((String) map.get("cta")).m2512a((String) map.get("icon_url")).m2514b((String) map.get(MessengerShareContentUtility.IMAGE_URL)).m2516d((String) map.get(TapjoyConstants.TJC_VIDEO_URL)).m2515c((String) map.get("star_rating_url")).m2521i((String) map.get("icon_url")).m2522j((String) map.get(MessengerShareContentUtility.IMAGE_URL)).m2523k((String) map.get(TapjoyConstants.TJC_VIDEO_URL)).m2508a(Float.parseFloat((String) map.get("star_rating"))).m2528p(str).m2524l(m2875a(a2, str)).m2525m(m2876a(a2, str, (String) map.get("event_id"))).m2526n(m2878b(a2, str)).m2527o(m2879c(a2, str)).m2509a(Long.parseLong((String) map.get("ad_id"))).m2513a(str2 != null ? aa.m2193a(str2) : this.d.getAsList(ea.f2389M)).m2510a(this.d).m2507a();
            arrayList.add(a4);
            this.d.getLogger().mo4172d("TaskRenderNativeAd", "Prepared native ad: " + a4.getAdId());
        }
        if (this.f2530a != null) {
            this.f2530a.onNativeAdsLoaded(arrayList);
        }
    }

    /* renamed from: b */
    private String m2878b(Map<String, String> map, String str) {
        String str2 = (String) map.get("video_start_url");
        return str2 != null ? str2.replace("{CLCODE}", str) : null;
    }

    /* renamed from: c */
    private String m2879c(Map<String, String> map, String str) {
        String str2 = (String) map.get("video_end_url");
        return str2 != null ? str2.replace("{CLCODE}", str) : null;
    }

    /* renamed from: a */
    void m2880a(int i) {
        try {
            if (this.f2530a != null) {
                this.f2530a.onNativeAdsFailedToLoad(i);
            }
        } catch (Throwable e) {
            this.d.getLogger().mo4174e("TaskRenderNativeAd", "Unable to notify listener about failure.", e);
        }
    }

    public void run() {
        try {
            if (this.f2531b == null || this.f2531b.length() == 0) {
                m2880a((int) AppLovinErrorCodes.UNABLE_TO_PREPARE_NATIVE_AD);
            } else {
                m2877a(this.f2531b);
            }
        } catch (Throwable e) {
            this.d.getLogger().mo4174e("TaskRenderNativeAd", "Unable to render widget.", e);
            m2880a((int) AppLovinErrorCodes.UNABLE_TO_PRECACHE_RESOURCES);
        }
    }
}
