// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.request;

public interface IResolveHostListener
{
    void onFailed(final String p0, final ResolveHostError p1, final String p2);
    
    void onResolve(final String p0, final String p1);
}
