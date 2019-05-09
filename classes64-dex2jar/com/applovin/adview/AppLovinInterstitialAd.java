// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.adview;

import com.applovin.sdk.AppLovinSdkUtils;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.impl.adview.InterstitialAdDialogCreatorImpl;
import com.applovin.sdk.AppLovinSdk;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;

public class AppLovinInterstitialAd extends View
{
    private AppLovinInterstitialAdDialog a;
    
    public AppLovinInterstitialAd(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public AppLovinInterstitialAd(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.a = null;
        final AppLovinSdk instance = AppLovinSdk.getInstance(context);
        if (instance != null && !instance.hasCriticalErrors()) {
            this.a = new InterstitialAdDialogCreatorImpl().createInterstitialAdDialog(instance, context);
        }
        this.setVisibility(8);
    }
    
    public static AppLovinInterstitialAdDialog create(final AppLovinSdk appLovinSdk, final Context context) {
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        if (context == null) {
            throw new IllegalArgumentException("No context specified");
        }
        return new InterstitialAdDialogCreatorImpl().createInterstitialAdDialog(appLovinSdk, context);
    }
    
    @Deprecated
    public static boolean isAdReadyToDisplay(final Context context) {
        return AppLovinSdk.getInstance(context).getAdService().hasPreloadedAd(AppLovinAdSize.INTERSTITIAL);
    }
    
    public static void show(final Context context) {
        show(context, null);
    }
    
    @Deprecated
    public static void show(final Context context, final String s) {
        if (context == null) {
            throw new IllegalArgumentException("No context specified");
        }
        final AppLovinSdk instance = AppLovinSdk.getInstance(context);
        if (instance != null && !instance.hasCriticalErrors()) {
            show(instance, context, s);
        }
    }
    
    @Deprecated
    public static void show(final AppLovinSdk appLovinSdk, final Context context, final String s) {
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        if (context == null) {
            throw new IllegalArgumentException("No context specified");
        }
        AppLovinSdkUtils.runOnUiThread(new b(appLovinSdk, context, s));
    }
    
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.a != null) {
            this.a.show();
        }
    }
}
