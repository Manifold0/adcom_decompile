// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

public enum LogLevel
{
    ASSERT(7), 
    DEBUG(3), 
    ERROR(6), 
    INFO(4), 
    SUPRESS(8), 
    VERBOSE(2), 
    WARN(5);
    
    final int androidLogLevel;
    
    private LogLevel(final int androidLogLevel) {
        this.androidLogLevel = androidLogLevel;
    }
    
    public int getAndroidLogLevel() {
        return this.androidLogLevel;
    }
}
