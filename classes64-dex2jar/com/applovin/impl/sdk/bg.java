// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import java.util.Timer;
import com.applovin.sdk.AppLovinAdRewardListener;
import android.app.Activity;

class bg
{
    private final AppLovinSdkImpl a;
    private final ax b;
    private final Activity c;
    private final Runnable d;
    private final AppLovinAdRewardListener e;
    private final Timer f;
    
    private bg(final bl bl) {
        this.a = bl.a;
        this.b = bl.b;
        this.c = bl.c;
        this.d = bl.e;
        this.e = bl.d;
        this.f = new Timer("IncentivizedAdLauncher");
    }
    
    static bl a() {
        return new bl(null);
    }
    
    void a(final AppLovinAd appLovinAd) {
        this.c.runOnUiThread((Runnable)new bh(this, appLovinAd));
    }
}
