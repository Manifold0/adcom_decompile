// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng;

import android.app.Application;

public abstract class MoatAnalytics
{
    private static MoatAnalytics a;
    
    static {
        MoatAnalytics.a = null;
    }
    
    public static MoatAnalytics getInstance() {
        synchronized (MoatAnalytics.class) {
            Label_0021: {
                if (MoatAnalytics.a != null) {
                    break Label_0021;
                }
                try {
                    MoatAnalytics.a = new k();
                    return MoatAnalytics.a;
                }
                catch (Exception ex) {
                    m.a(ex);
                    MoatAnalytics.a = new v.a();
                }
            }
        }
    }
    
    public abstract void prepareNativeDisplayTracking(final String p0);
    
    public abstract void start(final Application p0);
    
    public abstract void start(final MoatOptions p0, final Application p1);
}
