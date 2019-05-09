package com.ironsource.mediationsdk.sdk;

import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import com.ironsource.mediationsdk.BannerSmash;
import com.ironsource.mediationsdk.logger.IronSourceError;

public interface BannerManagerListener {
    void onBannerAdClicked(BannerSmash bannerSmash);

    void onBannerAdLeftApplication(BannerSmash bannerSmash);

    void onBannerAdLoadFailed(IronSourceError ironSourceError, BannerSmash bannerSmash);

    void onBannerAdLoaded(BannerSmash bannerSmash, View view, LayoutParams layoutParams);

    void onBannerAdReloadFailed(IronSourceError ironSourceError, BannerSmash bannerSmash);

    void onBannerAdReloaded(BannerSmash bannerSmash);

    void onBannerAdScreenDismissed(BannerSmash bannerSmash);

    void onBannerAdScreenPresented(BannerSmash bannerSmash);
}
