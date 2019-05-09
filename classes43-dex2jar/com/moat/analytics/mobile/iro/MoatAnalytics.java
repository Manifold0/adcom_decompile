// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.iro;

import android.app.Application;
import android.support.annotation.UiThread;

public abstract class MoatAnalytics
{
    private static MoatAnalytics \u02ce;
    
    static {
        MoatAnalytics.\u02ce = null;
    }
    
    public static MoatAnalytics getInstance() {
        synchronized (MoatAnalytics.class) {
            Label_0021: {
                if (MoatAnalytics.\u02ce != null) {
                    break Label_0021;
                }
                try {
                    MoatAnalytics.\u02ce = new j();
                    return MoatAnalytics.\u02ce;
                }
                catch (Exception ex) {
                    o.\u0971(ex);
                    MoatAnalytics.\u02ce = new NoOp.MoatAnalytics();
                }
            }
        }
    }
    
    @UiThread
    public abstract void prepareNativeDisplayTracking(final String p0);
    
    public abstract void start(final Application p0);
    
    public abstract void start(final MoatOptions p0, final Application p1);
}
