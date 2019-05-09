// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren;

public interface PlayAdCallback
{
    void onAdEnd(final String p0, final boolean p1, final boolean p2);
    
    void onAdStart(final String p0);
    
    void onError(final String p0, final Throwable p1);
}
