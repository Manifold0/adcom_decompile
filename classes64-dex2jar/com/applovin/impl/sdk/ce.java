// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;

final class ce implements Runnable
{
    final /* synthetic */ AppLovinAdVideoPlaybackListener a;
    final /* synthetic */ AppLovinAd b;
    final /* synthetic */ AppLovinSdk c;
    
    ce(final AppLovinAdVideoPlaybackListener a, final AppLovinAd b, final AppLovinSdk c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    @Override
    public void run() {
        try {
            this.a.videoPlaybackBegan(b(this.b));
        }
        catch (Throwable t) {
            this.c.getLogger().userError("ListenerCallbackInvoker", "Unable to notify ad event listener about ad playback began", t);
        }
    }
}
