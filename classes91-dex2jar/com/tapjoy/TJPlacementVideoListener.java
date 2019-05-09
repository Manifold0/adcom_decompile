// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

public interface TJPlacementVideoListener
{
    void onVideoComplete(final TJPlacement p0);
    
    void onVideoError(final TJPlacement p0, final String p1);
    
    void onVideoStart(final TJPlacement p0);
}
