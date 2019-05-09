// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAdRewardListener;
import android.app.Activity;

class bl
{
    private AppLovinSdkImpl a;
    private ax b;
    private Activity c;
    private AppLovinAdRewardListener d;
    private Runnable e;
    
    private bl() {
    }
    
    bg a() {
        return new bg(this, null);
    }
    
    bl a(final Activity c) {
        this.c = c;
        return this;
    }
    
    bl a(final AppLovinSdkImpl a) {
        this.a = a;
        return this;
    }
    
    bl a(final ax b) {
        this.b = b;
        return this;
    }
    
    bl a(final AppLovinAdRewardListener d) {
        this.d = d;
        return this;
    }
    
    bl a(final Runnable e) {
        this.e = e;
        return this;
    }
}
