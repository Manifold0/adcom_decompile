package com.tapjoy.internal;

import com.tapjoy.TJActionRequest;
import com.tapjoy.TJError;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;

public class TJPlacementListenerNative implements TJPlacementListener {
    /* renamed from: a */
    private final long f7110a;

    private static native void onContentDismissNative(long j, TJPlacement tJPlacement, String str);

    private static native void onContentReadyNative(long j, TJPlacement tJPlacement, String str);

    private static native void onContentShowNative(long j, TJPlacement tJPlacement, String str);

    private static native void onPurchaseRequestNative(long j, TJPlacement tJPlacement, String str, TJActionRequest tJActionRequest, String str2, String str3, String str4);

    private static native void onRequestFailureNative(long j, TJPlacement tJPlacement, String str, int i, String str2);

    private static native void onRequestSuccessNative(long j, TJPlacement tJPlacement, String str);

    private static native void onRewardRequestNative(long j, TJPlacement tJPlacement, String str, TJActionRequest tJActionRequest, String str2, String str3, String str4, int i);

    private TJPlacementListenerNative(long nativeHandle) {
        if (nativeHandle == 0) {
            throw new IllegalArgumentException();
        }
        this.f7110a = nativeHandle;
    }

    public void onRequestSuccess(TJPlacement placement) {
        onRequestSuccessNative(this.f7110a, placement, placement.getName());
    }

    public void onRequestFailure(TJPlacement placement, TJError error) {
        onRequestFailureNative(this.f7110a, placement, placement.getName(), error.code, error.message);
    }

    public void onContentReady(TJPlacement placement) {
        onContentReadyNative(this.f7110a, placement, placement.getName());
    }

    public void onContentShow(TJPlacement placement) {
        onContentShowNative(this.f7110a, placement, placement.getName());
    }

    public void onContentDismiss(TJPlacement placement) {
        onContentDismissNative(this.f7110a, placement, placement.getName());
    }

    public void onPurchaseRequest(TJPlacement placement, TJActionRequest request, String productId) {
        onPurchaseRequestNative(this.f7110a, placement, placement.getName(), request, request.getRequestId(), request.getToken(), productId);
    }

    public void onRewardRequest(TJPlacement placement, TJActionRequest request, String itemId, int quantity) {
        onRewardRequestNative(this.f7110a, placement, placement.getName(), request, request.getRequestId(), request.getToken(), itemId, quantity);
    }

    @ew
    static Object create(long nativeHandle) {
        return new TJPlacementListenerNative(nativeHandle);
    }
}
