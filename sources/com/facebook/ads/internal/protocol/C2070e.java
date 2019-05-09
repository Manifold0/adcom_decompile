package com.facebook.ads.internal.protocol;

import com.google.android.gms.nearby.messages.Strategy;
import com.tapjoy.TJAdUnitConstants;
import com.tonyodev.fetch.FetchConst;

/* renamed from: com.facebook.ads.internal.protocol.e */
public enum C2070e {
    UNKNOWN(0),
    WEBVIEW_BANNER_LEGACY(4),
    WEBVIEW_BANNER_50(5),
    WEBVIEW_BANNER_90(6),
    WEBVIEW_BANNER_250(7),
    WEBVIEW_INTERSTITIAL_UNKNOWN(100),
    WEBVIEW_INTERSTITIAL_HORIZONTAL(101),
    WEBVIEW_INTERSTITIAL_VERTICAL(102),
    WEBVIEW_INTERSTITIAL_TABLET(103),
    NATIVE_UNKNOWN(200),
    NATIVE_BANNER(TJAdUnitConstants.DEFAULT_VOLUME_CHECK_INTERVAL),
    NATIVE_250(FetchConst.NETWORK_WIFI),
    REWARDED_VIDEO(400),
    INSTREAM_VIDEO(Strategy.TTL_SECONDS_DEFAULT);
    
    /* renamed from: o */
    private final int f4677o;

    private C2070e(int i) {
        this.f4677o = i;
    }

    /* renamed from: a */
    public int m5047a() {
        return this.f4677o;
    }
}
