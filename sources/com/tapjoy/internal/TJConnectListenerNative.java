package com.tapjoy.internal;

import com.tapjoy.TJConnectListener;

public class TJConnectListenerNative implements TJConnectListener {
    /* renamed from: a */
    private final long f7107a;

    private static native void onConnectFailureNative(long j);

    private static native void onConnectSuccessNative(long j);

    private TJConnectListenerNative(long nativeHandle) {
        if (nativeHandle == 0) {
            throw new IllegalArgumentException();
        }
        this.f7107a = nativeHandle;
    }

    public void onConnectSuccess() {
        onConnectSuccessNative(this.f7107a);
    }

    public void onConnectFailure() {
        onConnectFailureNative(this.f7107a);
    }

    @ew
    static Object create(long nativeHandle) {
        return new TJConnectListenerNative(nativeHandle);
    }
}
