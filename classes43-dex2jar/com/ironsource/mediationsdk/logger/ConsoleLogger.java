// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.logger;

import android.util.Log;

public class ConsoleLogger extends IronSourceLogger
{
    public static final String NAME = "console";
    
    private ConsoleLogger() {
        super("console");
    }
    
    public ConsoleLogger(final int n) {
        super("console", n);
    }
    
    @Override
    public void log(final IronSourceTag ironSourceTag, final String s, final int n) {
        switch (n) {
            default: {}
            case 0: {
                Log.v("" + ironSourceTag, s);
            }
            case 1: {
                Log.i("" + ironSourceTag, s);
            }
            case 2: {
                Log.w("" + ironSourceTag, s);
            }
            case 3: {
                Log.e("" + ironSourceTag, s);
            }
        }
    }
    
    @Override
    public void logException(final IronSourceTag ironSourceTag, final String s, final Throwable t) {
        this.log(ironSourceTag, s + ":stacktrace[" + Log.getStackTraceString(t) + "]", 3);
    }
}
