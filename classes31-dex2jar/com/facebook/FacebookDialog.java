// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook;

public interface FacebookDialog<CONTENT, RESULT>
{
    boolean canShow(final CONTENT p0);
    
    void registerCallback(final CallbackManager p0, final FacebookCallback<RESULT> p1);
    
    void registerCallback(final CallbackManager p0, final FacebookCallback<RESULT> p1, final int p2);
    
    void show(final CONTENT p0);
}
