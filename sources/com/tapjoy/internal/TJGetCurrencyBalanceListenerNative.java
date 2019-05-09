package com.tapjoy.internal;

import com.tapjoy.TJGetCurrencyBalanceListener;

public class TJGetCurrencyBalanceListenerNative implements TJGetCurrencyBalanceListener {
    /* renamed from: a */
    private final long f7109a;

    private static native void onGetCurrencyBalanceResponseFailureNative(long j, String str);

    private static native void onGetCurrencyBalanceResponseNative(long j, String str, int i);

    private TJGetCurrencyBalanceListenerNative(long nativeHandle) {
        if (nativeHandle == 0) {
            throw new IllegalArgumentException();
        }
        this.f7109a = nativeHandle;
    }

    public void onGetCurrencyBalanceResponse(String currencyName, int balance) {
        onGetCurrencyBalanceResponseNative(this.f7109a, currencyName, balance);
    }

    public void onGetCurrencyBalanceResponseFailure(String error) {
        onGetCurrencyBalanceResponseFailureNative(this.f7109a, error);
    }

    @ew
    static Object create(long nativeHandle) {
        return new TJGetCurrencyBalanceListenerNative(nativeHandle);
    }
}
