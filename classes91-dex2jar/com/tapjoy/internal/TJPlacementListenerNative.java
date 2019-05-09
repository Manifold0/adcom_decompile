// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import com.tapjoy.TJError;
import com.tapjoy.TJActionRequest;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;

public class TJPlacementListenerNative implements TJPlacementListener
{
    private final long a;
    
    private TJPlacementListenerNative(final long a) {
        if (a == 0L) {
            throw new IllegalArgumentException();
        }
        this.a = a;
    }
    
    @ew
    static Object create(final long n) {
        return new TJPlacementListenerNative(n);
    }
    
    private static native void onContentDismissNative(final long p0, final TJPlacement p1, final String p2);
    
    private static native void onContentReadyNative(final long p0, final TJPlacement p1, final String p2);
    
    private static native void onContentShowNative(final long p0, final TJPlacement p1, final String p2);
    
    private static native void onPurchaseRequestNative(final long p0, final TJPlacement p1, final String p2, final TJActionRequest p3, final String p4, final String p5, final String p6);
    
    private static native void onRequestFailureNative(final long p0, final TJPlacement p1, final String p2, final int p3, final String p4);
    
    private static native void onRequestSuccessNative(final long p0, final TJPlacement p1, final String p2);
    
    private static native void onRewardRequestNative(final long p0, final TJPlacement p1, final String p2, final TJActionRequest p3, final String p4, final String p5, final String p6, final int p7);
    
    @Override
    public void onContentDismiss(final TJPlacement tjPlacement) {
        onContentDismissNative(this.a, tjPlacement, tjPlacement.getName());
    }
    
    @Override
    public void onContentReady(final TJPlacement tjPlacement) {
        onContentReadyNative(this.a, tjPlacement, tjPlacement.getName());
    }
    
    @Override
    public void onContentShow(final TJPlacement tjPlacement) {
        onContentShowNative(this.a, tjPlacement, tjPlacement.getName());
    }
    
    @Override
    public void onPurchaseRequest(final TJPlacement tjPlacement, final TJActionRequest tjActionRequest, final String s) {
        onPurchaseRequestNative(this.a, tjPlacement, tjPlacement.getName(), tjActionRequest, tjActionRequest.getRequestId(), tjActionRequest.getToken(), s);
    }
    
    @Override
    public void onRequestFailure(final TJPlacement tjPlacement, final TJError tjError) {
        onRequestFailureNative(this.a, tjPlacement, tjPlacement.getName(), tjError.code, tjError.message);
    }
    
    @Override
    public void onRequestSuccess(final TJPlacement tjPlacement) {
        onRequestSuccessNative(this.a, tjPlacement, tjPlacement.getName());
    }
    
    @Override
    public void onRewardRequest(final TJPlacement tjPlacement, final TJActionRequest tjActionRequest, final String s, final int n) {
        onRewardRequestNative(this.a, tjPlacement, tjPlacement.getName(), tjActionRequest, tjActionRequest.getRequestId(), tjActionRequest.getToken(), s, n);
    }
}
