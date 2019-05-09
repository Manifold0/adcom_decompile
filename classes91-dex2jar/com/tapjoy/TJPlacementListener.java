// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

public interface TJPlacementListener
{
    void onContentDismiss(final TJPlacement p0);
    
    void onContentReady(final TJPlacement p0);
    
    void onContentShow(final TJPlacement p0);
    
    void onPurchaseRequest(final TJPlacement p0, final TJActionRequest p1, final String p2);
    
    void onRequestFailure(final TJPlacement p0, final TJError p1);
    
    void onRequestSuccess(final TJPlacement p0);
    
    void onRewardRequest(final TJPlacement p0, final TJActionRequest p1, final String p2, final int p3);
}
