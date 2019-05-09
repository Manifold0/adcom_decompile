// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

public interface ILogger
{
    void Assert(final String p0, final Object... p1);
    
    void debug(final String p0, final Object... p1);
    
    void error(final String p0, final Object... p1);
    
    void info(final String p0, final Object... p1);
    
    void lockLogLevel();
    
    void setLogLevel(final LogLevel p0, final boolean p1);
    
    void setLogLevelString(final String p0, final boolean p1);
    
    void verbose(final String p0, final Object... p1);
    
    void warn(final String p0, final Object... p1);
    
    void warnInProduction(final String p0, final Object... p1);
}
