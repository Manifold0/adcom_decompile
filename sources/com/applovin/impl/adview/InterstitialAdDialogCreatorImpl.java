package com.applovin.impl.adview;

import android.content.Context;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.adview.InterstitialAdDialogCreator;
import com.applovin.sdk.AppLovinSdk;
import java.lang.ref.WeakReference;

public class InterstitialAdDialogCreatorImpl implements InterstitialAdDialogCreator {
    /* renamed from: a */
    private static final Object f1708a = new Object();
    /* renamed from: b */
    private static WeakReference<cb> f1709b = new WeakReference(null);
    /* renamed from: c */
    private static WeakReference<Context> f1710c = new WeakReference(null);

    public AppLovinInterstitialAdDialog createInterstitialAdDialog(AppLovinSdk appLovinSdk, Context context) {
        AppLovinInterstitialAdDialog appLovinInterstitialAdDialog;
        if (appLovinSdk == null) {
            appLovinSdk = AppLovinSdk.getInstance(context);
        }
        synchronized (f1708a) {
            appLovinInterstitialAdDialog = (cb) f1709b.get();
            if (appLovinInterstitialAdDialog != null && appLovinInterstitialAdDialog.isShowing() && f1710c.get() == context) {
                appLovinSdk.getLogger().mo4178w("InterstitialAdDialogCreator", "An interstitial dialog is already showing, returning it");
            } else {
                appLovinInterstitialAdDialog = new cb(appLovinSdk, context);
                f1709b = new WeakReference(appLovinInterstitialAdDialog);
                f1710c = new WeakReference(context);
            }
        }
        return appLovinInterstitialAdDialog;
    }
}
