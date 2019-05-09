package com.ironsource.mediationsdk.sdk;

import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import com.ironsource.mediationsdk.logger.IronSourceError;

public interface BannerSmashListener {
    void onBannerAdClicked();

    void onBannerAdLeftApplication();

    void onBannerAdLoadFailed(IronSourceError ironSourceError);

    void onBannerAdLoaded(View view, LayoutParams layoutParams);

    void onBannerAdScreenDismissed();

    void onBannerAdScreenPresented();

    void onBannerInitFailed(IronSourceError ironSourceError);

    void onBannerInitSuccess();
}
