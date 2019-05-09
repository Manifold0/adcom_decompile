// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

import android.content.Context;

public interface IPackageHandler
{
    void addPackage(final ActivityPackage p0);
    
    void closeFirstPackage(final ResponseData p0, final ActivityPackage p1);
    
    void init(final IActivityHandler p0, final Context p1, final boolean p2);
    
    void pauseSending();
    
    void resumeSending();
    
    void sendFirstPackage();
    
    void sendNextPackage(final ResponseData p0);
    
    void teardown(final boolean p0);
    
    void updatePackages(final SessionParameters p0);
}
