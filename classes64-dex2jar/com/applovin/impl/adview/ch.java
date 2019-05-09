// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.content.Context;
import com.applovin.sdk.AppLovinSdk;

public class ch extends ak
{
    private float c;
    private float d;
    
    public ch(final AppLovinSdk appLovinSdk, final Context context) {
        super(appLovinSdk, context);
        this.c = 30.0f;
        this.d = 1.0f;
    }
    
    public void a(final float d) {
        this.d = d;
    }
    
    @Override
    public void a(final int n) {
        this.a(n / this.c);
    }
}
