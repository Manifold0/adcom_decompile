// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinPostbackListener;

class dp implements AppLovinPostbackListener
{
    final /* synthetic */ dq a;
    final /* synthetic */ do b;
    
    dp(final do b, final dq a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public void onPostbackFailure(final String s, final int n) {
        this.b.b.i("PersistentPostbackManager", "Failed to submit postback with errorCode " + n + ". Will retry later...  Postback: " + this.a);
        this.b.e(this.a);
    }
    
    @Override
    public void onPostbackSuccess(final String s) {
        this.b.d(this.a);
        this.b.b.d("PersistentPostbackManager", "Successfully submitted postback: " + this.a);
        this.b.b();
    }
}
