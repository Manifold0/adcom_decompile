package com.facebook.ads.internal.adapters;

import android.view.View;
import com.facebook.ads.AdError;

public interface BannerAdapterListener {
    void onBannerAdClicked(C1902e c1902e);

    void onBannerAdLoaded(C1902e c1902e, View view);

    void onBannerError(C1902e c1902e, AdError adError);

    void onBannerLoggingImpression(C1902e c1902e);
}
