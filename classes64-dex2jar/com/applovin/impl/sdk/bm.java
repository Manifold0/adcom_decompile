// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import android.app.AlertDialog;
import com.applovin.impl.adview.az;

public class bm
{
    private final AppLovinSdkImpl a;
    private final az b;
    private AlertDialog c;
    
    public bm(final az b, final AppLovinSdkImpl a) {
        this.a = a;
        this.b = b;
    }
    
    public void a() {
        this.b.runOnUiThread((Runnable)new bn(this));
    }
    
    public void b() {
        this.b.runOnUiThread((Runnable)new bo(this));
    }
    
    public void c() {
        this.b.runOnUiThread((Runnable)new br(this));
    }
    
    public boolean d() {
        return this.c != null && this.c.isShowing();
    }
}
