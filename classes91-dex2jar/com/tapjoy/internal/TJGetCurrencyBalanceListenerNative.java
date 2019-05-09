// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import com.tapjoy.TJGetCurrencyBalanceListener;

public class TJGetCurrencyBalanceListenerNative implements TJGetCurrencyBalanceListener
{
    private final long a;
    
    private TJGetCurrencyBalanceListenerNative(final long a) {
        if (a == 0L) {
            throw new IllegalArgumentException();
        }
        this.a = a;
    }
    
    @ew
    static Object create(final long n) {
        return new TJGetCurrencyBalanceListenerNative(n);
    }
    
    private static native void onGetCurrencyBalanceResponseFailureNative(final long p0, final String p1);
    
    private static native void onGetCurrencyBalanceResponseNative(final long p0, final String p1, final int p2);
    
    @Override
    public void onGetCurrencyBalanceResponse(final String s, final int n) {
        onGetCurrencyBalanceResponseNative(this.a, s, n);
    }
    
    @Override
    public void onGetCurrencyBalanceResponseFailure(final String s) {
        onGetCurrencyBalanceResponseFailureNative(this.a, s);
    }
}
