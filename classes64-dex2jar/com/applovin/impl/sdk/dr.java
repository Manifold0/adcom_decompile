// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinPostbackListener;

class dr implements AppLovinPostbackListener
{
    final /* synthetic */ AppLovinPostbackListener a;
    final /* synthetic */ PostbackServiceImpl b;
    
    dr(final PostbackServiceImpl b, final AppLovinPostbackListener a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public void onPostbackFailure(final String s, final int n) {
        this.b.a.getLogger().e("PostbackService", "Failed to dispatch postback. Error code: " + n + " URL: " + s);
        if (this.a != null) {
            this.a.onPostbackFailure(s, n);
        }
    }
    
    @Override
    public void onPostbackSuccess(final String s) {
        this.b.a.getLogger().d("PostbackService", "Successfully dispatched postback to URL: " + s);
        if (this.a != null) {
            this.a.onPostbackSuccess(s);
        }
    }
}
