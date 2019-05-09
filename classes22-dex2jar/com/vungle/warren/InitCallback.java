// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren;

public interface InitCallback
{
    void onAutoCacheAdAvailable(final String p0);
    
    void onError(final Throwable p0);
    
    void onSuccess();
}
