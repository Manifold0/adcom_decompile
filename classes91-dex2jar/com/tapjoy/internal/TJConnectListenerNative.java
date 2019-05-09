// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import com.tapjoy.TJConnectListener;

public class TJConnectListenerNative implements TJConnectListener
{
    private final long a;
    
    private TJConnectListenerNative(final long a) {
        if (a == 0L) {
            throw new IllegalArgumentException();
        }
        this.a = a;
    }
    
    @ew
    static Object create(final long n) {
        return new TJConnectListenerNative(n);
    }
    
    private static native void onConnectFailureNative(final long p0);
    
    private static native void onConnectSuccessNative(final long p0);
    
    @Override
    public void onConnectFailure() {
        onConnectFailureNative(this.a);
    }
    
    @Override
    public void onConnectSuccess() {
        onConnectSuccessNative(this.a);
    }
}
