// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.HashSet;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdUpdateListener;
import java.util.Collection;
import com.applovin.sdk.AppLovinAd;

class v
{
    final Object a;
    AppLovinAd b;
    long c;
    boolean d;
    private final Collection<AppLovinAdUpdateListener> e;
    private final Collection<AppLovinAdLoadListener> f;
    
    private v() {
        this.a = new Object();
        this.e = new HashSet<AppLovinAdUpdateListener>();
        this.f = new HashSet<AppLovinAdLoadListener>();
    }
    
    @Override
    public String toString() {
        return "AdLoadState{loadedAd=" + this.b + ", loadedAdExpiration=" + this.c + ", isWaitingForAd=" + this.d + ", updateListeners=" + this.e + ", pendingAdListeners=" + this.f + '}';
    }
}
