// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import android.view.WindowManager;
import android.util.DisplayMetrics;
import android.content.res.Configuration;
import android.content.Context;

public class TapjoyDisplayMetricsUtil
{
    private Context a;
    private Configuration b;
    private DisplayMetrics c;
    
    public TapjoyDisplayMetricsUtil(final Context a) {
        this.a = a;
        this.c = new DisplayMetrics();
        ((WindowManager)this.a.getSystemService("window")).getDefaultDisplay().getMetrics(this.c);
        this.b = this.a.getResources().getConfiguration();
    }
    
    public int getScreenDensityDPI() {
        return this.c.densityDpi;
    }
    
    public float getScreenDensityScale() {
        return this.c.density;
    }
    
    public int getScreenLayoutSize() {
        return this.b.screenLayout & 0xF;
    }
}
