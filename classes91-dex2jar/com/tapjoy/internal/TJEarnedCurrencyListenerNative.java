// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import com.tapjoy.TJEarnedCurrencyListener;

public class TJEarnedCurrencyListenerNative implements TJEarnedCurrencyListener
{
    private final long a;
    
    private TJEarnedCurrencyListenerNative(final long a) {
        if (a == 0L) {
            throw new IllegalArgumentException();
        }
        this.a = a;
    }
    
    @ew
    static Object create(final long n) {
        return new TJEarnedCurrencyListenerNative(n);
    }
    
    private static native void onEarnedCurrencyNative(final long p0, final String p1, final int p2);
    
    @Override
    public void onEarnedCurrency(final String s, final int n) {
        onEarnedCurrencyNative(this.a, s, n);
    }
}
