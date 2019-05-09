// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import com.tapjoy.TJSpendCurrencyListener;

public class TJSpendCurrencyListenerNative implements TJSpendCurrencyListener
{
    private final long a;
    
    private TJSpendCurrencyListenerNative(final long a) {
        if (a == 0L) {
            throw new IllegalArgumentException();
        }
        this.a = a;
    }
    
    @ew
    static Object create(final long n) {
        return new TJSpendCurrencyListenerNative(n);
    }
    
    private static native void onSpendCurrencyResponseFailureNative(final long p0, final String p1);
    
    private static native void onSpendCurrencyResponseNative(final long p0, final String p1, final int p2);
    
    @Override
    public void onSpendCurrencyResponse(final String s, final int n) {
        onSpendCurrencyResponseNative(this.a, s, n);
    }
    
    @Override
    public void onSpendCurrencyResponseFailure(final String s) {
        onSpendCurrencyResponseFailureNative(this.a, s);
    }
}
