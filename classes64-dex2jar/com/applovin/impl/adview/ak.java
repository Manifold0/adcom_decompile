// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.content.Context;
import com.applovin.sdk.AppLovinSdk;
import android.view.View;

public abstract class ak extends View
{
    protected final AppLovinSdk a;
    protected final Context b;
    
    ak(final AppLovinSdk a, final Context b) {
        super(b);
        this.b = b;
        this.a = a;
    }
    
    public static ak a(final AppLovinSdk appLovinSdk, final Context context, final al al) {
        if (al.equals(al.c)) {
            return new ch(appLovinSdk, context);
        }
        if (al.equals(al.b)) {
            return new cj(appLovinSdk, context);
        }
        return new cq(appLovinSdk, context);
    }
    
    public abstract void a(final int p0);
}
