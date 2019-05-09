// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.adview;

import com.applovin.impl.adview.InterstitialAdDialogCreatorImpl;
import android.content.Context;
import com.applovin.sdk.AppLovinSdk;

final class b implements Runnable
{
    final /* synthetic */ AppLovinSdk a;
    final /* synthetic */ Context b;
    final /* synthetic */ String c;
    
    b(final AppLovinSdk a, final Context b, final String c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    @Override
    public void run() {
        new InterstitialAdDialogCreatorImpl().createInterstitialAdDialog(this.a, this.b).show(this.c);
    }
}
