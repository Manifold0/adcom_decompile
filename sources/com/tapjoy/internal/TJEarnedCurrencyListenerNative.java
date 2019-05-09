package com.tapjoy.internal;

import com.tapjoy.TJEarnedCurrencyListener;

public class TJEarnedCurrencyListenerNative implements TJEarnedCurrencyListener {
    /* renamed from: a */
    private final long f7108a;

    private static native void onEarnedCurrencyNative(long j, String str, int i);

    private TJEarnedCurrencyListenerNative(long nativeHandle) {
        if (nativeHandle == 0) {
            throw new IllegalArgumentException();
        }
        this.f7108a = nativeHandle;
    }

    public void onEarnedCurrency(String currencyName, int amount) {
        onEarnedCurrencyNative(this.f7108a, currencyName, amount);
    }

    @ew
    static Object create(long nativeHandle) {
        return new TJEarnedCurrencyListenerNative(nativeHandle);
    }
}
