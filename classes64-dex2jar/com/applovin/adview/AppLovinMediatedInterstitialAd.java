// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.adview;

import com.applovin.sdk.AppLovinAdSize;
import com.applovin.impl.adview.MediatedInterstitialAdDialogCreatorImpl;
import com.applovin.impl.adview.InterstitialAdDialogCreatorImpl;
import com.applovin.sdk.AppLovinSdk;
import android.content.Context;
import android.util.AttributeSet;
import android.app.Activity;
import android.view.View;

public class AppLovinMediatedInterstitialAd extends View
{
    private AppLovinInterstitialAdDialog a;
    
    public AppLovinMediatedInterstitialAd(final Activity activity, final AttributeSet set) {
        this(activity, set, 0);
    }
    
    public AppLovinMediatedInterstitialAd(final Activity activity, final AttributeSet set, final int n) {
        super((Context)activity, set, n);
        this.a = null;
        final AppLovinSdk instance = AppLovinSdk.getInstance((Context)activity);
        if (instance != null && !instance.hasCriticalErrors()) {
            this.a = new InterstitialAdDialogCreatorImpl().createInterstitialAdDialog(instance, (Context)activity);
        }
        this.setVisibility(8);
    }
    
    public static AppLovinInterstitialAdDialog create(final AppLovinSdk appLovinSdk, final Activity activity) {
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        }
        return new MediatedInterstitialAdDialogCreatorImpl().createInterstitialAdDialog(appLovinSdk, (Context)activity);
    }
    
    public static boolean isAdReadyToDisplay(final Activity activity) {
        return AppLovinSdk.getInstance((Context)activity).getAdService().hasPreloadedAd(AppLovinAdSize.INTERSTITIAL);
    }
    
    public static void show(final Activity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        }
        final AppLovinSdk instance = AppLovinSdk.getInstance((Context)activity);
        if (instance != null && !instance.hasCriticalErrors()) {
            show(instance, activity);
        }
    }
    
    @Deprecated
    public static void show(final AppLovinSdk appLovinSdk, final Activity activity) {
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        }
        activity.runOnUiThread((Runnable)new c(appLovinSdk, activity));
    }
    
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.a != null) {
            this.a.show();
        }
    }
}
