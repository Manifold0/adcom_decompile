// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import com.tapjoy.TJVideoListener;

public class TJVideoListenerNative implements TJVideoListener
{
    private final long a;
    
    private TJVideoListenerNative(final long a) {
        if (a == 0L) {
            throw new IllegalArgumentException();
        }
        this.a = a;
    }
    
    @ew
    static Object create(final long n) {
        return new TJVideoListenerNative(n);
    }
    
    private static native void onVideoCompleteNative(final long p0);
    
    private static native void onVideoErrorNative(final long p0, final int p1);
    
    private static native void onVideoStartNative(final long p0);
    
    @Override
    public void onVideoComplete() {
        onVideoCompleteNative(this.a);
    }
    
    @Override
    public void onVideoError(final int n) {
        onVideoErrorNative(this.a, n);
    }
    
    @Override
    public void onVideoStart() {
        onVideoStartNative(this.a);
    }
}
