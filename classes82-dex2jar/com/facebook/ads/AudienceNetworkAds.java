// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

import android.text.TextUtils;
import com.facebook.ads.internal.n.a;
import com.facebook.ads.internal.w.b.l;
import android.content.Context;

public final class AudienceNetworkAds
{
    public static final String TAG = "FBAudienceNetwork";
    
    private AudienceNetworkAds() {
    }
    
    public static void initialize(final Context context) {
        l.a(context, "Context can not be null.");
        a.a(context);
    }
    
    public static boolean isInAdsProcess(final Context context) {
        l.a(context, "Context can not be null.");
        com.facebook.ads.internal.w.f.a.a = true;
        final String a = com.facebook.ads.internal.w.f.a.a(context);
        return !TextUtils.isEmpty((CharSequence)a) && a.endsWith(":adnw");
    }
}
