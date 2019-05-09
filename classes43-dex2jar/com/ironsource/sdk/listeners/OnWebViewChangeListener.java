// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.listeners;

public interface OnWebViewChangeListener
{
    boolean onBackButtonPressed();
    
    void onCloseRequested();
    
    void onOrientationChanged(final String p0, final int p1);
}
