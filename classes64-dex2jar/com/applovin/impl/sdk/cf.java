// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;

final class cf implements Runnable
{
    final /* synthetic */ AppLovinAdVideoPlaybackListener a;
    final /* synthetic */ AppLovinAd b;
    final /* synthetic */ double c;
    final /* synthetic */ boolean d;
    final /* synthetic */ AppLovinSdk e;
    
    cf(final AppLovinAdVideoPlaybackListener a, final AppLovinAd b, final double c, final boolean d, final AppLovinSdk e) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    @Override
    public void run() {
        try {
            this.a.videoPlaybackEnded(b(this.b), this.c, this.d);
        }
        catch (Throwable t) {
            this.e.getLogger().userError("ListenerCallbackInvoker", "Unable to notify ad event listener about ad playback ended", t);
        }
    }
}
