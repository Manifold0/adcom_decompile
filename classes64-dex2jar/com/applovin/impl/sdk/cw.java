// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.concurrent.atomic.AtomicBoolean;
import com.applovin.sdk.AppLovinAdLoadListener;

class cw
{
    private final ck a;
    private final AppLovinAdLoadListener b;
    private final AtomicBoolean c;
    
    cw(final ck a, final AppLovinAdLoadListener b) {
        this.b = b;
        this.a = a;
        this.c = new AtomicBoolean();
    }
}
