// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.app.Activity;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinSdk;
import android.content.Context;
import java.lang.ref.WeakReference;
import com.applovin.adview.InterstitialAdDialogCreator;

public class MediatedInterstitialAdDialogCreatorImpl implements InterstitialAdDialogCreator
{
    private static final Object a;
    private static WeakReference<ci> b;
    private static WeakReference<Context> c;
    
    static {
        a = new Object();
        MediatedInterstitialAdDialogCreatorImpl.b = new WeakReference<ci>(null);
        MediatedInterstitialAdDialogCreatorImpl.c = new WeakReference<Context>(null);
    }
    
    @Override
    public AppLovinInterstitialAdDialog createInterstitialAdDialog(final AppLovinSdk appLovinSdk, final Context context) {
        if (!(context instanceof Activity)) {
            throw new IllegalArgumentException("Specified context is not an activity");
        }
        AppLovinSdk instance;
        if ((instance = appLovinSdk) == null) {
            instance = AppLovinSdk.getInstance(context);
        }
        synchronized (MediatedInterstitialAdDialogCreatorImpl.a) {
            ci ci = MediatedInterstitialAdDialogCreatorImpl.b.get();
            if (ci == null || !ci.isShowing() || MediatedInterstitialAdDialogCreatorImpl.c.get() != context) {
                ci = new ci(instance, (Activity)context);
                MediatedInterstitialAdDialogCreatorImpl.b = new WeakReference<ci>(ci);
                MediatedInterstitialAdDialogCreatorImpl.c = new WeakReference<Context>(context);
            }
            else {
                instance.getLogger().w("InterstitialAdDialogCreator", "An interstitial dialog is already showing, returning it");
            }
            return ci;
        }
    }
}
