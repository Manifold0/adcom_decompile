// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinSdk;
import com.applovin.mediation.AppLovinMediationAdapterConfig;
import java.util.Map;

class cq implements Runnable
{
    final /* synthetic */ Map a;
    final /* synthetic */ cp b;
    
    cq(final cp b, final Map a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public void run() {
        try {
            this.b.e.a(this.a);
            this.b.b.initialize(this.b.e, this.b.c, this.b.c.getInitializationActivity());
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
