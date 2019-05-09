package com.applovin.adview;

import android.content.Context;
import com.applovin.sdk.AppLovinSdk;

public interface InterstitialAdDialogCreator {
    AppLovinInterstitialAdDialog createInterstitialAdDialog(AppLovinSdk appLovinSdk, Context context);
}
