// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

public interface IAttributionHandler
{
    void checkSdkClickResponse(final SdkClickResponseData p0);
    
    void checkSessionResponse(final SessionResponseData p0);
    
    void getAttribution();
    
    void init(final IActivityHandler p0, final ActivityPackage p1, final boolean p2);
    
    void pauseSending();
    
    void resumeSending();
    
    void teardown();
}
