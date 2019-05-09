// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

import com.facebook.ads.internal.view.i.a.a;

public enum VideoStartReason
{
    AUTO_STARTED(com.facebook.ads.internal.view.i.a.a.c), 
    NOT_STARTED(com.facebook.ads.internal.view.i.a.a.a), 
    USER_STARTED(com.facebook.ads.internal.view.i.a.a.b);
    
    private final a a;
    
    private VideoStartReason(final a a) {
        this.a = a;
    }
    
    a a() {
        return this.a;
    }
}
