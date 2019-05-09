// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

import java.util.Locale;
import java.util.Arrays;
import android.util.Log;

public class Logger implements ILogger
{
    private static String formatErrorMessage;
    private boolean isProductionEnvironment;
    private LogLevel logLevel;
    private boolean logLevelLocked;
    
    static {
        Logger.formatErrorMessage = "Error formating log message: %s, with params: %s";
    }
    
    public Logger() {
        this.isProductionEnvironment = false;
        this.logLevelLocked = false;
        this.setLogLevel(LogLevel.INFO, this.isProductionEnvironment);
    }
    
    @Override
    public void Assert(final String s, final Object... array) {
        if (this.isProductionEnvironment || this.logLevel.androidLogLevel > 7) {
            return;
        }
        try {
            Log.println(7, "Adjust", Util.formatString(s, array));
        }
        catch (Exception ex) {
            Log.e("Adjust", Util.formatString(Logger.formatErrorMessage, s, Arrays.toString(array)));
        }
    }
    
    @Override
    public void debug(final String s, final Object... array) {
        if (this.isProductionEnvironment || this.logLevel.androidLogLevel > 3) {
            return;
        }
        try {
            Log.d("Adjust", Util.formatString(s, array));
        }
        catch (Exception ex) {
            Log.e("Adjust", Util.formatString(Logger.formatErrorMessage, s, Arrays.toString(array)));
        }
    }
    
    @Override
    public void error(final String s, final Object... array) {
        if (this.isProductionEnvironment || this.logLevel.androidLogLevel > 6) {
            return;
        }
        try {
            Log.e("Adjust", Util.formatString(s, array));
        }
        catch (Exception ex) {
            Log.e("Adjust", Util.formatString(Logger.formatErrorMessage, s, Arrays.toString(array)));
        }
    }
    
    @Override
    public void info(final String s, final Object... array) {
        if (this.isProductionEnvironment || this.logLevel.androidLogLevel > 4) {
            return;
        }
        try {
            Log.i("Adjust", Util.formatString(s, array));
        }
        catch (Exception ex) {
            Log.e("Adjust", Util.formatString(Logger.formatErrorMessage, s, Arrays.toString(array)));
        }
    }
    
    @Override
    public void lockLogLevel() {
        this.logLevelLocked = true;
    }
    
    @Override
    public void setLogLevel(final LogLevel logLevel, final boolean isProductionEnvironment) {
        if (this.logLevelLocked) {
            return;
        }
        this.logLevel = logLevel;
        this.isProductionEnvironment = isProductionEnvironment;
    }
    
    @Override
    public void setLogLevelString(final String s, final boolean b) {
        if (s == null) {
            return;
        }
        try {
            this.setLogLevel(LogLevel.valueOf(s.toUpperCase(Locale.US)), b);
        }
        catch (IllegalArgumentException ex) {
            this.error("Malformed logLevel '%s', falling back to 'info'", s);
        }
    }
    
    @Override
    public void verbose(final String s, final Object... array) {
        if (this.isProductionEnvironment || this.logLevel.androidLogLevel > 2) {
            return;
        }
        try {
            Log.v("Adjust", Util.formatString(s, array));
        }
        catch (Exception ex) {
            Log.e("Adjust", Util.formatString(Logger.formatErrorMessage, s, Arrays.toString(array)));
        }
    }
    
    @Override
    public void warn(final String s, final Object... array) {
        if (this.isProductionEnvironment || this.logLevel.androidLogLevel > 5) {
            return;
        }
        try {
            Log.w("Adjust", Util.formatString(s, array));
        }
        catch (Exception ex) {
            Log.e("Adjust", Util.formatString(Logger.formatErrorMessage, s, Arrays.toString(array)));
        }
    }
    
    @Override
    public void warnInProduction(final String s, final Object... array) {
        if (this.logLevel.androidLogLevel > 5) {
            return;
        }
        try {
            Log.w("Adjust", Util.formatString(s, array));
        }
        catch (Exception ex) {
            Log.e("Adjust", Util.formatString(Logger.formatErrorMessage, s, Arrays.toString(array)));
        }
    }
}
