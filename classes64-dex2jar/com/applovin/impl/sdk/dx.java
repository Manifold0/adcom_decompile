// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import android.content.Context;
import com.applovin.sdk.AppLovinLogger;

abstract class dx implements Runnable
{
    final String c;
    protected final AppLovinSdkImpl d;
    final AppLovinLogger e;
    final Context f;
    protected boolean g;
    
    dx(String simpleName, final AppLovinSdkImpl d) {
        if (d == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.d = d;
        if (simpleName == null) {
            simpleName = this.getClass().getSimpleName();
        }
        this.c = simpleName;
        this.e = d.getLogger();
        this.f = d.getApplicationContext();
    }
    
    String a() {
        return this.c;
    }
}
