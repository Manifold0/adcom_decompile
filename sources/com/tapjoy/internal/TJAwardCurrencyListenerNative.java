package com.tapjoy.internal;

import com.tapjoy.TJAwardCurrencyListener;

public class TJAwardCurrencyListenerNative implements TJAwardCurrencyListener {
    /* renamed from: a */
    private final long f7106a;

    private static native void onAwardCurrencyResponseFailureNative(long j, String str);

    private static native void onAwardCurrencyResponseNative(long j, String str, int i);

    private TJAwardCurrencyListenerNative(long nativeHandle) {
        if (nativeHandle == 0) {
            throw new IllegalArgumentException();
        }
        this.f7106a = nativeHandle;
    }

    public void onAwardCurrencyResponse(String currencyName, int balance) {
        onAwardCurrencyResponseNative(this.f7106a, currencyName, balance);
    }

    public void onAwardCurrencyResponseFailure(String error) {
        onAwardCurrencyResponseFailureNative(this.f7106a, error);
    }

    @ew
    static Object create(long nativeHandle) {
        return new TJAwardCurrencyListenerNative(nativeHandle);
    }
}
