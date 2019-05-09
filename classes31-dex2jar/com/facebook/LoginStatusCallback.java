// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook;

public interface LoginStatusCallback
{
    void onCompleted(final AccessToken p0);
    
    void onError(final Exception p0);
    
    void onFailure();
}
