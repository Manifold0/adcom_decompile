// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinSdk;
import android.content.Context;
import java.lang.ref.WeakReference;
import com.applovin.adview.InterstitialAdDialogCreator;

public class InterstitialAdDialogCreatorImpl implements InterstitialAdDialogCreator
{
    private static final Object a;
    private static WeakReference<cb> b;
    private static WeakReference<Context> c;
    
    static {
        a = new Object();
        InterstitialAdDialogCreatorImpl.b = new WeakReference<cb>(null);
        InterstitialAdDialogCreatorImpl.c = new WeakReference<Context>(null);
    }
    
    @Override
    public AppLovinInterstitialAdDialog createInterstitialAdDialog(final AppLovinSdk appLovinSdk, final Context context) {
        AppLovinSdk instance = appLovinSdk;
        if (appLovinSdk == null) {
            instance = AppLovinSdk.getInstance(context);
        }
        synchronized (InterstitialAdDialogCreatorImpl.a) {
            cb cb = InterstitialAdDialogCreatorImpl.b.get();
            if (cb == null || !cb.isShowing() || InterstitialAdDialogCreatorImpl.c.get() != context) {
                cb = new cb(instance, context);
                InterstitialAdDialogCreatorImpl.b = new WeakReference<cb>(cb);
                InterstitialAdDialogCreatorImpl.c = new WeakReference<Context>(context);
            }
            else {
                instance.getLogger().w("InterstitialAdDialogCreator", "An interstitial dialog is already showing, returning it");
            }
            return cb;
        }
    }
}
