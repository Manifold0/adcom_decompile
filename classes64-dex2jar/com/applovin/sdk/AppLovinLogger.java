// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.sdk;

public interface AppLovinLogger
{
    public static final String SDK_TAG = "AppLovinSdk";
    
    void d(final String p0, final String p1);
    
    void e(final String p0, final String p1);
    
    void e(final String p0, final String p1, final Throwable p2);
    
    void i(final String p0, final String p1);
    
    void userError(final String p0, final String p1);
    
    void userError(final String p0, final String p1, final Throwable p2);
    
    void w(final String p0, final String p1);
    
    void w(final String p0, final String p1, final Throwable p2);
}
