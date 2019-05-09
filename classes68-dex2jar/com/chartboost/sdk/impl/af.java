// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import com.chartboost.sdk.Model.CBError;

public class af<T>
{
    public final T a;
    public final CBError b;
    
    private af(final T a, final CBError b) {
        this.a = a;
        this.b = b;
    }
    
    public static <T> af<T> a(final CBError cbError) {
        return new af<T>(null, cbError);
    }
    
    public static <T> af<T> a(final T t) {
        return new af<T>(t, null);
    }
}
