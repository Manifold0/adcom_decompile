// 
// Decompiled by Procyon v0.5.34
// 

package com.google.ads.mediation.customevent;

@Deprecated
public interface CustomEventListener
{
    void onDismissScreen();
    
    void onFailedToReceiveAd();
    
    void onLeaveApplication();
    
    void onPresentScreen();
}
