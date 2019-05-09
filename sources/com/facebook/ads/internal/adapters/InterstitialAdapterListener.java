package com.facebook.ads.internal.adapters;

import com.facebook.ads.AdError;

public interface InterstitialAdapterListener {
    void onInterstitialActivityDestroyed();

    void onInterstitialAdClicked(C1918g c1918g, String str, boolean z);

    void onInterstitialAdDismissed(C1918g c1918g);

    void onInterstitialAdDisplayed(C1918g c1918g);

    void onInterstitialAdLoaded(C1918g c1918g);

    void onInterstitialError(C1918g c1918g, AdError adError);

    void onInterstitialLoggingImpression(C1918g c1918g);
}
