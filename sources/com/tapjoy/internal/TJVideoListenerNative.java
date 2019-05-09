package com.tapjoy.internal;

import com.tapjoy.TJVideoListener;

public class TJVideoListenerNative implements TJVideoListener {
    /* renamed from: a */
    private final long f7112a;

    private static native void onVideoCompleteNative(long j);

    private static native void onVideoErrorNative(long j, int i);

    private static native void onVideoStartNative(long j);

    private TJVideoListenerNative(long nativeHandle) {
        if (nativeHandle == 0) {
            throw new IllegalArgumentException();
        }
        this.f7112a = nativeHandle;
    }

    public void onVideoStart() {
        onVideoStartNative(this.f7112a);
    }

    public void onVideoError(int statusCode) {
        onVideoErrorNative(this.f7112a, statusCode);
    }

    public void onVideoComplete() {
        onVideoCompleteNative(this.f7112a);
    }

    @ew
    static Object create(long nativeHandle) {
        return new TJVideoListenerNative(nativeHandle);
    }
}
