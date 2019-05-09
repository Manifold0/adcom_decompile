// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.adview;

import android.content.Context;
import com.applovin.impl.adview.MediatedInterstitialAdDialogCreatorImpl;
import android.app.Activity;
import com.applovin.sdk.AppLovinSdk;

final class c implements Runnable
{
    final /* synthetic */ AppLovinSdk a;
    final /* synthetic */ Activity b;
    
    c(final AppLovinSdk a, final Activity b) {
        this.a = a;
        this.b = b;
    }
    
    @Override
    public void run() {
        new MediatedInterstitialAdDialogCreatorImpl().createInterstitialAdDialog(this.a, (Context)this.b).show();
    }
}
