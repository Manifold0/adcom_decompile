// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

public interface TJActionRequest
{
    void cancelled();
    
    void completed();
    
    String getRequestId();
    
    String getToken();
}
