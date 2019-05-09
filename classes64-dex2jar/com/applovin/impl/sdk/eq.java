// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

public class eq extends dx
{
    private final er a;
    
    public eq(final AppLovinSdkImpl appLovinSdkImpl, final er a) {
        super("TaskCollectAdvertisingId", appLovinSdkImpl);
        this.a = a;
    }
    
    @Override
    public void run() {
        this.a.a(this.d.getDataCollector().e());
    }
}
