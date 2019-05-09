// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook;

public interface FacebookCallback<RESULT>
{
    void onCancel();
    
    void onError(final FacebookException p0);
    
    void onSuccess(final RESULT p0);
}
