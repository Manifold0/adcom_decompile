// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;

class cy implements AppLovinAdLoadListener
{
    final /* synthetic */ long a;
    final /* synthetic */ cp b;
    final /* synthetic */ ck c;
    final /* synthetic */ AppLovinAdLoadListener d;
    final /* synthetic */ MediationServiceImpl e;
    
    cy(final MediationServiceImpl e, final long a, final cp b, final ck c, final AppLovinAdLoadListener d) {
        this.e = e;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    @Override
    public void adReceived(final AppLovinAd appLovinAd) {
        final long n = System.currentTimeMillis() - this.a;
        synchronized (this.e.d) {
            this.e.f = this.b.a();
            this.e.e = n;
            // monitorexit(MediationServiceImpl.a(this.e))
            g.a(this.c, this.e.a);
            g.a(n, this.c, this.e.a);
            if (this.e.a.get(ea.dC)) {
                this.e.c.a(this.b);
            }
            this.e.a(appLovinAd, this.d);
        }
    }
    
    @Override
    public void failedToReceiveAd(final int n) {
        this.e.a(this.c, n, this.d);
    }
}
