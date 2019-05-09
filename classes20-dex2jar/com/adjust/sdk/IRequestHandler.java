// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

public interface IRequestHandler
{
    void init(final IPackageHandler p0);
    
    void sendPackage(final ActivityPackage p0, final int p1);
    
    void teardown();
}
