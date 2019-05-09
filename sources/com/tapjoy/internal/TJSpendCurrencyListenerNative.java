package com.tapjoy.internal;

import com.tapjoy.TJSpendCurrencyListener;

public class TJSpendCurrencyListenerNative implements TJSpendCurrencyListener {
    /* renamed from: a */
    private final long f7111a;

    private static native void onSpendCurrencyResponseFailureNative(long j, String str);

    private static native void onSpendCurrencyResponseNative(long j, String str, int i);

    private TJSpendCurrencyListenerNative(long nativeHandle) {
        if (nativeHandle == 0) {
            throw new IllegalArgumentException();
        }
        this.f7111a = nativeHandle;
    }

    public void onSpendCurrencyResponse(String currencyName, int balance) {
        onSpendCurrencyResponseNative(this.f7111a, currencyName, balance);
    }

    public void onSpendCurrencyResponseFailure(String error) {
        onSpendCurrencyResponseFailureNative(this.f7111a, error);
    }

    @ew
    static Object create(long nativeHandle) {
        return new TJSpendCurrencyListenerNative(nativeHandle);
    }
}
