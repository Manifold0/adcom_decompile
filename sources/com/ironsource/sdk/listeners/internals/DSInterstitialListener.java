package com.ironsource.sdk.listeners.internals;

public interface DSInterstitialListener extends DSAdProductListener {
    void onInterstitialLoadFailed(String str, String str2);

    void onInterstitialLoadSuccess(String str);

    void onInterstitialShowFailed(String str, String str2);

    void onInterstitialShowSuccess(String str);
}
