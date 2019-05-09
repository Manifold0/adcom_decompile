package com.facebook.ads;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.ads.internal.p025w.p026b.C2582l;
import com.facebook.ads.internal.p025w.p071f.C2616a;
import com.facebook.ads.internal.p045n.C2051a;

public final class AudienceNetworkAds {
    public static final String TAG = "FBAudienceNetwork";

    private AudienceNetworkAds() {
    }

    public static void initialize(Context context) {
        C2582l.m6647a(context, "Context can not be null.");
        C2051a.m4996a(context);
    }

    public static boolean isInAdsProcess(Context context) {
        C2582l.m6647a(context, "Context can not be null.");
        C2616a.f6479a = true;
        Object a = C2616a.m6729a(context);
        return !TextUtils.isEmpty(a) && a.endsWith(":adnw");
    }
}
