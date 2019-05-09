// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import com.tapjoy.TJAwardCurrencyListener;

public class TJAwardCurrencyListenerNative implements TJAwardCurrencyListener
{
    private final long a;
    
    private TJAwardCurrencyListenerNative(final long a) {
        if (a == 0L) {
            throw new IllegalArgumentException();
        }
        this.a = a;
    }
    
    @ew
    static Object create(final long n) {
        return new TJAwardCurrencyListenerNative(n);
    }
    
    private static native void onAwardCurrencyResponseFailureNative(final long p0, final String p1);
    
    private static native void onAwardCurrencyResponseNative(final long p0, final String p1, final int p2);
    
    @Override
    public void onAwardCurrencyResponse(final String s, final int n) {
        onAwardCurrencyResponseNative(this.a, s, n);
    }
    
    @Override
    public void onAwardCurrencyResponseFailure(final String s) {
        onAwardCurrencyResponseFailureNative(this.a, s);
    }
}
