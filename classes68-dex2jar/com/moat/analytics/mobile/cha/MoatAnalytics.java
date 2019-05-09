// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.cha;

import android.app.Application;
import android.support.annotation.UiThread;

public abstract class MoatAnalytics
{
    private static MoatAnalytics \u02cf;
    
    static {
        MoatAnalytics.\u02cf = null;
    }
    
    public static MoatAnalytics getInstance() {
        synchronized (MoatAnalytics.class) {
            Label_0021: {
                if (MoatAnalytics.\u02cf != null) {
                    break Label_0021;
                }
                try {
                    MoatAnalytics.\u02cf = new f();
                    return MoatAnalytics.\u02cf;
                }
                catch (Exception ex) {
                    o.\u02ce(ex);
                    MoatAnalytics.\u02cf = new NoOp.MoatAnalytics();
                }
            }
        }
    }
    
    @UiThread
    public abstract void prepareNativeDisplayTracking(final String p0);
    
    public abstract void start(final Application p0);
    
    public abstract void start(final MoatOptions p0, final Application p1);
}
