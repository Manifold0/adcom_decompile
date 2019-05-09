package com.applovin.impl.adview;

import android.app.Activity;
import android.content.Context;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.adview.InterstitialAdDialogCreator;
import com.applovin.sdk.AppLovinSdk;
import java.lang.ref.WeakReference;

public class MediatedInterstitialAdDialogCreatorImpl implements InterstitialAdDialogCreator {
    /* renamed from: a */
    private static final Object f1711a = new Object();
    /* renamed from: b */
    private static WeakReference<ci> f1712b = new WeakReference(null);
    /* renamed from: c */
    private static WeakReference<Context> f1713c = new WeakReference(null);

    public AppLovinInterstitialAdDialog createInterstitialAdDialog(AppLovinSdk appLovinSdk, Context context) {
        if (context instanceof Activity) {
            AppLovinInterstitialAdDialog appLovinInterstitialAdDialog;
            if (appLovinSdk == null) {
                appLovinSdk = AppLovinSdk.getInstance(context);
            }
            synchronized (f1711a) {
                appLovinInterstitialAdDialog = (ci) f1712b.get();
                if (appLovinInterstitialAdDialog != null && appLovinInterstitialAdDialog.isShowing() && f1713c.get() == context) {
                    appLovinSdk.getLogger().mo4178w("InterstitialAdDialogCreator", "An interstitial dialog is already showing, returning it");
                } else {
                    ci ciVar = new ci(appLovinSdk, (Activity) context);
                    f1712b = new WeakReference(ciVar);
                    f1713c = new WeakReference(context);
                    appLovinInterstitialAdDialog = ciVar;
                }
            }
            return appLovinInterstitialAdDialog;
        }
        throw new IllegalArgumentException("Specified context is not an activity");
    }
}
