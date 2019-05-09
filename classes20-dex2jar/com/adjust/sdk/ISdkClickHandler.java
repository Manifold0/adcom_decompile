// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

public interface ISdkClickHandler
{
    void init(final IActivityHandler p0, final boolean p1);
    
    void pauseSending();
    
    void resumeSending();
    
    void sendReftagReferrers();
    
    void sendSdkClick(final ActivityPackage p0);
    
    void teardown();
}
